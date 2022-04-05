package com.rasyidin.mygithubapp.ui.component

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rasyidin.mygithubapp.R
import com.rasyidin.mygithubapp.profile.presentation.FollowersFollowingFragment
import com.rasyidin.mygithubapp.profile.presentation.RepositoryFragment

class ProfilePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val username: String
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 ->RepositoryFragment.newInstance(username)
            else -> FollowersFollowingFragment.newInstance(username, position)
        }
    }

    companion object {
        val TAB_TITLES = intArrayOf(R.string.repositories, R.string.followers, R.string.following)
    }
}