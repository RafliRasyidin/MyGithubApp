package com.rasyidin.mygithubapp.profile.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.rasyidin.mygithubapp.R
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentProfileBinding
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.component.ProfilePagerAdapter
import com.rasyidin.mygithubapp.ui.component.ProfilePagerAdapter.Companion.TAB_TITLES
import com.rasyidin.mygithubapp.ui.helper.isLoading
import com.rasyidin.mygithubapp.ui.helper.isSuccess
import com.rasyidin.mygithubapp.ui.helper.toShortNumber
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : FragmentBinding<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by activityViewModels()

    private lateinit var mediator: TabLayoutMediator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()

        setupAdapter()

        observeProfile()

    }

    override fun onResume() {
        super.onResume()
        showBotNavigation()
    }

    private fun showBotNavigation() {
        val botNav = (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bot_nav_view)
        botNav.visibility = View.VISIBLE
    }

    private fun observeProfile() {
        lifecycleScope.launchWhenCreated {
            viewModel.user.collect { result ->

                binding.shimmerAppBar.isVisible = isLoading(result)

                binding.appBar.isVisible = isSuccess(result)

                binding.containerTab.isVisible = isSuccess(result)

                result.onSuccess { user ->
                    val isBioEmpty = user?.bio.isNullOrEmpty()
                    val isEmailEmpty = user?.email.isNullOrEmpty()
                    val isCompanyEmpty = user?.company.isNullOrEmpty()
                    val isLocationEmpty = user?.location.isNullOrEmpty()

                    binding.apply {
                        if (isBioEmpty) {
                            tvBio.visibility = View.GONE
                        } else {
                            tvBio.visibility = View.VISIBLE
                        }

                        if (isEmailEmpty) {
                            imgEmail.visibility = View.GONE
                        } else {
                            imgEmail.visibility = View.VISIBLE
                        }

                        if (isCompanyEmpty) {
                            imgCompany.visibility = View.GONE
                        } else {
                            imgCompany.visibility = View.VISIBLE
                        }

                        if (isLocationEmpty) {
                            imgLocation.visibility = View.GONE
                        } else {
                            imgLocation.visibility = View.VISIBLE
                        }

                        Glide.with(requireActivity())
                            .load(user?.avatarUrl)
                            .placeholder(R.drawable.ic_profile_placeholder)
                            .error(R.drawable.ic_profile_placeholder)
                            .into(imgUser)

                        tvToolbar.text = user?.username
                        tvBio.text = user?.bio
                        tvCompany.text = user?.company
                        tvEmail.text = user?.email
                        tvFollowersCount.text = user?.followers.toString().toShortNumber()
                        tvFollowingCount.text = user?.following.toString().toShortNumber()
                        tvRepoCount.text = user?.publicRepos.toString().toShortNumber()
                        tvLocation.text = user?.location
                        tvName.text = user?.name
                    }
                }
            }
        }

    }

    private fun setupViewPager() {
        val pagerAdapter by lazy {
            ProfilePagerAdapter(childFragmentManager, lifecycle, "")
        }
        binding.vp.adapter = pagerAdapter
    }

    private fun setupAdapter() {
        mediator = TabLayoutMediator(binding.tabs, binding.vp) { tabs, pos ->
            tabs.text = when (pos) {
                0 -> getString(TAB_TITLES[0])
                1 -> getString(TAB_TITLES[1])
                else -> getString(TAB_TITLES[2])
            }
        }
        mediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator.detach()
        binding.vp.adapter = null
        _binding = null
    }

}