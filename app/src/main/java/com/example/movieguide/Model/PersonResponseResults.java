package com.example.movieguide.Model;

import java.util.List;
@SuppressWarnings("unused")

public class PersonResponseResults {
    private double popularity;
    private int id;
    private String profile_path;
    private String name;

    private List<PersonResponseResultsKnownFor> results;

    private boolean adult;

    public PersonResponseResults(){}

    public PersonResponseResults(double popularity, int id, String profile_path, String name, List<PersonResponseResultsKnownFor> results, boolean adult) {
        this.popularity = popularity;
        this.id = id;
        this.profile_path = profile_path;
        this.name = name;
        this.results = results;
        this.adult = adult;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile_path()
    {
        //create a base url for this poster
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + profile_path;

    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonResponseResultsKnownFor> getResults() {
        return results;
    }

    public void setResults(List<PersonResponseResultsKnownFor> results) {
        this.results = results;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

}
