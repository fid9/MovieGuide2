package com.example.movieguide.Model;

import java.util.List;

@SuppressWarnings("unused")

public class MovieDetails {

    private boolean adult;
    private String backdrop_path;
    private MovieDetailsBelongsToCollection belongs_to_collection;
    private Integer budget;
    private List<MovieDetailsGenres> genres;
    private String homepage;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String poster_path;
    private List<MovieDetailsProductionCompanies> production_companies;
    private List<MovieDetailsProductionCountries> production_countries;
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    private List<MovieDetailsSpokenLanguages> spoken_languages;
    private String status;
    private String title;
    private boolean video;
    private Double vote_average;
    private int vote_count;

    public MovieDetails() {
    }

    public MovieDetails(boolean adult, String backdrop_path, MovieDetailsBelongsToCollection belongs_to_collection, Integer budget, List<MovieDetailsGenres> genres, String homepage, int id, String original_language, String original_title, String overview, Double popularity, String poster_path, List<MovieDetailsProductionCompanies> production_companies, List<MovieDetailsProductionCountries> production_countries, String release_date, Integer revenue, Integer runtime, List<MovieDetailsSpokenLanguages> spoken_languages, String status, String title, boolean video, Double vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.belongs_to_collection = belongs_to_collection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spoken_languages = spoken_languages;
        this.status = status;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public MovieDetailsBelongsToCollection getMovieDetailsBelongsToCollection() {
        return belongs_to_collection;
    }

    public void setMovieDetailsBelongsToCollection(MovieDetailsBelongsToCollection belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<MovieDetailsGenres> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieDetailsGenres> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        String baseUrl = "https://image.tmdb.org/t/p/w500";

        return baseUrl + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<MovieDetailsProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<MovieDetailsProductionCompanies> production_companies) {
        this.production_companies = production_companies;
    }

    public List<MovieDetailsProductionCountries> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<MovieDetailsProductionCountries> production_countries) {
        this.production_countries = production_countries;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<MovieDetailsSpokenLanguages> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<MovieDetailsSpokenLanguages> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
