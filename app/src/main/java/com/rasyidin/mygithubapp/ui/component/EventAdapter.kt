package com.rasyidin.mygithubapp.ui.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rasyidin.mygithubapp.R
import com.rasyidin.mygithubapp.databinding.ItemEventBinding
import com.rasyidin.mygithubapp.home.domain.model.Event

class EventAdapter : ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback) {

    var onItemClick: ((Event) -> Unit)? = null

    class EventViewHolder(val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event, context: Context) {
            val actor = event.actor
            val repo = event.repo
            val payload = event.payload
            binding.apply {
                tvUsername.text = actor?.username
                Glide.with(context)
                    .load(actor?.avatarUrl)
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .error(R.drawable.ic_profile_placeholder)
                    .into(imgUser)
                when (event.type) {
                    WATCH_EVENT -> {
                        tvAdditionalEvent.visibility = View.GONE
                        labelOf.visibility = View.GONE
                        tvTypeEvent.text = context.getString(R.string.starred)
                        tvNameDestinationEvent.text = repo?.fullName
                        showCardRepo(event)
                    }
                    FORK_EVENT -> {
                        tvAdditionalEvent.visibility = View.VISIBLE
                        labelOf.visibility = View.VISIBLE
                        tvTypeEvent.text = context.getString(R.string.forked)
                        tvNameDestinationEvent.text = payload?.forkee?.fullName
                        labelOf.text = context.getString(R.string.from)
                        tvAdditionalEvent.text = repo?.fullName

                        showCardRepo(event)
                    }
                    CREATE_EVENT -> {
                        tvAdditionalEvent.visibility = View.GONE
                        labelOf.visibility = View.GONE
                        tvTypeEvent.text = context.getString(R.string.create_repo)
                        tvNameDestinationEvent.text = repo?.name

                        showCardRepo(event)
                    }
                    RELEASE_EVENT -> {
                        tvAdditionalEvent.visibility = View.VISIBLE
                        labelOf.visibility = View.VISIBLE
                        tvTypeEvent.text = context.getString(R.string.release)
                        tvNameDestinationEvent.text = payload?.release?.name
                        labelOf.text = context.getString(R.string.of)
                        tvAdditionalEvent.text = repo?.name

                        showCardRelease(event, context)
                    }
                    MEMBER_EVENT -> {
                        tvAdditionalEvent.visibility = View.VISIBLE
                        labelOf.visibility = View.VISIBLE
                        tvTypeEvent.text = context.getString(R.string.added)
                        tvNameDestinationEvent.text = payload?.member?.username
                        labelOf.text = context.getString(R.string.as_contributor)
                        tvAdditionalEvent.text = repo?.fullName

                        showCardRepo(event)
                    }
                    PUBLIC_EVENT -> {
                        tvAdditionalEvent.visibility = View.VISIBLE
                        labelOf.visibility = View.VISIBLE
                        tvTypeEvent.text = context.getString(R.string.made)
                        tvNameDestinationEvent.text = repo?.fullName
                        labelOf.text = context.getString(R.string.pub)
                        tvAdditionalEvent.text = ""

                        showCardRepo(event)
                    }
                }
            }
        }

        private fun showCardUser(event: Event) {
            binding.apply {
                containerCardUser.root.visibility = View.VISIBLE
                containerCardRelease.root.visibility = View.GONE
                containerCardUser.root.visibility = View.GONE
            }
        }

        private fun showCardRelease(event: Event, context: Context) {
            binding.apply {
                containerCardUser.root.visibility = View.GONE
                containerCardRelease.root.visibility = View.VISIBLE
                containerCardRepo.root.visibility = View.GONE

                val repo = event.repo
                with(containerCardRelease) {
                    Glide.with(context)
                        .load(event.org?.avatarUrl)
                        .placeholder(R.drawable.ic_profile_placeholder)
                        .error(R.drawable.ic_profile_placeholder)
                        .into(imgOrg)
                    tvRepoName.text = repo?.fullName
                    tvRepoDesc.text = repo?.description
                }
            }
        }

        private fun showCardRepo(event: Event?) {
            binding.apply {
                containerCardUser.root.visibility = View.GONE
                containerCardRelease.root.visibility = View.GONE
                containerCardRepo.root.visibility = View.VISIBLE

                val repository = event?.repo
                with(containerCardRepo) {
                    tvLang.text = repository?.language
                    tvRepoDesc.text = repository?.description
                    tvRepoName.text = repository?.fullName
                    tvStarCount.text = repository?.stargazersCount.toString()
                    tvUpdateAt.text = repository?.updatedAt
                }

            }
        }

        companion object {
            const val WATCH_EVENT = "WatchEvent"
            const val FORK_EVENT = "ForkEvent"
            const val CREATE_EVENT = "CreateEvent"
            const val RELEASE_EVENT = "ReleaseEvent"
            const val MEMBER_EVENT = "MemberEvent"
            const val PUBLIC_EVENT = "PublicEvent"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event, holder.itemView.context)

        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(event)
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }
}