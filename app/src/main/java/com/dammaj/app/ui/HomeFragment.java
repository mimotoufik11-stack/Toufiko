package com.dammaj.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dammaj.app.R;
import com.dammaj.app.adapter.ContentAdapter;
import com.dammaj.app.data.ScraperCallback;
import com.dammaj.app.data.WebScraper;
import com.dammaj.app.model.ContentItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment implements ScraperCallback {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView errorText;
    private ContentAdapter adapter;
    private WebScraper scraper;
    private ExecutorService executorService;
    private List<ContentItem> contentItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.recycler_view);
        errorText = view.findViewById(R.id.error_text);

        contentItems = new ArrayList<>();
        adapter = new ContentAdapter(contentItems);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        scraper = new WebScraper();
        executorService = Executors.newSingleThreadExecutor();

        loadContent();
    }

    private void loadContent() {
        showLoading();
        scraper.scrapeHome(this);
    }

    @Override
    public void onSuccess(List<ContentItem> items) {
        if (isAdded()) {
            contentItems.clear();
            contentItems.addAll(items);
            adapter.notifyDataSetChanged();
            showContent();
        }
    }

    @Override
    public void onFailure(Exception e) {
        if (isAdded()) {
            showError();
        }
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }

    private void showContent() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.GONE);
    }

    private void showError() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (scraper != null) {
            scraper.shutdown();
        }
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
