package com.ml.redditreaderapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ml.redditreaderapp.databinding.ListItemPostBinding
import com.ml.redditreaderapp.domain.RedditPost

class PostAdapter(val postClick: (RedditPost) -> Unit) :
    PagingDataAdapter<RedditPost, PostAdapter.PostViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { post ->
            holder.bindTo(post)
            holder.itemView.setOnClickListener { postClick(post) }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ListItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PostViewHolder(binding)
    }

    class PostViewHolder(private val binding: ListItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(redditPost: RedditPost) {
            with(redditPost) {
                binding.authorNameTextview.text = title
                binding.postTitleTextview.text = author
            }
        }
    }
}


val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem == newItem
    }
}
