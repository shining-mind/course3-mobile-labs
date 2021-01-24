package com.shiningmind.photogallery.model;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseUnitTest {
    @Test
    public void responseIsParsedCorrectly()
    {
        Gson gson = new Gson();
        Response response = gson.fromJson("{" +
                "  \"photos\": {" +
                "    \"page\": 1," +
                "    \"pages\": \"334\"," +
                "    \"perpage\": 3," +
                "    \"total\": \"1000\"," +
                "    \"photo\": [" +
                "      {" +
                "        \"id\": \"50868931668\"," +
                "        \"owner\": \"156275984@N03\"," +
                "        \"secret\": \"0637a7437c\"," +
                "        \"server\": \"65535\"," +
                "        \"farm\": 66," +
                "        \"title\": \"IMG_20190817_113626_739\"," +
                "        \"ispublic\": 1," +
                "        \"isfriend\": 1," +
                "        \"isfamily\": 0" +
                "      }," +
                "      {" +
                "        \"id\": \"50869741487\"," +
                "        \"owner\": \"191883606@N04\"," +
                "        \"secret\": \"fba2f48b62\"," +
                "        \"server\": \"65535\"," +
                "        \"farm\": 66," +
                "        \"title\": \"\"," +
                "        \"ispublic\": 1," +
                "        \"isfriend\": 0," +
                "        \"isfamily\": 0" +
                "      }," +
                "      {" +
                "        \"id\": \"50869741537\"," +
                "        \"owner\": \"58640909@N04\"," +
                "        \"secret\": \"c7b3783062\"," +
                "        \"server\": \"65535\"," +
                "        \"farm\": 66," +
                "        \"title\": \"Energy Gels\"," +
                "        \"ispublic\": 1," +
                "        \"isfriend\": 0," +
                "        \"isfamily\": 0" +
                "      }" +
                "    ]" +
                "  }," +
                "  \"stat\": \"ok\"" +
                "}", Response.class);
        assertEquals(response.getStat(), "ok");
        assertEquals(response.getPhotos().getPhoto().size(), 3);
        Photo photo1 = response.getPhotos().getPhoto().get(0);
        assertEquals(photo1.getUrl("w"), "https://live.staticflickr.com/65535/50868931668_0637a7437c_w.jpg");
        assertEquals(photo1.getIsPublic(), 1);
        assertEquals(photo1.getIsFamily(), 0);
        assertEquals(photo1.getIsFriend(), 1);
        Photo photo2 = response.getPhotos().getPhoto().get(1);
        assertEquals(photo2.getUrl("w"), "https://live.staticflickr.com/65535/50869741487_fba2f48b62_w.jpg");
        assertEquals(photo2.getIsPublic(), 1);
        assertEquals(photo2.getIsFamily(), 0);
        assertEquals(photo2.getIsFriend(), 0);
        Photo photo3 = response.getPhotos().getPhoto().get(2);
        assertEquals(photo3.getUrl("w"), "https://live.staticflickr.com/65535/50869741537_c7b3783062_w.jpg");
        assertEquals(photo3.getIsPublic(), 1);
        assertEquals(photo3.getIsFamily(), 0);
        assertEquals(photo3.getIsFriend(), 0);
    }
}
