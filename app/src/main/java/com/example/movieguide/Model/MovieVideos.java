package com.example.movieguide.Model;

import java.util.List;
@SuppressWarnings("unused")

public class MovieVideos {

    private Integer id;
    private List<MovieVideosResults> results;

    public MovieVideos() {
    }

    public MovieVideos(Integer id, List<MovieVideosResults> results) {
        this.id = id;
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieVideosResults> getResults() {
        return results;
    }

    public void setResults(List<MovieVideosResults> results) {
        this.results = results;
    }
}
