package com.rasyidin.mygithubapp.profile.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentRepositoryBinding
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.component.RepositoryAdapter
import com.rasyidin.mygithubapp.ui.helper.isLoading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RepositoryFragment : FragmentBinding<FragmentRepositoryBinding>(FragmentRepositoryBinding::inflate) {

    private val viewModel: ProfileViewModel by activityViewModels()

    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeRepository()

    }

    private fun observeRepository() {
        lifecycleScope.launchWhenCreated {
            viewModel.userRepos.collect { result ->
                result.onSuccess { repos ->
                    repositoryAdapter.submitList(repos)
                }

                binding.shimmerRepo.isVisible = isLoading(result)
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

}