package com.alilmia.app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.alilmia.app.databinding.ActivityDetailBinding
import com.alilmia.app.model.ContentItem
import com.bumptech.glide.Glide

/**
 * Detail activity for displaying full content information
 * Features image display, content metadata, and external link opening
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var contentItem: ContentItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        extractContentData()
        setupUI()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
        }
    }

    private fun extractContentData() {
        contentItem = intent.getParcelableExtra<ContentItem>("content_item")
        if (contentItem == null) {
            Toast.makeText(this, getString(R.string.error_loading_content), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupUI() {
        contentItem?.let { item ->
            // Load image with Glide
            if (!item.imageUrl.isNullOrEmpty()) {
                Glide.with(this)
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
            
            // Set description
            if (!item.description.isNullOrEmpty()) {
                binding.descriptionText.text = item.description
                binding.descriptionText.visibility = android.view.View.VISIBLE
            } else {
                binding.descriptionText.visibility = android.view.View.GONE
            }
            
            // Set author
            if (!item.author.isNullOrEmpty()) {
                binding.authorText.text = getString(R.string.author_format, item.author)
                binding.authorText.visibility = android.view.View.VISIBLE
            } else {
                binding.authorText.visibility = android.view.View.GONE
            }
            
            // Set content type
            binding.typeText.text = getString(getContentTypeStringRes(item.type))
            
            // Set date
            val dateFormatted = java.text.SimpleDateFormat(
                "MMM dd, yyyy", 
                java.util.Locale.getDefault()
            ).format(java.util.Date(item.dateAdded))
            binding.dateText.text = dateFormatted
            
            // Setup action buttons
            setupActionButtons(item)
        }
    }

    private fun setupActionButtons(item: ContentItem) {
        // Open in browser button
        binding.openBrowserButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.linkUrl))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.error_opening_link), Toast.LENGTH_SHORT).show()
            }
        }
        
        // Share button
        binding.shareButton.setOnClickListener {
            shareContent(item)
        }
        
        // For audio content, show play button
        if (item.type == com.alilmia.app.model.ContentType.AUDIO) {
            binding.playButton.visibility = android.view.View.VISIBLE
            binding.playButton.setOnClickListener {
                // TODO: Implement audio playback
                Toast.makeText(this, getString(R.string.audio_playback_coming_soon), Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.playButton.visibility = android.view.View.GONE
        }
    }

    private fun shareContent(item: ContentItem) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, item.title)
            putExtra(Intent.EXTRA_TEXT, "${item.title}\n\n${item.description ?: ""}\n\n${item.linkUrl}")
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_content)))
    }

    private fun getContentTypeStringRes(type: com.alilmia.app.model.ContentType): Int {
        return when (type) {
            com.alilmia.app.model.ContentType.BOOK -> R.string.type_book
            com.alilmia.app.model.ContentType.AUDIO -> R.string.type_audio
            com.alilmia.app.model.ContentType.LESSON -> R.string.type_lesson
            com.alilmia.app.model.ContentType.FATWA -> R.string.type_fatwa
            com.alilmia.app.model.ContentType.SCHOLAR -> R.string.type_scholar
            com.alilmia.app.model.ContentType.ARTICLE -> R.string.type_article
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_share -> {
                contentItem?.let { shareContent(it) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}