package com.example.assignment_10.model;

public class Movie {
    private String title, genre, posterResource;
    private Integer year;

    //Constructors
    public Movie() //empty constructor;
    {

    }
    public Movie(String title, Integer year, String genre, String posterResource)
    {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.posterResource = posterResource;
    }

    //getters
    public String getTitle()
    {
        return this.title;
    }
    public String getGenre()
    {
        return this.genre;
    }
    public Integer getYear()
    {
        return this.year;
    }
    public String getPosterResource()
    {
        return this.posterResource;
    }


    //setters
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    public void setYear(Integer year)
    {
        this.year = year;
    }
    public void setPosterResource(String posterResource)
    {
        this.posterResource = posterResource;
    }
}

