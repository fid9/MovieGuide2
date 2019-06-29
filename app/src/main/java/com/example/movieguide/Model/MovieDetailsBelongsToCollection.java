package com.example.movieguide.Model;

@SuppressWarnings("unused")

public class MovieDetailsBelongsToCollection
{
    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public MovieDetailsBelongsToCollection() {
    }

    public MovieDetailsBelongsToCollection(int id, String name, String poster_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {

        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
