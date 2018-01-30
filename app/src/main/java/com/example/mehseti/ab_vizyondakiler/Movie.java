package com.example.mehseti.ab_vizyondakiler;

import android.net.Uri;

import java.io.Serializable;

public class Movie implements Serializable {
    int id;
    String name;
    String type;
    String overview;
    String imageCode;
    String originalLanguage;
    double voteAverage;
    int imageCodeDrawable;


    protected Movie(int id, String name, String overview, String imageCode,String originalLanguage,double voteAverage)
    {
        this.id = id;
        this.name = name;
      //  this.type = type;
        this.overview = overview;
        this.imageCode = imageCode;
        this.originalLanguage = originalLanguage;
        this.voteAverage = voteAverage;


    }

    protected Movie(int id, String name, String overview, int imageCodeDrawable,String originalLanguage,double voteAverage)
    {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.imageCodeDrawable = imageCodeDrawable;
        this.originalLanguage = originalLanguage;
        this.voteAverage = voteAverage;


    }


    public String getImageCode() {
        return imageCode;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }


    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public int getImageCodeDrawable() {
        return imageCodeDrawable;
    }
}
