package com.fialkowski.api500px;

import com.ericski.api500px.Photo;
import com.ericski.api500px.PhotoResponse;
import com.ericski.api500px.Api500pxPhotoUrlBuilder;
import java.awt.image.BufferedImage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Ignore;
import org.junit.Test;

public class PhotoUrlTest {

    @Test
    public void testSimple() {
        PhotoResponse pr = new Api500pxPhotoUrlBuilder(1).largeThumbnail().getResponse();
        assertNotNull("No PhotoResponse", pr);
        Photo p = pr.getPhoto();
        assertNotNull("No photo found", p);
        assertEquals(1, p.getId());
    }

    @Test
    public void testNoImage() {
        PhotoResponse pr = new Api500pxPhotoUrlBuilder(101010000).largeThumbnail().getResponse();
        assertNotNull("No PhotoResponse", pr);
        Photo p = pr.getPhoto();
        assertNull("Found a photo where one wasn't expected", p);
    }
	
		
	@Test
	@Ignore
	// this image has issues decoding (cmyk?)
	public void getPhoto355711()
	{
		int imageId = 355711;
		PhotoResponse pr = new Api500pxPhotoUrlBuilder(imageId).smallThumbnail().getResponse();
		assertNotNull("No response", pr);
		Photo p = pr.getPhoto();
		assertNotNull("No photo", p);
		assertNotNull("No image url", p.getImageUrl());
		BufferedImage image = p.getImage();
		assertNotNull("No image", image);
	}
}
