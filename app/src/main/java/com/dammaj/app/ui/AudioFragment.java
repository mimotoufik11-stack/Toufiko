package com.dammaj.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dammaj.app.R;
import com.dammaj.app.adapter.AudioAdapter;
import com.dammaj.app.model.AudioItem;

import java.util.ArrayList;
import java.util.List;

public class AudioFragment extends Fragment {
    private RecyclerView recyclerView;
    private AudioAdapter adapter;
    private List<AudioItem> audioItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);

        audioItems = new ArrayList<>();
        adapter = new AudioAdapter(audioItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        loadAudioContent();
    }

    private void loadAudioContent() {
        audioItems.add(new AudioItem(
                "1",
                "Islamic Lecture 1",
                "https://example.com/audio1.mp3",
                "45:30",
                "Scholar Name",
                "2024-01-15"
        ));

        audioItems.add(new AudioItem(
                "2",
                "Islamic Lecture 2",
                "https://example.com/audio2.mp3",
                "38:45",
                "Scholar Name",
                "2024-01-16"
        ));

        audioItems.add(new AudioItem(
                "3",
                "Islamic Lecture 3",
                "https://example.com/audio3.mp3",
                "52:15",
                "Scholar Name",
                "2024-01-17"
        ));

        adapter.notifyDataSetChanged();
    }
}
