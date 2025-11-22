package com.dammaj.app.data;

import com.dammaj.app.model.AudioItem;
import com.dammaj.app.model.ContentItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebScraper {
    private static final String BASE_URL = "https://dammaj.org";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36";
    private final ExecutorService executor;

    public WebScraper() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void scrapeHome(ScraperCallback callback) {
        executor.execute(() -> {
            try {
                List<ContentItem> contentItems = new ArrayList<>();
                
                // Simulate web scraping - in real implementation, this would connect to the actual website
                // For demo purposes, we'll create sample data
                contentItems.addAll(createSampleContent());
                
                callback.onSuccess(contentItems);
            } catch (Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void scrapeCategory(String category, ScraperCallback callback) {
        executor.execute(() -> {
            try {
                List<ContentItem> contentItems = new ArrayList<>();
                
                // Filter sample content by category
                contentItems.addAll(createSampleContentByCategory(category));
                
                callback.onSuccess(contentItems);
            } catch (Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void scrapeAudio(ScraperCallback callback) {
        executor.execute(() -> {
            try {
                List<AudioItem> audioItems = new ArrayList<>();
                
                // Create sample audio items
                audioItems.addAll(createSampleAudio());
                
                callback.onAudioSuccess(audioItems);
            } catch (Exception e) {
                callback.onAudioFailure(e);
            }
        });
    }

    private List<ContentItem> createSampleContent() {
        List<ContentItem> items = new ArrayList<>();
        
        // Sample Books
        items.add(new ContentItem(
            "1", "شرح السنة", "https://example.com/book1.jpg", 
            BASE_URL + "/books/sharh-us-sunnah", "book",
            "شرح كتاب السنة للإمام البخاري رحمه الله", "الإمام البخاري", "2024-01-01", "books"
        ));
        
        items.add(new ContentItem(
            "2", "رياض الصالحين", "https://example.com/book2.jpg", 
            BASE_URL + "/books/riyad-us-saliheen", "book",
            "كتاب رياض الصالحين للنووي", "الإمام النووي", "2024-01-02", "books"
        ));
        
        // Sample Articles
        items.add(new ContentItem(
            "3", "أهمية الصلاة", "https://example.com/article1.jpg", 
            BASE_URL + "/articles/importance-of-prayer", "article",
            "مقال عن أهمية الصلاة في حياة المسلم", "د. أحمد محمد", "2024-01-03", "articles"
        ));
        
        items.add(new ContentItem(
            "4", "أخلاق المسلم", "https://example.com/article2.jpg", 
            BASE_URL + "/articles/muslim-ethics", "article",
            "الأخلاق الحميدة في الإسلام", "د. فهد العبدالله", "2024-01-04", "articles"
        ));
        
        // Sample Lessons
        items.add(new ContentItem(
            "5", "شرح التوحيد", "https://example.com/lesson1.jpg", 
            BASE_URL + "/lessons/tawhid-explanation", "lesson",
            "شرح مبسط لكتاب التوحيد", "الشيخ عبدالله الجبرين", "2024-01-05", "lessons"
        ));
        
        items.add(new ContentItem(
            "6", "فقه العبادات", "https://example.com/lesson2.jpg", 
            BASE_URL + "/lessons/worship-fiqh", "lesson",
            "شرح فقه العبادات للمبتدئين", "الشيخ محمد بن عثيمين", "2024-01-06", "lessons"
        ));
        
        // Sample Fatwas
        items.add(new ContentItem(
            "7", "حكم التأمين", "https://example.com/fatwa1.jpg", 
            BASE_URL + "/fatwas/insurance-ruling", "fatwa",
            "حكم التعامل بالتأمين التجاري", "اللجنة الدائمة للبحوث العلمية", "2024-01-07", "fatwas"
        ));
        
        // Sample Scholars
        items.add(new ContentItem(
            "8", "سيرة ابن باز", "https://example.com/scholar1.jpg", 
            BASE_URL + "/scholars/ibn-baz-biography", "scholar",
            "ترجمة موجزة لسماحة الشيخ ابن باز", "سيرة ذاتية", "2024-01-08", "scholars"
        ));
        
        return items;
    }

    private List<ContentItem> createSampleContentByCategory(String category) {
        List<ContentItem> allItems = createSampleContent();
        List<ContentItem> filteredItems = new ArrayList<>();
        
        for (ContentItem item : allItems) {
            if (category.equals(item.getCategory())) {
                filteredItems.add(item);
            }
        }
        
        return filteredItems;
    }

    private List<AudioItem> createSampleAudio() {
        List<AudioItem> items = new ArrayList<>();
        
        items.add(new AudioItem(
            "1", "شرح الحديث الأول", "https://example.com/audio1.mp3", 
            "45:30", "الشيخ عبدالعزيز الراجحي", "2024-01-01", "https://example.com/audio1.jpg"
        ));
        
        items.add(new AudioItem(
            "2", "محاضرة عن الصبر", "https://example.com/audio2.mp3", 
            "32:15", "الشيخ صالح الفوزان", "2024-01-02", "https://example.com/audio2.jpg"
        ));
        
        items.add(new AudioItem(
            "3", "تفسير سورة الفاتحة", "https://example.com/audio3.mp3", 
            "58:42", "الشيخ محمد بن صالح العثيمين", "2024-01-03", "https://example.com/audio3.jpg"
        ));
        
        items.add(new AudioItem(
            "4", "دروس في العقيدة", "https://example.com/audio4.mp3", 
            "41:20", "الشيخ عبدالله الجبرين", "2024-01-04", "https://example.com/audio4.jpg"
        ));
        
        items.add(new AudioItem(
            "5", "فتاوى متنوعة", "https://example.com/audio5.mp3", 
            "28:55", "اللجنة الدائمة للبحوث العلمية", "2024-01-05", "https://example.com/audio5.jpg"
        ));
        
        return items;
    }

    // Method to parse real document (for future implementation)
    private List<ContentItem> parseDocument(Document doc) {
        List<ContentItem> contentItems = new ArrayList<>();
        
        try {
            // Example WordPress selectors
            Elements articles = doc.select("article");
            
            for (Element article : articles) {
                String title = article.select(".entry-title, h1, h2, h3").first() != null ? 
                    article.select(".entry-title, h1, h2, h3").first().text() : "";
                
                String imageUrl = article.select(".wp-post-image, img").first() != null ? 
                    article.select(".wp-post-image, img").first().absUrl("src") : "";
                
                String linkUrl = article.select("a").first() != null ? 
                    article.select("a").first().absUrl("href") : "";
                
                String description = article.select(".entry-content, p").first() != null ? 
                    article.select(".entry-content, p").first().text() : "";
                
                if (!title.isEmpty() && !linkUrl.isEmpty()) {
                    ContentItem item = new ContentItem();
                    item.setTitle(title);
                    item.setImageUrl(imageUrl);
                    item.setLinkUrl(linkUrl);
                    item.setDescription(description);
                    item.setDateAdded(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
                    
                    contentItems.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return contentItems;
    }

    public void shutdown() {
        executor.shutdown();
    }
}