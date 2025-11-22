package com.alilmia.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alilmia.app.R
import com.alilmia.app.adapter.ContentAdapter
import com.alilmia.app.data.ScraperCallback
import com.alilmia.app.data.WebScraper
import com.alilmia.app.databinding.FragmentLibraryBinding
import com.alilmia.app.model.ContentItem
import com.alilmia.app.model.ContentType
import com.google.android.material.chip.Chip

/**
 * Library fragment with category filtering and content organization
 * Features filter chips and categorized content display
 */
class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var webScraper: WebScraper
    private var contentItems: MutableList<ContentItem> = mutableListOf()
    private var filteredItems: MutableList<ContentItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupFilterChips()
        setupWebScraper()
        
        // Load initial content
        loadAllContent()
    }

    private fun setupRecyclerView() {
        contentAdapter = ContentAdapter { contentItem ->
            // Handle item click - navigate to detail
            navigateToDetail(contentItem)
        }
        
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = contentAdapter
        }
    }

    private fun setupFilterChips() {
        // Set up chip click listeners
        binding.chipAll.setOnClickListener {
            filterContent(null)
        }
        
        binding.chipBooks.setOnClickListener {
            filterContent(ContentType.BOOK)
        }
        
        binding.chipAudio.setOnClickListener {
            filterContent(ContentType.AUDIO)
        }
        
        binding.chipLessons.setOnClickListener {
            filterContent(ContentType.LESSON)
        }
        
        binding.chipFatwas.setOnClickListener {
            filterContent(ContentType.FATWA)
        }
        
        binding.chipScholars.setOnClickListener {
            filterContent(ContentType.SCHOLAR)
        }
    }

    private fun setupWebScraper() {
        webScraper = WebScraper()
    }

    private fun loadAllContent() {
        showLoading(true)
        hideError()
        
        webScraper.scrapeHome(object : ScraperCallback {
            override fun onSuccess(items: List<ContentItem>) {
                activity?.runOnUiThread {
                    showLoading(false)
                    contentItems.clear()
                    contentItems.addAll(items)
                    filteredItems.clear()
                    filteredItems.addAll(items)
                    contentAdapter.submitList(filteredItems.toList())
                    
                    if (items.isEmpty()) {
                        showError(getString(R.string.no_content_available))
                    }
                }
            }

            override fun onFailure(error: Exception) {
                activity?.runOnUiThread {
                    showLoading(false)
                    showError(getString(R.string.error_loading_content))
                    Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun filterContent(contentType: ContentType?) {
        filteredItems.clear()
        
        if (contentType == null) {
            filteredItems.addAll(contentItems)
        } else {
            filteredItems.addAll(contentItems.filter { it.type == contentType })
        }
        
        contentAdapter.submitList(filteredItems.toList())
        
        // Update chip selection
        updateChipSelection(contentType)
    }

    private fun updateChipSelection(selectedType: ContentType?) {
        binding.chipAll.isChecked = selectedType == null
        binding.chipBooks.isChecked = selectedType == ContentType.BOOK
        binding.chipAudio.isChecked = selectedType == ContentType.AUDIO
        binding.chipLessons.isChecked = selectedType == ContentType.LESSON
        binding.chipFatwas.isChecked = selectedType == ContentType.FATWA
        binding.chipScholars.isChecked = selectedType == ContentType.SCHOLAR
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        binding.errorText.text = message
        binding.errorText.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    private fun hideError() {
        binding.errorText.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun navigateToDetail(contentItem: ContentItem) {
        val intent = android.content.Intent(context, DetailActivity::class.java).apply {
            putExtra("content_item", contentItem)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        webScraper.shutdown()
    }

    companion object {
        fun newInstance() = LibraryFragment()
    }
}