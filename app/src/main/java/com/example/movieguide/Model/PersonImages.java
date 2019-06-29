package com.example.movieguide.Model;

import java.util.List;
@SuppressWarnings("unused")

public class PersonImages
{
    private List<PersonImageProfiles> profiles;

    private Integer id;

    public PersonImages() {
    }

    public PersonImages(List<PersonImageProfiles> profiles, Integer id) {
        this.profiles = profiles;
        this.id = id;
    }

    public List<PersonImageProfiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<PersonImageProfiles> profiles) {
        this.profiles = profiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
