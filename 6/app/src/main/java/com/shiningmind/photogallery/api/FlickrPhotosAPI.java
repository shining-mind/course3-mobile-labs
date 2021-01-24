package com.shiningmind.photogallery.api;

import com.shiningmind.photogallery.model.PhotosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrPhotosAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&format=json&nojsoncallback=1&per_page=99")
    Call<PhotosResponse> getRecent(@Query("api_key") String apiKey);
    @GET("services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&per_page=99")
    Call<PhotosResponse> getSearchPhotos(@Query("api_key") String apiKey, @Query("text") String text);
}

