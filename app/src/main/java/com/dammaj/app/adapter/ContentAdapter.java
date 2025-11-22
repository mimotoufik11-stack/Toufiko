package com.dammaj.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dammaj.app.R;
import com.dammaj.app.model.ContentItem;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private List<ContentItem> contentList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ContentItem item);
    }

    public ContentAdapter(List<ContentItem> contentList, OnItemClickListener onItemClickListener) {
        this.contentList = contentList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_card, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        ContentItem item = contentList.get(position);
        
        holder.titleText.setText(item.getTitle());
        holder.authorText.setText(item.getAuthor() != null ? "بقلم: " + item.getAuthor() : "");
        holder.dateText.setText(item.getDateAdded() != null ? item.getDateAdded() : "");
        
        // Load image with Glide
        RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(), new RoundedCorners(12))
                .placeholder(R.drawable.ic_article)
                .error(R.drawable.ic_article)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        
        Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .apply(options)
                .into(holder.imageView);
        
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleText;
        TextView authorText;
        TextView dateText;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            titleText = itemView.findViewById(R.id.title_text);
            authorText = itemView.findViewById(R.id.author_text);
            dateText = itemView.findViewById(R.id.date_text);
        }
    }
}