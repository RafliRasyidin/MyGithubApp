package com.rasyidin.mygithubapp.profile.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentRepositoryBinding
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.component.RepositoryAdapter
import com.rasyidin.mygithubapp.ui.helper.isLoading
import com.rasyidin.mygithubapp.ui.helper.isSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment :
    FragmentBinding<FragmentRepositoryBinding>(FragmentRepositoryBinding::inflate) {

    private val viewModel: ProfileViewModel by activityViewModels()

    private lateinit var repositoryAdapter: RepositoryAdapter

    private var username: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(ARG_USERNAME)

        setupAdapter()

        observeRepository()

        onItemClick()

    }

    private fun onItemClick() {
        repositoryAdapter.onItemClick = { repository ->
            val url = repository.htmlUrl?.toUri()
            val intent = Intent(Intent.ACTION_VIEW, url)
            startActivity(intent)
        }
    }

    private fun observeRepository() {
        if (username.isNullOrEmpty()) {
            viewModel.getAuthReposUser()
        } else {
            viewModel.getReposUser(username.toString())
        }

        lifecycleScope.launchWhenCreated {
            viewModel.userRepos.collect { result ->
                result.onSuccess { repos ->
                    repositoryAdapter.submitList(repos)
                }

                binding.shimmerRepo.isVisible = isLoading(result)

                binding.rvRepository.isVisible = isSuccess(result)
            }
        }
    }

    private fun setupAdapter() {
        binding.rvRepository.apply {
            repositoryAdapter = RepositoryAdapter()
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = repositoryAdapter
        }
    }

    companion object {
        private const val ARG_USERNAME = "argUsername"

        @JvmStatic
        fun newInstance(username: String) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_USERNAME, username)
            }
        }
    }

}