package com.example.movieguide.Model;

import java.util.List;
@SuppressWarnings("unused")

public class MovieImages {

    private Integer id;
    private List<MovieImagesBackdropsAndPosters> movieImagesBackdropsAndPostersList;
    private List<MovieImagesBackdropsAndPosters> posters;

    public MovieImages() {
    }

    public MovieImages(Integer id, List<MovieImagesBackdropsAndPosters> movieImagesBackdropsAndPostersList, List<MovieImagesBackdropsAndPosters> posters) {
        this.id = id;
        this.movieImagesBackdropsAndPostersList = movieImagesBackdropsAndPostersList;
        this.posters = posters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieImagesBackdropsAndPosters> getMovieImagesBackdropsAndPostersList() {
        return movieImagesBackdropsAndPostersList;
    }

    public void setMovieImagesBackdropsAndPostersList(List<MovieImagesBackdropsAndPosters> movieImagesBackdropsAndPostersList) {
        this.movieImagesBackdropsAndPostersList = movieImagesBackdropsAndPostersList;
    }

    public List<MovieImagesBackdropsAndPosters> getPosters() {
        return posters;
    }

    public void setPosters(List<MovieImagesBackdropsAndPosters> posters) {
        this.posters = posters;
    }
}
