package com.alilmia.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alilmia.app.R
import com.alilmia.app.adapter.AudioAdapter
import com.alilmia.app.data.ScraperCallback
import com.alilmia.app.data.WebScraper
import com.alilmia.app.databinding.FragmentAudioBinding
import com.alilmia.app.model.ContentItem
import com.alilmia.app.model.ContentType

/**
 * Audio fragment for audio content playback and management
 * Features list layout optimized for audio content with ExoPlayer integration
 */
class AudioFragment : Fragment() {

    private var _binding: FragmentAudioBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var audioAdapter: AudioAdapter
    private lateinit var webScraper: WebScraper
    private var audioItems: MutableList<ContentItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAudioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupWebScraper()
        
        // Load audio content
        loadAudioContent()
    }

    private fun setupRecyclerView() {
        audioAdapter = AudioAdapter { contentItem ->
            // Handle audio item click - play audio
            playAudio(contentItem)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = audioAdapter
        }
    }

    private fun setupWebScraper() {
        webScraper = WebScraper()
    }

    private fun loadAudioContent() {
        showLoading(true)
        hideError()
        
        webScraper.scrapeHome(object : ScraperCallback {
            override fun onSuccess(items: List<ContentItem>) {
                activity?.runOnUiThread {
                    showLoading(false)
                    
                    // Filter only audio content
                    audioItems.clear()
                    audioItems.addAll(items.filter { it.type == ContentType.AUDIO })
                    audioAdapter.submitList(audioItems.toList())
                    
                    if (audioItems.isEmpty()) {
                        showError(getString(R.string.no_audio_content))
                    }
                }
            }

            override fun onFailure(error: Exception) {
                activity?.runOnUiThread {
                    showLoading(false)
                    showError(getString(R.string.error_loading_audio))
                    Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun playAudio(contentItem: ContentItem) {
        // TODO: Implement ExoPlayer audio playback
        // For now, show a toast with the audio title
        Toast.makeText(
            context,
            getString(R.string.playing_audio, contentItem.title),
            Toast.LENGTH_SHORT
        ).show()
        
        // Navigate to detail or show player UI
        navigateToDetail(contentItem)
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
        fun newInstance() = AudioFragment()
    }
}