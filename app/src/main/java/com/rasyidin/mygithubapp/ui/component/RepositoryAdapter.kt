package com.rasyidin.mygithubapp.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rasyidin.mygithubapp.databinding.ContainerCardRepoBinding
import com.rasyidin.mygithubapp.search.domain.model.Repository

class RepositoryAdapter :
    ListAdapter<Repository, RepositoryAdapter.RepositoryViewHolder>(DiffCallback) {

    var onItemClick: ((Repository) -> Unit)? = null

    class RepositoryViewHolder(val binding: ContainerCardRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) {

            binding.apply {

                val isDescEmpty = repository.description.isNullOrEmpty()
                if (isDescEmpty) {
                    tvRepoDesc.visibility = View.GONE
                } else {
                    tvRepoDesc.visibility = View.VISIBLE
                }

                tvRepoName.text = repository.fullName
                tvStarCount.text = repository.stargazersCount.toString()
                tvUpdateAt.text = repository.updatedAt
                tvRepoDesc.text = repository.description
                tvLang.text = repository.language
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ContainerCardRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = getItem(position)
        holder.bind(repository)
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(repository)
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}