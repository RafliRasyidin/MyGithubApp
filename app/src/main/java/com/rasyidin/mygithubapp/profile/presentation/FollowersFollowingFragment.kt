package com.rasyidin.mygithubapp.profile.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentFollowersFollowingBinding
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.component.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FollowersFollowingFragment :
    FragmentBinding<FragmentFollowersFollowingBinding>(FragmentFollowersFollowingBinding::inflate) {

    private lateinit var userAdapter: UserAdapter

    private val viewModel: ProfileViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        setupViewPager()


    }

    private fun observeFollowers() {
        lifecycleScope.launchWhenCreated {
            viewModel.userFollowers.collect { result ->
                result.onSuccess { users ->
                    userAdapter.submitList(users)
                }
            }
        }
    }

    private fun observeFollowing() {
        lifecycleScope.launchWhenCreated {
            viewModel.userFollowing.collect { result ->
                 result.onSuccess { users ->
                     userAdapter.submitList(users)
                 }
            }
        }
    }

    private fun setupViewPager() {
        val position = arguments?.getInt(ARG_POSITION)

        when (position) {
            1 -> observeFollowers()
            2 -> observeFollowing()
        }
    }

    private fun setupAdapter() {
        binding.rvFollowersFollowing.apply {
            userAdapter = UserAdapter()
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvFollowersFollowing.adapter = null
        _binding = null
    }

    companion object {
        private const val ARG_POSITION = "argPosition"

        @JvmStatic
        fun newInstance(position: Int) = FollowersFollowingFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_POSITION, position)
            }
        }
    }
}