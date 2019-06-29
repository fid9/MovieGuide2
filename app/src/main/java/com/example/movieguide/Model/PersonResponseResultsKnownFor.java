package com.example.movieguide.Model;

import java.util.List;
@SuppressWarnings("unused")

class PersonResponseResultsKnownFor {
    private float vote_average;
    private int vote_count;
    private int id;
    private boolean video;
    private String media_type;
    private String title;
    private double popularity;
    private String poster_path;
    private String original_language;
    private String original_title;

    private List<Integer> genre_ids;
    private String backdoor_path;
    private boolean adult;
    private String overview;
    private String release_date;

    public PersonResponseResultsKnownFor(){}

    public PersonResponseResultsKnownFor(float vote_average, int vote_count, int id, boolean video, String media_type, String title, double popularity, String poster_path, String original_language, String original_title, List<Integer> genre_ids, String backdoor_path, boolean adult, String overview, String release_date) {
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.id = id;
        this.video = video;
        this.media_type = media_type;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.backdoor_path = backdoor_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path()
    {
        //create a base url for this poster
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + poster_path;

    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdoor_path() {
        return backdoor_path;
    }

    public void setBackdoor_path(String backdoor_path) {
        this.backdoor_path = backdoor_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

}
