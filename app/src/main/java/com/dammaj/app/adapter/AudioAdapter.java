package com.dammaj.app.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dammaj.app.R;
import com.dammaj.app.model.AudioItem;

import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {
    private final List<AudioItem> items;

    public AudioAdapter(List<AudioItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_audio, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        AudioItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class AudioViewHolder extends RecyclerView.ViewHolder {
        private final ImageView audioImage;
        private final TextView audioTitle;
        private final TextView audioAuthor;
        private final TextView audioDuration;
        private final ImageButton playButton;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            audioImage = itemView.findViewById(R.id.audio_image);
            audioTitle = itemView.findViewById(R.id.audio_title);
            audioAuthor = itemView.findViewById(R.id.audio_author);
            audioDuration = itemView.findViewById(R.id.audio_duration);
            playButton = itemView.findViewById(R.id.play_button);
        }

        public void bind(AudioItem item) {
            audioTitle.setText(item.getTitle());
            audioAuthor.setText(item.getAuthor());
            audioDuration.setText(formatDuration(item.getDuration()));

            Glide.with(itemView.getContext())
                    .load(R.drawable.ic_headphone)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(audioImage);

            playButton.setOnClickListener(v -> playAudio(item));
        }

        private void playAudio(AudioItem item) {
            if (item.getAudioUrl() != null && !item.getAudioUrl().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.getAudioUrl()));
                intent.setType("audio/*");
                itemView.getContext().startActivity(Intent.createChooser(intent, "Play audio"));
            }
        }

        private String formatDuration(String duration) {
            if (duration == null || duration.isEmpty()) {
                return "0:00";
            }
            return duration;
        }
    }
}
