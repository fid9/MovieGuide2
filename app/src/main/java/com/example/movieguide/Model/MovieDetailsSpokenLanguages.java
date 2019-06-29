package com.example.movieguide.Model;

@SuppressWarnings("unused")
public class MovieDetailsSpokenLanguages {
    private String iso_639_1;
    private String name;

    public MovieDetailsSpokenLanguages() {
    }

    public MovieDetailsSpokenLanguages(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
