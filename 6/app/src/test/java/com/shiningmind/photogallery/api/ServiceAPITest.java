package com.shiningmind.photogallery.api;



import com.shiningmind.photogallery.model.PhotosResponse;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceAPITest {
    @Test
    public void testGetRecentReturnsStatFailWhenWrongApiKeyIsProvided()
    {
        ServiceAPI service = new ServiceAPI("");
        PhotosResponse photosResponse = service.getRecent();
        assertEquals("fail", photosResponse.getStat());
    }
}
