package com.dammaj.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dammaj.app.R;
import com.dammaj.app.adapter.ContentAdapter;
import com.dammaj.app.data.ScraperCallback;
import com.dammaj.app.data.WebScraper;
import com.dammaj.app.model.ContentItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ContentAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private View errorLayout;
    private TextView errorMessage;
    private Button retryButton;
    private ContentAdapter contentAdapter;
    private WebScraper webScraper;
    private List<ContentItem> contentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initViews(view);
        setupRecyclerView();
        setupWebScraper();
        loadContent();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        errorLayout = view.findViewById(R.id.error_layout);
        errorMessage = view.findViewById(R.id.error_message);
        retryButton = view.findViewById(R.id.retry_button);
        
        contentList = new ArrayList<>();
        
        retryButton.setOnClickListener(v -> loadContent());
    }

    private void setupRecyclerView() {
        contentAdapter = new ContentAdapter(contentList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(contentAdapter);
    }

    private void setupWebScraper() {
        webScraper = new WebScraper();
    }

    private void loadContent() {
        showLoading();
        webScraper.scrapeHome(new ScraperCallback() {
            @Override
            public void onSuccess(List<ContentItem> contentItems) {
                if (getContext() != null) {
                    getContext().runOnUiThread(() -> {
                        hideLoading();
                        contentList.clear();
                        contentList.addAll(contentItems);
                        contentAdapter.notifyDataSetChanged();
                    });
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (getContext() != null) {
                    getContext().runOnUiThread(() -> {
                        hideLoading();
                        showError(e.getMessage() != null ? e.getMessage() : getString(R.string.error_loading));
                    });
                }
            }

            @Override
            public void onAudioSuccess(List<com.dammaj.app.model.AudioItem> audioItems) {
                // Not used in this fragment
            }

            @Override
            public void onAudioFailure(Exception e) {
                // Not used in this fragment
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
    }

    private void showError(String message) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        errorMessage.setText(message);
    }

    @Override
    public void onItemClick(ContentItem item) {
        // Handle item click - open content detail or browser
        if (item.getLinkUrl() != null && !item.getLinkUrl().isEmpty()) {
            // For now, show a toast with the URL
            Toast.makeText(getContext(), "فتح: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            
            // In a real implementation, you would:
            // 1. Open a detail fragment
            // 2. Or open in a web view
            // 3. Or open in external browser
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webScraper != null) {
            webScraper.shutdown();
        }
    }
}