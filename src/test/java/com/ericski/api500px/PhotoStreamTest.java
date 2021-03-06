package com.ericski.api500px;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

public class PhotoStreamTest
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
    public void testFreshYesterdayPhotoCounts()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).freshYesterdayPhotos().largeThumbnails().resultsPerPage(100).getResponse();
        if (pr != null)
        {
            int items = pr.getTotal_items();
            assertTrue("Nothing returned", items > 0);
            System.out.printf("Found %d items%n", items);
        }
        else
        {
            fail("No response");
        }
    }

    @Test
    public void testSkipCategory()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).freshYesterdayPhotos().largeThumbnails().resultsPerPage(100).exclude(Category.Nature).getResponse();
        if (pr != null)
        {
            int items = pr.getTotal_items();
            System.out.printf("Found %d items%n", items);

            for (Photo p : pr.getPhotos())
            {
                if (p.getCategory().equals(Category.Nature))
                {
                    fail("Found an image in the exclude category");
                }
            }
        }
        else
        {
            fail("No response");
        }
    }

    @Test
    public void testOnlyCategory()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).freshYesterdayPhotos().largeThumbnails().resultsPerPage(100).only(Category.Nature).getResponse();
        if (pr != null)
        {
            int items = pr.getTotal_items();
            System.out.printf("Found %d items%n", items);

            for (Photo p : pr.getPhotos())
            {
                if (!p.getCategory().equals(Category.Nature))
                {
                    fail("Found an image not in the only category");
                }
            }
        }
        else
        {
            fail("No response");
        }
    }

    @Test
    public void dumpPhotos()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).freshYesterdayPhotos().largeThumbnails().resultsPerPage(1).getResponse();
        assertNotNull("No repsonse", pr);
        ArrayList<Photo> photos = pr.getPhotos();
        assertNotNull("Photos null", photos);
        assertTrue("No photos", photos.size() > 0);        
    }

    @Test
    public void getUserPhotos()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).userPhotos("ericski").sort(Sort.times_viewed).smallThumbnails().getResponse();
        assertNotNull(pr);
        int items = pr.getTotal_items();
        int pages = pr.getTotal_pages();
        System.out.printf("Found %d items in %d pages%n", items, pages);
        int total_views = 0;
        int rank = 0;
        do
        {
            for (Photo p : pr.getPhotos())
            {
                assertNotNull(p.getUser());
                assertEquals("ericski", p.getUser().getUsername());
                System.out.print("Rank: " + ++rank);
                System.out.print(",");
                System.out.print("ID: " + p.getId());
                System.out.print(",");
                System.out.print("Name: " + p.getName());
                System.out.print(",");
//				System.out.print("URL: " + p.getImageUrl());
//				System.out.print("\t");
//				System.out.print("Votes: " + p.getVotesCount());
//				System.out.print("\t");
//				System.out.print("Favorites: " + p.getFavoritesCount());
//				System.out.print("\t");
//				System.out.print("Rating: " + p.getRating());
//				System.out.print("\t");
                System.out.print("Views: " + p.getTimesViewed());
                System.out.println();
                total_views += p.getTimesViewed();
//				System.out.println("Comments: " + p.getCommentsCount());
            }
        } while ((pr = pr.getNextPage()) != null);
        System.out.println("Total views: " + total_views);
    }

    @Test
    public void getAllUserPhotos()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).userPhotos("ericski").sort(Sort.times_viewed).smallThumbnails().getResponse();
        assertNotNull(pr);
        pr.getAllPhotos();
        int items = pr.getTotal_items();
        int pages = pr.getTotal_pages();
        System.out.printf("Found %d items in %d pages%n", items, pages);
        int total_views = 0;
        int rank = 0;
        for (Photo p : pr.getPhotos())
        {
            assertNotNull(p.getUser());
            assertEquals("ericski", p.getUser().getUsername());
            System.out.print("Rank: " + ++rank);
            System.out.print(",");
            System.out.print("ID: " + p.getId());
            System.out.print(",");
            System.out.print("Name: " + p.getName());
            System.out.print(",");
//				System.out.print("URL: " + p.getImageUrl());
//				System.out.print("\t");
//				System.out.print("Votes: " + p.getVotesCount());
//				System.out.print("\t");
//				System.out.print("Favorites: " + p.getFavoritesCount());
//				System.out.print("\t");
//				System.out.print("Rating: " + p.getRating());
//				System.out.print("\t");
            System.out.print("Views: " + p.getTimesViewed());
            System.out.println();
            total_views += p.getTimesViewed();
//				System.out.println("Comments: " + p.getCommentsCount());
        }
        System.out.println("Total views: " + total_views);
    }

    @Test
    public void getUserPhotosSorted()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).userPhotos("ericski").sort(Sort.times_viewed).getResponse();
        assertNotNull(pr);
        int items = pr.getTotal_items();
        System.out.printf("getUserPhotosSorted - Found %d items%n", items);

        for (Photo p : pr.getPhotos())
        {
            assertNotNull(p.getUser());
            assertEquals("ericski", p.getUser().getUsername());
        }
    }
    
    @Test
    public void requestWithTags()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).popularPhotos().resultsPerPage(2).withTags().getResponse();
        assertNotNull(pr);        
    }
    
    @Test
    public void requestWithState()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).popularPhotos().resultsPerPage(2).withStates().getResponse();
        assertNotNull(pr);        
    }
    
    @Test
    public void requestWithStore()
    {
        FeatureResponse pr = new Api500pxStreamBuilder(consumerKey).popularPhotos().resultsPerPage(2).withStore().getResponse();
        assertNotNull(pr);        
    }
}
