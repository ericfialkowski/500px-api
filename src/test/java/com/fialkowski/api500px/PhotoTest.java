/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fialkowski.api500px;

import com.ericski.api500px.Photo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eric
 */
public class PhotoTest {
    
    public PhotoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Photo.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Photo instance = new Photo();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
}
