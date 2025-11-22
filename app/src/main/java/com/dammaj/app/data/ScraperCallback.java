package com.dammaj.app.data;

import com.dammaj.app.model.ContentItem;
import com.dammaj.app.model.AudioItem;

import java.util.List;

public interface ScraperCallback {
    void onSuccess(List<ContentItem> contentItems);
    void onFailure(Exception e);
    
    // For audio content
    void onAudioSuccess(List<AudioItem> audioItems);
    void onAudioFailure(Exception e);
}