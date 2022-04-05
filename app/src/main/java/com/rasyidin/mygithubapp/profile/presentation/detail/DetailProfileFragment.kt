package com.rasyidin.mygithubapp.profile.presentation.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.rasyidin.mygithubapp.R
import com.rasyidin.mygithubapp.core.utils.onFailure
import com.rasyidin.mygithubapp.core.utils.onSuccess
import com.rasyidin.mygithubapp.databinding.FragmentDetailProfileBinding
import com.rasyidin.mygithubapp.profile.presentation.ProfileViewModel
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import com.rasyidin.mygithubapp.ui.component.ProfilePagerAdapter
import com.rasyidin.mygithubapp.ui.component.ProfilePagerAdapter.Companion.TAB_TITLES
import com.rasyidin.mygithubapp.ui.helper.isLoading
import com.rasyidin.mygithubapp.ui.helper.isSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailProfileFragment :
    FragmentBinding<FragmentDetailProfileBinding>(FragmentDetailProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    private val args: DetailProfileFragmentArgs by navArgs()

    private lateinit var mediator: TabLayoutMediator

    private var isNotFollowed = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBotNavigation()

        setupViewPager()

        setupAdapter()

        observeProfile()

        observeIsFollowed()

        setupToolbar()

        observeFollow()

        onBtnFollowClick()

    }

    private fun onBtnFollowClick() {
        binding.btnFollow.setOnClickListener {
            if (isNotFollowed) {
                viewModel.followUser(args.username)
            } else {
                viewModel.unfollowUser(args.username)
            }
        }
    }

    private fun setupToolbar() {
        binding.tvToolbar.text = args.username
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setBtnFollow() {
        binding.btnFollow.apply {
            text = getString(R.string.follow)
            setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.white
                )
            )
            setBackgroundColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.black
                )
            )
            strokeWidth = 0
            strokeColor = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireActivity(),
                    android.R.color.transparent
                )
            )
        }
    }

    private fun setBtnUnfollow() {
        binding.btnFollow.apply {
            text = getString(R.string.unfollow)
            setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.black
                )
            )
            setBackgroundColor(
                ContextCompat.getColor(requireActivity(), R.color.white)
            )
            strokeWidth = 2
            strokeColor = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.black
                )
            )
        }
    }

    private fun observeFollow() {
        lifecycleScope.launchWhenCreated {
            viewModel.eventFollow.collectLatest { result ->
                result.onFailure {
                    val isFollowed = it.message.toString().contains("204")
                    if (isFollowed) {
                        setBtnUnfollow()
                    } else {
                        setBtnFollow()
                    }
                    viewModel.getDetailUser(args.username)
                }
            }
        }
    }

    private fun observeIsFollowed() {
        lifecycleScope.launchWhenCreated {
            viewModel.isFollowed.collect { result ->

                result.onFailure {
                    isNotFollowed = it.message.toString().contains("404")
                    binding.btnFollow.apply {
                        if (isNotFollowed) {
                            setBtnFollow()
                        } else {
                            setBtnUnfollow()
                        }

                    }
                }
            }
        }
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
                        tvFollowersCount.text = user?.followers.toString()
                        tvFollowingCount.text = user?.following.toString()
                        tvRepoCount.text = user?.publicRepos.toString()
                        tvLocation.text = user?.location
                        tvName.text = user?.name
                    }
                }
            }
        }

    }

    private fun hideBotNavigation() {
        val botNav = (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bot_nav_view)
        botNav.visibility = View.GONE
    }

    private fun setupViewPager() {
        val pagerAdapter by lazy {
            ProfilePagerAdapter(childFragmentManager, lifecycle, args.username)
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
        binding.vp.adapter = null
        mediator.detach()
        _binding = null
    }

}