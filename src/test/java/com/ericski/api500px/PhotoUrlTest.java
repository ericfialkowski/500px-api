package com.ericski.api500px;

import java.awt.image.BufferedImage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

public class PhotoUrlTest
{

    private static String consumerKey;

    @BeforeClass
    public static void checkForconsumerKey()
    {
        consumerKey = ConsumerKeyFinder.getKey();
        if (consumerKey.isEmpty())
        {
            fail("Please define your consumer key");
        }
    }

    @Test
    public void testSimple()
    {
        PhotoResponse pr = new Api500pxPhotoUrlBuilder(1, consumerKey).largeThumbnail().getResponse();
        assertNotNull("No PhotoResponse", pr);
        Photo p = pr.getPhoto();
        assertNotNull("No photo found", p);
        assertEquals(1, p.getId());
    }

    @Test
    public void testNoImage()
    {
        PhotoResponse pr = new Api500pxPhotoUrlBuilder(101010000, consumerKey).largeThumbnail().getResponse();
        assertNotNull("No PhotoResponse", pr);
        Photo p = pr.getPhoto();
        assertNull("Found a photo where one wasn't expected", p);
    }

    @Test
    // this image had issues decoding (cmyk?)
    public void getPhoto355711()
    {
        int imageId = 355711;
        PhotoResponse pr = new Api500pxPhotoUrlBuilder(imageId, consumerKey).smallThumbnail().getResponse();
        assertNotNull("No response", pr);
        Photo p = pr.getPhoto();
        assertNotNull("No photo", p);
        assertNotNull("No image url", p.getImageUrl());
        BufferedImage image = p.getImage();
        assertNotNull("No image", image);
    }
}
