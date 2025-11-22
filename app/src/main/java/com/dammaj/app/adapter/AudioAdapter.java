package com.dammaj.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dammaj.app.R;
import com.dammaj.app.model.AudioItem;

import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {

    private List<AudioItem> audioList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(AudioItem item);
        void onPlayButtonClicked(AudioItem item);
    }

    public AudioAdapter(List<AudioItem> audioList, OnItemClickListener onItemClickListener) {
        this.audioList = audioList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audio, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        AudioItem item = audioList.get(position);
        
        holder.titleText.setText(item.getTitle());
        holder.authorText.setText(item.getAuthor() != null ? "الشيخ: " + item.getAuthor() : "");
        holder.durationText.setText(item.getDuration() != null ? item.getDuration() : "");
        
        // Load thumbnail image with Glide
        RequestOptions options = new RequestOptions()
                .transform(new CircleCrop())
                .placeholder(R.drawable.ic_headphone)
                .error(R.drawable.ic_headphone)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        
        // Note: In a real implementation, you would load the actual audio thumbnail
        // For now, we'll use a placeholder or the default headphone icon
        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(item.getImageUrl())
                    .apply(options)
                    .into(holder.thumbnailImage);
        } else {
            holder.thumbnailImage.setImageResource(R.drawable.ic_headphone);
        }
        
        // Set click listeners
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(item);
            }
        });
        
        holder.playButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onPlayButtonClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    static class AudioViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnailImage;
        ImageButton playButton;
        TextView titleText;
        TextView authorText;
        TextView durationText;
        TextView durationBadge;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            playButton = itemView.findViewById(R.id.play_button);
            titleText = itemView.findViewById(R.id.title_text);
            authorText = itemView.findViewById(R.id.author_text);
            durationText = itemView.findViewById(R.id.duration_text);
            durationBadge = itemView.findViewById(R.id.duration_badge);
            
            // Find thumbnail image - we'll use the play button's parent for now
            // In a real implementation, you would have a separate ImageView for the thumbnail
            thumbnailImage = itemView.findViewById(R.id.play_button);
        }
    }
}