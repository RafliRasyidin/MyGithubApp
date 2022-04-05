package com.rasyidin.mygithubapp.search.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rasyidin.mygithubapp.R
import com.rasyidin.mygithubapp.core.utils.onLoading
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentSearchBinding
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.component.RepositoryAdapter
import com.rasyidin.mygithubapp.ui.component.UserAdapter
import com.rasyidin.mygithubapp.ui.helper.isLoading
import com.rasyidin.mygithubapp.ui.helper.isSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

@FlowPreview
@AndroidEntryPoint
class SearchFragment : FragmentBinding<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var userAdapter: UserAdapter

    private lateinit var repoAdapter: RepositoryAdapter

    private val viewModel: SearchViewModel by activityViewModels()

    private var isSearchUsers = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isSearchUsers = true

        search()

        checkFilters()

    }

    override fun onResume() {
        super.onResume()
        showBotNavigation()
    }

    private fun onUserClick() {
        userAdapter.onItemClick = { user ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailProfileFragment(
                    user.username.toString()
                )
            )
        }
    }

    private fun onRepoClick() {
        repoAdapter.onItemClick = { repository ->
            val url = repository.htmlUrl?.toUri()
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }
    }

    private fun showBotNavigation() {
        val botNav =
            (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bot_nav_view)
        botNav.visibility = View.VISIBLE
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (isSearchUsers) {
                    observeUsers(query)
                } else {
                    observeRepos(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun checkFilters() {
        binding.rgFilters.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = requireActivity().findViewById<RadioButton>(checkedId)
            binding.searchView.setQuery(null, false)
            when (radioButton.text) {
                requireActivity().getString(R.string.users) -> isSearchUsers = true
                requireActivity().getString(R.string.repositories) -> isSearchUsers = false
            }
        }
    }

    private fun observeRepos(search: String) {
        viewModel.searchRepositories(search)
        lifecycleScope.launchWhenCreated {
            viewModel.repositories.collect { result ->
                setupRepoAdapter()

                binding.shimmerSearch.isVisible = isLoading(result)
                binding.rvSearch.isVisible = isSuccess(result)

                result.onLoading {
                    binding.animSearch.visibility = View.GONE
                }

                result.onSuccess { repos ->
                    if (repos.isNullOrEmpty()) {
                        binding.animSearch.visibility = View.VISIBLE
                        repoAdapter.submitList(null)
                    } else {
                        onRepoClick()
                        binding.animSearch.visibility = View.GONE
                        repoAdapter.submitList(repos)
                    }
                }

            }
        }
    }

    private fun observeUsers(search: String) {
        viewModel.searchUsers(search)
        lifecycleScope.launchWhenCreated {
            viewModel.users.collect { result ->
                setupUserAdapter()

                binding.shimmerSearch.isVisible = isLoading(result)
                binding.rvSearch.isVisible = isSuccess(result)

                result.onLoading {
                    binding.animSearch.visibility = View.GONE
                }

                result.onSuccess { users ->
                    if (users.isNullOrEmpty()) {
                        binding.animSearch.visibility = View.VISIBLE
                        userAdapter.submitList(null)
                    } else {
                        binding.animSearch.visibility = View.GONE
                        onUserClick()
                        userAdapter.submitList(users)
                    }
                }

            }
        }
    }

    private fun setupUserAdapter() {
        userAdapter = UserAdapter()
        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = userAdapter
        }
    }

    private fun setupRepoAdapter() {
        repoAdapter = RepositoryAdapter()
        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = repoAdapter
        }
    }
}