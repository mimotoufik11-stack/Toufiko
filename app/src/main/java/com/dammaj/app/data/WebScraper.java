package com.dammaj.app.data;

import android.util.Log;

import com.dammaj.app.model.ContentItem;

import org.jsoup.Connection;
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
    private static final String TAG = "WebScraper";
    private final ExecutorService executorService;

    public WebScraper() {
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public void scrapeHome(ScraperCallback callback) {
        executorService.execute(() -> {
            try {
                List<ContentItem> items = fetchFromHome();
                callback.onSuccess(items);
            } catch (Exception e) {
                Log.e(TAG, "Error scraping home", e);
                callback.onFailure(e);
            }
        });
    }

    public void scrapeCategory(String category, ScraperCallback callback) {
        executorService.execute(() -> {
            try {
                List<ContentItem> items = fetchFromCategory(category);
                callback.onSuccess(items);
            } catch (Exception e) {
                Log.e(TAG, "Error scraping category: " + category, e);
                callback.onFailure(e);
            }
        });
    }

    private List<ContentItem> fetchFromHome() throws IOException {
        List<ContentItem> items = new ArrayList<>();

        try {
            String url = "https://mimotoufik11-stack.github.io/Toufiko/";
            Document doc = connectWithUserAgent(url).get();

            items = parseDocument(doc);

            if (items.isEmpty()) {
                Log.w(TAG, "No items found using primary selector, trying fallback");
                items = parseFallback(doc);
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch from home", e);
            throw e;
        }

        return items;
    }

    private List<ContentItem> fetchFromCategory(String category) throws IOException {
        List<ContentItem> items = new ArrayList<>();

        try {
            String url = "https://mimotoufik11-stack.github.io/Toufiko/?category=" + category;
            Document doc = connectWithUserAgent(url).get();

            items = parseDocument(doc);

            if (items.isEmpty()) {
                items = parseFallback(doc);
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch category: " + category, e);
            throw e;
        }

        return items;
    }

    private List<ContentItem> parseDocument(Document doc) {
        List<ContentItem> items = new ArrayList<>();

        try {
            Elements articles = doc.select("article, .content-item, [data-type='content']");

            if (articles.isEmpty()) {
                Log.w(TAG, "No articles found with main selectors");
                return items;
            }

            for (Element article : articles) {
                try {
                    ContentItem item = extractContentItem(article);
                    if (item != null && item.getTitle() != null && !item.getTitle().isEmpty()) {
                        items.add(item);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "Error parsing individual item", e);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error parsing document", e);
        }

        return items;
    }

    private List<ContentItem> parseFallback(Document doc) {
        List<ContentItem> items = new ArrayList<>();

        try {
            Elements articles = doc.select("div, section, li");

            for (Element element : articles) {
                try {
                    if (element.hasAttr("data-content") || element.hasAttr("data-item")) {
                        ContentItem item = extractContentItem(element);
                        if (item != null && item.getTitle() != null) {
                            items.add(item);
                        }
                    }
                } catch (Exception e) {
                    Log.w(TAG, "Error parsing fallback item", e);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error with fallback parsing", e);
        }

        return items;
    }

    private ContentItem extractContentItem(Element element) {
        try {
            ContentItem item = new ContentItem();

            item.setId(element.attr("data-id").isEmpty() ? System.currentTimeMillis() + "" : element.attr("data-id"));

            Element titleElement = element.selectFirst("h1, h2, h3, .title, [data-title]");
            if (titleElement != null) {
                item.setTitle(titleElement.text());
            }

            Element linkElement = element.selectFirst("a");
            if (linkElement != null) {
                item.setLinkUrl(linkElement.attr("href"));
            }

            Element imageElement = element.selectFirst("img");
            if (imageElement != null) {
                item.setImageUrl(imageElement.attr("src"));
            }

            Element typeElement = element.selectFirst("[data-type], .type, .category");
            if (typeElement != null) {
                item.setType(typeElement.text());
            } else {
                item.setType("Content");
            }

            Element descElement = element.selectFirst("p, .description, [data-description]");
            if (descElement != null) {
                item.setDescription(descElement.text());
            }

            Element authorElement = element.selectFirst(".author, [data-author]");
            if (authorElement != null) {
                item.setAuthor(authorElement.text());
            }

            item.setDateAdded(String.valueOf(System.currentTimeMillis()));

            return item;
        } catch (Exception e) {
            Log.e(TAG, "Error extracting content item", e);
            return null;
        }
    }

    private Connection connectWithUserAgent(String url) {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .timeout(10000)
                .followRedirects(true);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
