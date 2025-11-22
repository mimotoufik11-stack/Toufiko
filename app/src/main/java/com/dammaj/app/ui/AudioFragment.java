package com.dammaj.app.ui;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dammaj.app.R;
import com.dammaj.app.adapter.AudioAdapter;
import com.dammaj.app.data.ScraperCallback;
import com.dammaj.app.data.WebScraper;
import com.dammaj.app.model.AudioItem;

import java.util.ArrayList;
import java.util.List;

public class AudioFragment extends Fragment implements AudioAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private View errorLayout;
    private TextView errorMessage;
    private Button retryButton;
    private AudioAdapter audioAdapter;
    private WebScraper webScraper;
    private List<AudioItem> audioList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initViews(view);
        setupRecyclerView();
        setupWebScraper();
        loadAudioContent();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        errorLayout = view.findViewById(R.id.error_layout);
        errorMessage = view.findViewById(R.id.error_message);
        retryButton = view.findViewById(R.id.retry_button);
        
        audioList = new ArrayList<>();
        
        retryButton.setOnClickListener(v -> loadAudioContent());
    }

    private void setupRecyclerView() {
        audioAdapter = new AudioAdapter(audioList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(audioAdapter);
    }

    private void setupWebScraper() {
        webScraper = new WebScraper();
    }

    private void loadAudioContent() {
        showLoading();
        webScraper.scrapeAudio(new ScraperCallback() {
            @Override
            public void onSuccess(List<com.dammaj.app.model.ContentItem> contentItems) {
                // Not used in this fragment
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
            public void onAudioSuccess(List<AudioItem> audioItems) {
                if (getContext() != null) {
                    getContext().runOnUiThread(() -> {
                        hideLoading();
                        audioList.clear();
                        audioList.addAll(audioItems);
                        audioAdapter.notifyDataSetChanged();
                    });
                }
            }

            @Override
            public void onAudioFailure(Exception e) {
                if (getContext() != null) {
                    getContext().runOnUiThread(() -> {
                        hideLoading();
                        showError(e.getMessage() != null ? e.getMessage() : getString(R.string.error_loading));
                    });
                }
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
    public void onItemClick(AudioItem item) {
        // Handle audio item click - play audio
        if (item.getAudioUrl() != null && !item.getAudioUrl().isEmpty()) {
            Toast.makeText(getContext(), "تشغيل: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            
            // In a real implementation, you would:
            // 1. Initialize ExoPlayer
            // 2. Set the audio source
            // 3. Start playback
            // 4. Show playback controls
        }
    }

    @Override
    public void onPlayButtonClicked(AudioItem item) {
        // Handle play button click specifically
        onItemClick(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webScraper != null) {
            webScraper.shutdown();
        }
    }
}