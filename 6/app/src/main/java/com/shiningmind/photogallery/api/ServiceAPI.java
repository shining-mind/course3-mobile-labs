package com.shiningmind.photogallery.api;

import com.google.gson.Gson;
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
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        api = retrofit.create(FlickrPhotosAPI.class);
    }

    public PhotosResponse getRecent() {
        return safeCall(api.getRecent(apiKey));
    }

    public PhotosResponse search(String text) {
        return safeCall(api.getSearchPhotos(apiKey, text));
    }

    public <T> T safeCall(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
