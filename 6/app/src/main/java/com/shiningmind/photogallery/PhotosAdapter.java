package com.shiningmind.photogallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shiningmind.photogallery.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoHolder> {

    private List<Photo> photos;

    public static class PhotoHolder extends RecyclerView.ViewHolder {
        public final ImageView ivPreview;
        public final TextView tvTitle;

        public PhotoHolder(View view) {
            super(view);
            ivPreview = view.findViewById(R.id.ivPreview);
            tvTitle = view.findViewById(R.id.tvTitle);
        }
    }

    public PhotosAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotosAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image, parent, false);
        PhotoHolder vh = new PhotoHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Photo item = this.photos.get(position);
        if (item != null) {
            Picasso.get()
                    .load(item.getUrl("s"))
                    .into(holder.ivPreview);
            holder.tvTitle.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
