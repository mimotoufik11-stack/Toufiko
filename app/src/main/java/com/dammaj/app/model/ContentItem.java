package com.dammaj.app.model;

import java.util.Date;

public class ContentItem {
    private String id;
    private String title;
    private String imageUrl;
    private String linkUrl;
    private String type;
    private String description;
    private String author;
    private String dateAdded;
    private String category;

    public ContentItem() {
    }

    public ContentItem(String id, String title, String imageUrl, String linkUrl, String type, 
                     String description, String author, String dateAdded, String category) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.type = type;
        this.description = description;
        this.author = author;
        this.dateAdded = dateAdded;
        this.category = category;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ContentItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}