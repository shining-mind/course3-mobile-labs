package com.shiningmind.photogallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shiningmind.photogallery.api.ServiceAPI;
import com.shiningmind.photogallery.model.Photo;
import com.shiningmind.photogallery.model.PhotosResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoGallery extends AppCompatActivity {

    private ServiceAPI service;
    private RecyclerView recyclerView;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        List<Photo> photos = new ArrayList<Photo>();
        service = new ServiceAPI(getString(R.string.api_key));
        tvMessage = findViewById(R.id.tvMessage);
        recyclerView = findViewById(R.id.rvImageList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new PhotosAdapter(photos));
        service.getRecent().enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                PhotosResponse photosResponse = response.body();
                if (photosResponse != null && photosResponse.getStat().equals("ok")) {
                    photos.addAll(photosResponse.getPhotos().getPhoto());
                    updateImageList();
                } else {
                    showErrorMessage(getString(R.string.api_failed) + photosResponse.getStat());
                }
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                showErrorMessage(getString(R.string.api_failed));
                t.printStackTrace();
            }
        });

    }

    private void updateImageList()
    {
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.getAdapter().notifyDataSetChanged();
        tvMessage.setVisibility(View.GONE);
    }

    private void showErrorMessage(String message)
    {
        recyclerView.setVisibility(View.GONE);
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(message);
    }
}