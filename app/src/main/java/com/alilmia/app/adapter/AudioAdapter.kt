package com.alilmia.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.alilmia.app.R
import com.alilmia.app.databinding.ItemAudioCardBinding
import com.alilmia.app.model.ContentItem

/**
 * RecyclerView adapter for displaying audio content items in a list layout
 * Optimized for audio content with playback controls and metadata display
 */
class AudioAdapter(
    private val onItemClick: (ContentItem) -> Unit
) : ListAdapter<ContentItem, AudioAdapter.AudioViewHolder>(AudioDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val binding = ItemAudioCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AudioViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AudioViewHolder(
        private val binding: ItemAudioCardBinding,
        private val onItemClick: (ContentItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContentItem) {
            // Load thumbnail image with Glide
            if (!item.imageUrl.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.placeholder_audio)
                    .error(R.drawable.error_image)
                    .centerCrop()
                    .into(binding.thumbnailView)
            } else {
                binding.thumbnailView.setImageResource(R.drawable.placeholder_audio)
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
            
            // Set duration if available
            if (!item.duration.isNullOrEmpty()) {
                binding.durationText.text = item.duration
                binding.durationText.visibility = View.VISIBLE
            } else {
                binding.durationText.visibility = View.GONE
            }
            
            // Set description preview
            if (!item.description.isNullOrEmpty()) {
                val preview = if (item.description.length > 100) {
                    "${item.description.take(100)}..."
                } else {
                    item.description
                }
                binding.descriptionText.text = preview
                binding.descriptionText.visibility = View.VISIBLE
            } else {
                binding.descriptionText.visibility = View.GONE
            }
            
            // Set play button click listener
            binding.playButton.setOnClickListener {
                onItemClick(item)
            }
            
            // Set root click listener
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    private class AudioDiffCallback : DiffUtil.ItemCallback<ContentItem>() {
        override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem == newItem
        }
    }
}