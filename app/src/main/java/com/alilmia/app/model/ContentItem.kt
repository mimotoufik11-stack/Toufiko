package com.alilmia.app.model

/**
 * Data model for content items scraped from Al-Ilmia website
 */
data class ContentItem(
    val id: String,
    val title: String,
    val imageUrl: String?,
    val linkUrl: String,
    val type: ContentType,
    val description: String?,
    val author: String?,
    val dateAdded: Long,
    val category: String? = null,
    val duration: String? = null, // For audio content
    val tags: List<String> = emptyList()
)

/**
 * Enum for different content types available on Al-Ilmia
 */
enum class ContentType {
    BOOK,
    AUDIO,
    LESSON,
    FATWA,
    SCHOLAR,
    ARTICLE
}