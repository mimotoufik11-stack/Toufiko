package com.dammaj.app.model;

import java.io.Serializable;

public class AudioItem implements Serializable {
    private String id;
    private String title;
    private String audioUrl;
    private String duration;
    private String author;
    private String dateAdded;

    public AudioItem() {
    }

    public AudioItem(String id, String title, String audioUrl, String duration,
                     String author, String dateAdded) {
        this.id = id;
        this.title = title;
        this.audioUrl = audioUrl;
        this.duration = duration;
        this.author = author;
        this.dateAdded = dateAdded;
    }

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

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    @Override
    public String toString() {
        return "AudioItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", duration='" + duration + '\'' +
                ", author='" + author + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }
}
