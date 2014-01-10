package com.ericski.api500px;

/*
 * Defines the image sizes and the programmatic keys used to retrieve them 
 */
public enum ImageSize
{

    //OriginalImage (0),
    SmallThumbnail(1),
    MediumThumbnail(2),
    LargeThumbnail(3),
    Image(4);
	//LargeImage (5);

    private int sizeKey;

    ImageSize(int key)
    {
        sizeKey = key;
    }

    public int getSizeKey()
    {
        return sizeKey;
    }
}
