package com.alilmia.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alilmia.app.R
import com.alilmia.app.adapter.ContentAdapter
import com.alilmia.app.data.ScraperCallback
import com.alilmia.app.data.WebScraper
import com.alilmia.app.databinding.FragmentHomeBinding
import com.alilmia.app.model.ContentItem

/**
 * Home fragment displaying scraped content from Al-Ilmia homepage
 * Features grid layout with pull-to-refresh and error handling
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var webScraper: WebScraper
    private var contentItems: MutableList<ContentItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupSwipeRefresh()
        setupWebScraper()
        
        // Load initial content
        loadContent()
    }

    private fun setupRecyclerView() {
        contentAdapter = ContentAdapter { contentItem ->
            // Handle item click - navigate to detail
            navigateToDetail(contentItem)
        }
        
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = contentAdapter
            // Add spacing between items
            addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(
                context, 
                androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
            ))
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            loadContent()
        }
        
        // Configure colors for swipe refresh
        binding.swipeRefresh.setColorSchemeColors(
            resources.getColor(R.color.gold_luxury, null)
        )
    }

    private fun setupWebScraper() {
        webScraper = WebScraper()
    }

    private fun loadContent() {
        showLoading(true)
        hideError()
        
        webScraper.scrapeHome(object : ScraperCallback {
            override fun onSuccess(items: List<ContentItem>) {
                activity?.runOnUiThread {
                    showLoading(false)
                    contentItems.clear()
                    contentItems.addAll(items)
                    contentAdapter.submitList(contentItems.toList())
                    
                    if (items.isEmpty()) {
                        showError(getString(R.string.no_content_available))
                    }
                }
            }

            override fun onFailure(error: Exception) {
                activity?.runOnUiThread {
                    showLoading(false)
                    showError(getString(R.string.error_loading_content))
                    // Show retry option
                    Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.swipeRefresh.isRefreshing = false
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
        // Navigate to detail activity
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
        fun newInstance() = HomeFragment()
    }
}