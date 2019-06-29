package com.example.movieguide.Model;
@SuppressWarnings("unused")

public class PersonImageProfiles
{
    private String iso_639_1;
    private Integer width;
    private Integer  height;
    private Integer vote_count;
    private Double vote_average;
    private String file_path;
    private double aspect_ratio;

    public PersonImageProfiles() {
    }

    public PersonImageProfiles(String iso_639_1, int width, int height, int vote_count, double vote_average, String file_path, double aspect_ratio) {
        this.iso_639_1 = iso_639_1;
        this.width = width;
        this.height = height;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.file_path = file_path;
        this.aspect_ratio = aspect_ratio;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getFile_path() {
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        return baseUrl + file_path;
    }

    public void setFile_path(String file_path) {

        this.file_path = file_path;
    }

    public double getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(double aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }
}
