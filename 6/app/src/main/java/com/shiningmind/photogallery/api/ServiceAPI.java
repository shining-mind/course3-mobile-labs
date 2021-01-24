package com.shiningmind.photogallery.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shiningmind.photogallery.model.PhotosResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceAPI {

    private final String BASE_URL = "https://api.flickr.com/";
    private Retrofit retrofit = null;
    private FlickrPhotosAPI api;
    private String apiKey;

    public ServiceAPI(String apiKey) {
        this.apiKey = apiKey;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
        api = retrofit.create(FlickrPhotosAPI.class);
    }

    public Call<PhotosResponse> getRecent() {
        return api.getRecent(apiKey);
    }

    public Call<PhotosResponse> search(String text) {
        return api.getSearchPhotos(apiKey, text);
    }
}
