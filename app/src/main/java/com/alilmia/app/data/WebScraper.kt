package com.alilmia.app.data

import com.alilmia.app.model.ContentItem
import com.alilmia.app.model.ContentType
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.net.URLEncoder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Web scraper for Al-Ilmia website using Jsoup
 * Handles background threading and robust error handling
 */
class WebScraper {
    
    private val executor: ExecutorService = Executors.newFixedThreadPool(3)
    private val baseUrl = "https://alilmia.com"
    
    /**
     * Scrape homepage content
     */
    fun scrapeHome(callback: ScraperCallback) {
        executor.execute {
            try {
                val doc = Jsoup.connect(baseUrl)
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .get()
                
                val items = parseDocument(doc)
                callback.onSuccess(items)
            } catch (e: Exception) {
                callback.onFailure(e)
            }
        }
    }
    
    /**
     * Scrape specific category content
     */
    fun scrapeCategory(category: String, callback: ScraperCallback) {
        executor.execute {
            try {
                val encodedCategory = URLEncoder.encode(category, "UTF-8")
                val url = "$baseUrl/category/$encodedCategory"
                
                val doc = Jsoup.connect(url)
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .get()
                
                val items = parseDocument(doc, category)
                callback.onSuccess(items)
            } catch (e: Exception) {
                callback.onFailure(e)
            }
        }
    }
    
    /**
     * Parse document to extract content items
     */
    private fun parseDocument(doc: Document, category: String? = null): List<ContentItem> {
        val items = mutableListOf<ContentItem>()
        
        try {
            // Try multiple selectors for robustness
            val articles = doc.select("article, .post, .entry, .content-item")
            
            for (element in articles) {
                try {
                    val item = extractContentFromElement(element, category)
                    if (item != null) {
                        items.add(item)
                    }
                } catch (e: Exception) {
                    // Continue processing other elements
                }
            }
            
            // If no items found, try alternative selectors
            if (items.isEmpty()) {
                val fallbackElements = doc.select(".post-item, .article, .lesson, .book")
                for (element in fallbackElements) {
                    try {
                        val item = extractContentFromElement(element, category)
                        if (item != null) {
                            items.add(item)
                        }
                    } catch (e: Exception) {
                        // Continue processing
                    }
                }
            }
        } catch (e: Exception) {
            // Return empty list on parsing error
        }
        
        return items
    }
    
    /**
     * Extract content data from individual HTML element
     */
    private fun extractContentFromElement(element: Element, category: String?): ContentItem? {
        // Extract title
        val titleElement = element.select(".entry-title, .post-title, h1, h2, h3, .title").first()
            ?: element.select("a[title]").first()
        val title = titleElement?.text()?.trim() ?: return null
        
        // Extract link
        val linkElement = element.select("a[href]").first()
        val linkUrl = linkElement?.attr("href") ?: ""
        val fullLinkUrl = if (linkUrl.startsWith("http")) linkUrl else "$baseUrl$linkUrl"
        
        // Extract image
        val imgElement = element.select("img[src]").first()
        val imageUrl = imgElement?.attr("src") ?: imgElement?.attr("data-src")
        val fullImageUrl = if (!imageUrl.isNullOrEmpty() && imageUrl.startsWith("http")) {
            imageUrl
        } else if (!imageUrl.isNullOrEmpty()) {
            "$baseUrl$imageUrl"
        } else {
            null
        }
        
        // Extract description
        val descElement = element.select(".post-content, .entry-content, .excerpt, .description, p").first()
        val description = descElement?.text()?.trim()
        
        // Extract author
        val authorElement = element.select(".author, .by-author, .post-author").first()
        val author = authorElement?.text()?.trim()
        
        // Determine content type based on classes and URL patterns
        val type = determineContentType(element, fullLinkUrl, title)
        
        return ContentItem(
            id = generateId(title, fullLinkUrl),
            title = title,
            imageUrl = fullImageUrl,
            linkUrl = fullLinkUrl,
            type = type,
            description = description,
            author = author,
            dateAdded = System.currentTimeMillis(),
            category = category
        )
    }
    
    /**
     * Determine content type based on element attributes and URL
     */
    private fun determineContentType(element: Element, url: String, title: String): ContentType {
        val elementClasses = element.className().lowercase()
        val urlLower = url.lowercase()
        val titleLower = title.lowercase()
        
        return when {
            elementClasses.contains("audio") || urlLower.contains("audio") || titleLower.contains("صوتي") -> ContentType.AUDIO
            elementClasses.contains("book") || urlLower.contains("book") || titleLower.contains("كتاب") -> ContentType.BOOK
            elementClasses.contains("lesson") || urlLower.contains("lesson") || titleLower.contains("درس") -> ContentType.LESSON
            elementClasses.contains("fatwa") || urlLower.contains("fatwa") || titleLower.contains("فتوى") -> ContentType.FATWA
            elementClasses.contains("scholar") || urlLower.contains("scholar") || titleLower.contains("عالم") -> ContentType.SCHOLAR
            else -> ContentType.ARTICLE
        }
    }
    
    /**
     * Generate unique ID for content item
     */
    private fun generateId(title: String, url: String): String {
        return "${title}_${url.hashCode()}"
    }
    
    /**
     * Shutdown executor service
     */
    fun shutdown() {
        executor.shutdown()
    }
}