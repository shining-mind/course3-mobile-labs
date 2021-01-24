package com.shiningmind.photogallery.api;

import com.shiningmind.photogallery.model.PhotosResponse;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ServiceAPITest {
    @Test
    public void testGetRecentReturnsStatFailWhenWrongApiKeyIsProvided() throws IOException {
        ServiceAPI service = new ServiceAPI("");
        PhotosResponse photosResponse = service.getRecent().execute().body();
        assertEquals("fail", photosResponse.getStat());
    }
}
