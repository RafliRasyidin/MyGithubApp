package com.rasyidin.mygithubapp.profile.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.mygithubapp.R
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentFollowersFollowingBinding
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.component.UserAdapter
import com.rasyidin.mygithubapp.ui.helper.isLoading
import com.rasyidin.mygithubapp.ui.helper.isSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFollowingFragment :
    FragmentBinding<FragmentFollowersFollowingBinding>(FragmentFollowersFollowingBinding::inflate) {

    private lateinit var userAdapter: UserAdapter

    private val viewModel: ProfileViewModel by viewModels()

    private var username: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(ARG_USERNAME)

        fetchDataFoll()

        setupAdapter()

        setupViewPager()

        onItemClick()

    }

    private fun onItemClick() {
        userAdapter.onItemClick = { user ->
            val extra = Bundle()
            extra.putString(NAV_ARG_USERNAME, user.username)
            findNavController().navigate(R.id.detailProfileFragment, extra)
        }
    }

    private fun fetchDataFoll() {
        if (username.isNullOrEmpty()) {
            viewModel.getAuthFollUser()
        } else {
            viewModel.getFollUser(username.toString())
        }
    }

    private fun observeFollowers() {
        lifecycleScope.launchWhenCreated {
            viewModel.userFollowers.collect { result ->
                result.onSuccess { users ->
                    userAdapter.submitList(users)
                }

                binding.rvFollowersFollowing.isVisible = isSuccess(result)

                binding.shimmerUser.isVisible = isLoading(result)
            }
        }
    }

    private fun observeFollowing() {
        lifecycleScope.launchWhenCreated {
            viewModel.userFollowing.collect { result ->
                result.onSuccess { users ->
                    userAdapter.submitList(users)
                }

                binding.rvFollowersFollowing.isVisible = isSuccess(result)

                binding.shimmerUser.isVisible = isLoading(result)
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
        private const val ARG_USERNAME = "argUsername"
        private const val NAV_ARG_USERNAME = "username"

        @JvmStatic
        fun newInstance(username: String, position: Int) = FollowersFollowingFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_POSITION, position)
                putString(ARG_USERNAME, username)
            }
        }
    }
}