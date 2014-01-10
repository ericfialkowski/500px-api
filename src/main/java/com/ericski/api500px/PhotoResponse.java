package com.ericski.api500px;

import java.util.ArrayList;

public class PhotoResponse
{

    Photo photo;
    ArrayList<Comment> comments;

    public Photo getPhoto()
    {
        return photo;
    }

    public void setPhoto(Photo photo)
    {
        this.photo = photo;
    }

    public ArrayList<Comment> getComments()
    {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments)
    {
        this.comments = comments;
    }

}
