package com.example.movieguide.Model;

import java.util.List;
@SuppressWarnings("unused")

public class MovieCredits    {

    private Integer id;
    private List<MovieCreditsCast> cast;
    private List<MovieCreditsCrew> crew;

    public MovieCredits() {
    }

    public MovieCredits(Integer id, List<MovieCreditsCast> cast, List<MovieCreditsCrew> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieCreditsCast> getCast() {
        return cast;
    }

    public void setCast(List<MovieCreditsCast> cast) {
        this.cast = cast;
    }

    public List<MovieCreditsCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<MovieCreditsCrew> crew) {
        this.crew = crew;
    }
}
