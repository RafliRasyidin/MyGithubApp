package com.rasyidin.mygithubapp.search.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.rasyidin.mygithubapp.databinding.FragmentRepositoryBinding
import com.rasyidin.mygithubapp.profile.presentation.ProfileViewModel
import com.rasyidin.mygithubapp.ui.component.FragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment : FragmentBinding<FragmentRepositoryBinding>(FragmentRepositoryBinding::inflate) {

    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}