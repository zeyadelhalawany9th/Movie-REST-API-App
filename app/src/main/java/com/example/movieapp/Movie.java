package com.example.movieapp;

public class Movie {

    String name, imageURL;

    public Movie(String name, String imageURL)
    {
        this.name = name;
        this.imageURL = imageURL;
    }

    public Movie()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
}
