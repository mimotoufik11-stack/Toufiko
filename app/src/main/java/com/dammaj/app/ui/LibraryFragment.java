package com.dammaj.app.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment implements ContentAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private EditText searchEditText;
    private View filterButton;
    private ChipGroup chipGroup;
    private ProgressBar progressBar;
    private View errorLayout;
    private TextView errorMessage;
    private Button retryButton;
    
    private ContentAdapter contentAdapter;
    private WebScraper webScraper;
    private List<ContentItem> allContentList;
    private List<ContentItem> filteredContentList;
    private String selectedCategory = "all";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initViews(view);
        setupRecyclerView();
        setupChips();
        setupSearch();
        setupWebScraper();
        loadAllContent();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        searchEditText = view.findViewById(R.id.search_edit_text);
        filterButton = view.findViewById(R.id.filter_button);
        chipGroup = view.findViewById(R.id.chip_group);
        progressBar = view.findViewById(R.id.progress_bar);
        errorLayout = view.findViewById(R.id.error_layout);
        errorMessage = view.findViewById(R.id.error_message);
        retryButton = view.findViewById(R.id.retry_button);
        
        allContentList = new ArrayList<>();
        filteredContentList = new ArrayList<>();
        
        retryButton.setOnClickListener(v -> loadAllContent());
        filterButton.setOnClickListener(v -> toggleChipGroupVisibility());
        
        // Initially hide chips
        chipGroup.setVisibility(View.GONE);
    }

    private void setupRecyclerView() {
        contentAdapter = new ContentAdapter(filteredContentList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(contentAdapter);
    }

    private void setupChips() {
        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                int checkedId = checkedIds.get(0);
                
                if (checkedId == R.id.chip_all) {
                    selectedCategory = "all";
                } else if (checkedId == R.id.chip_books) {
                    selectedCategory = "books";
                } else if (checkedId == R.id.chip_articles) {
                    selectedCategory = "articles";
                } else if (checkedId == R.id.chip_lessons) {
                    selectedCategory = "lessons";
                } else if (checkedId == R.id.chip_fatwas) {
                    selectedCategory = "fatwas";
                } else if (checkedId == R.id.chip_scholars) {
                    selectedCategory = "scholars";
                }
                
                filterContent();
            }
        });
        
        // Select "All" by default
        chipGroup.check(R.id.chip_all);
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterContent();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupWebScraper() {
        webScraper = new WebScraper();
    }

    private void loadAllContent() {
        showLoading();
        webScraper.scrapeHome(new ScraperCallback() {
            @Override
            public void onSuccess(List<ContentItem> contentItems) {
                if (getContext() != null) {
                    getContext().runOnUiThread(() -> {
                        hideLoading();
                        allContentList.clear();
                        allContentList.addAll(contentItems);
                        filterContent();
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

    private void filterContent() {
        filteredContentList.clear();
        
        String searchQuery = searchEditText.getText().toString().toLowerCase().trim();
        
        for (ContentItem item : allContentList) {
            boolean matchesCategory = selectedCategory.equals("all") || 
                                    selectedCategory.equals(item.getCategory());
            
            boolean matchesSearch = searchQuery.isEmpty() ||
                                  item.getTitle().toLowerCase().contains(searchQuery) ||
                                  (item.getAuthor() != null && item.getAuthor().toLowerCase().contains(searchQuery)) ||
                                  (item.getDescription() != null && item.getDescription().toLowerCase().contains(searchQuery));
            
            if (matchesCategory && matchesSearch) {
                filteredContentList.add(item);
            }
        }
        
        contentAdapter.notifyDataSetChanged();
    }

    private void toggleChipGroupVisibility() {
        if (chipGroup.getVisibility() == View.VISIBLE) {
            chipGroup.setVisibility(View.GONE);
        } else {
            chipGroup.setVisibility(View.VISIBLE);
        }
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
        // Handle item click
        if (item.getLinkUrl() != null && !item.getLinkUrl().isEmpty()) {
            Toast.makeText(getContext(), "فتح: " + item.getTitle(), Toast.LENGTH_SHORT).show();
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