package com.shiningmind.photogallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.shiningmind.photogallery.api.ServiceAPI;
import com.shiningmind.photogallery.db.PhotoDAO;
import com.shiningmind.photogallery.db.PhotoDatabase;
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
    private PhotoDatabase db;
    private List<Photo> photos = new ArrayList<Photo>();
    private State state = new State();
    private Callback<PhotosResponse> apiCallback = new Callback<PhotosResponse>() {
        @Override
        public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
            PhotosResponse photosResponse = response.body();
            if (photosResponse != null && photosResponse.getStat().equals("ok")) {
                updateImageList(photosResponse.getPhotos().getPhoto());
            } else {
                showMessage(getString(R.string.api_failed));
            }
        }

        @Override
        public void onFailure(Call<PhotosResponse> call, Throwable t) {
            showMessage(getString(R.string.api_failed));
            t.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        db = PhotoDatabase.getDatabase(getApplicationContext());
        service = new ServiceAPI(getString(R.string.api_key));
        tvMessage = findViewById(R.id.tvMessage);
        recyclerView = findViewById(R.id.rvImageList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new PhotosAdapter(photos, new ImageClickListener()));
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            state.setCurrentMenuItemId(R.id.searchImages);
            loadSearchImages(query);
        } else {
            state.setCurrentMenuItemId(R.id.recentImages);
            loadRecentImages();
        }
    }

    public void loadRecentImages() {
        service.getRecent().enqueue(apiCallback);
        showMessage(getString(R.string.recent_images));
    }

    public void loadSearchImages(String text) {
        service.search(text).enqueue(apiCallback);
        showMessage(String.format("%s: %s", getString(R.string.search_result), text));
    }

    public void loadLocalImages() {
        PhotoDatabase db = PhotoDatabase.getDatabase(getApplicationContext());
        PhotoDAO dao = db.photoDao();
        PhotoDatabase.databaseWriteExecutor.execute(() -> {
            List<Photo> localPhotos = dao.getAll();
            runOnUiThread(() -> {
                if (localPhotos.size() == 0) {
                    showMessage(getString(R.string.no_local_images));
                } else {
                    updateImageList(localPhotos);
                }
                showMessage(getString(R.string.local_images));
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.searchImages).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        int prevMenuItemId = state.getCurrentMenuItemId();
        state.setCurrentMenuItemId(menuItemId);
        switch (menuItemId) {
            case R.id.localImages:
                if (prevMenuItemId != menuItemId) {
                    loadLocalImages();
                }
                return true;
            case R.id.recentImages:
                if (prevMenuItemId != menuItemId) {
                    loadRecentImages();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateImageList(List<Photo> newPhotos)
    {
        photos.clear();
        photos.addAll(newPhotos);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void showMessage(String message)
    {
        tvMessage.setText(message);
    }

    public class ImageClickListener {
        public void onImageClick(Photo photo) {
            PhotoDatabase.databaseWriteExecutor.execute(() -> {
                if (state.getCurrentMenuItemId() == R.id.localImages) {
                    db.photoDao().delete(photo);
                    photos.remove(photo);
                    runOnUiThread(() -> {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    });
                } else {
                    db.photoDao().insert(photo);
                    runOnUiThread(() -> {
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.image_saved), Toast.LENGTH_SHORT);
                        toast.show();
                    });
                }
            });
        }
    }
}