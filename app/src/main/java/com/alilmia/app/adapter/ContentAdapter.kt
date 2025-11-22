package com.alilmia.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.alilmia.app.R
import com.alilmia.app.databinding.ItemBookCardBinding
import com.alilmia.app.model.ContentItem

/**
 * RecyclerView adapter for displaying content items in a card layout
 * Features image loading with Glide, click handling, and efficient list updates
 */
class ContentAdapter(
    private val onItemClick: (ContentItem) -> Unit
) : ListAdapter<ContentItem, ContentAdapter.ContentViewHolder>(ContentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binding = ItemBookCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContentViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContentViewHolder(
        private val binding: ItemBookCardBinding,
        private val onItemClick: (ContentItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContentItem) {
            // Load image with Glide
            if (!item.imageUrl.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.placeholder_book)
                    .error(R.drawable.error_image)
                    .centerCrop()
                    .into(binding.imageView)
            } else {
                binding.imageView.setImageResource(R.drawable.placeholder_book)
            }
            
            // Set title
            binding.titleText.text = item.title
            
            // Set author if available
            if (!item.author.isNullOrEmpty()) {
                binding.authorText.text = item.author
                binding.authorText.visibility = View.VISIBLE
            } else {
                binding.authorText.visibility = View.GONE
            }
            
            // Set content type indicator
            binding.typeIndicator.setImageResource(getTypeIndicator(item.type))
            
            // Set click listener
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }

        private fun getTypeIndicator(type: com.alilmia.app.model.ContentType): Int {
            return when (type) {
                com.alilmia.app.model.ContentType.BOOK -> R.drawable.ic_book
                com.alilmia.app.model.ContentType.AUDIO -> R.drawable.ic_audio
                com.alilmia.app.model.ContentType.LESSON -> R.drawable.ic_lesson
                com.alilmia.app.model.ContentType.FATWA -> R.drawable.ic_fatwa
                com.alilmia.app.model.ContentType.SCHOLAR -> R.drawable.ic_scholar
                com.alilmia.app.model.ContentType.ARTICLE -> R.drawable.ic_article
            }
        }
    }

    private class ContentDiffCallback : DiffUtil.ItemCallback<ContentItem>() {
        override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem == newItem
        }
    }
}