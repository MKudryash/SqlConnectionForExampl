package com.example.connectionsqlsever.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    int ID;
    String Title;
    String Genre;
    String Country;
    String Image;

    public Movie(int ID, String title, String genre, String country, String image, int yearPremiere) {
        this.ID = ID;
        Title = title;
        Genre = genre;
        Country = country;
        Image = image;
        YearPremiere = yearPremiere;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getYearPremiere() {
        return YearPremiere;
    }

    public void setYearPremiere(int yearPremiere) {
        YearPremiere = yearPremiere;
    }

    int YearPremiere;


    protected Movie(Parcel in) {
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
