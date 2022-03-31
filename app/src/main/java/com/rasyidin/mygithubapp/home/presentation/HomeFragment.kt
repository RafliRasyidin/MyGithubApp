package com.rasyidin.mygithubapp.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.mygithubapp.core.utils.onFailure
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentHomeBinding
import com.rasyidin.mygithubapp.ui.component.EventAdapter
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.helper.isLoading
import com.rasyidin.mygithubapp.ui.helper.isSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : FragmentBinding<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var eventAdapter: EventAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        observeEvents()

    }

    private fun observeEvents() {
        lifecycleScope.launchWhenCreated {
            viewModel.events.collect { result ->

                binding.shimmerEvent.isVisible = isLoading(result)

                binding.rvEvent.isVisible = isSuccess(result)

                result.onSuccess { events ->
                    eventAdapter.submitList(events)
                }

                result.onFailure {
                    Log.e("HomeFragment", it.message.toString())
                }
            }
        }
    }

    private fun setupAdapter() {
        val itemDecoration = DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
        binding.rvEvent.apply {
            eventAdapter = EventAdapter()
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = eventAdapter
            addItemDecoration(itemDecoration)
        }
    }
}