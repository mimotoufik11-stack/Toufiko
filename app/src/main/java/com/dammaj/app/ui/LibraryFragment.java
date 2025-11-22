package com.dammaj.app.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dammaj.app.R;
import com.dammaj.app.adapter.ContentAdapter;
import com.dammaj.app.model.ContentItem;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class LibraryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ChipGroup chipGroup;
    private ContentAdapter adapter;
    private List<ContentItem> allItems;
    private List<ContentItem> filteredItems;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        chipGroup = view.findViewById(R.id.chip_group_filter);

        allItems = new ArrayList<>();
        filteredItems = new ArrayList<>();
        adapter = new ContentAdapter(filteredItems);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());

        setupFilterChips();
        loadCachedContent();
    }

    private void setupFilterChips() {
        String[] categories = {"All", "Lessons", "Articles", "Books", "Fatwas", "Scholars"};

        for (String category : categories) {
            com.google.android.material.chip.Chip chip = new com.google.android.material.chip.Chip(requireContext());
            chip.setText(category);
            chip.setCheckable(true);

            if ("All".equals(category)) {
                chip.setChecked(true);
            }

            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    filterByCategory(category);
                }
            });

            chipGroup.addView(chip);
        }
    }

    private void filterByCategory(String category) {
        filteredItems.clear();

        if ("All".equals(category)) {
            filteredItems.addAll(allItems);
        } else {
            for (ContentItem item : allItems) {
                if (category.equals(item.getType())) {
                    filteredItems.add(item);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void loadCachedContent() {
        String cachedContent = preferences.getString("cached_library_content", "");
        if (!cachedContent.isEmpty()) {
            filterByCategory("All");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar();
        }
    }
}
