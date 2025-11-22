package com.alilmia.app.data

import com.alilmia.app.model.ContentItem

/**
 * Callback interface for web scraping operations
 */
interface ScraperCallback {
    /**
     * Called when scraping succeeds
     * @param items List of scraped content items
     */
    fun onSuccess(items: List<ContentItem>)
    
    /**
     * Called when scraping fails
     * @param error Exception that occurred during scraping
     */
    fun onFailure(error: Exception)
}