package com.dammaj.app.data;

import com.dammaj.app.model.ContentItem;
import java.util.List;

public interface ScraperCallback {
    void onSuccess(List<ContentItem> items);
    void onFailure(Exception e);
}
