package com.example.oscarapp.models;

public class Movie {
    private int id;
    private String name;
    private String gender;
    private String photo_url;

    public Movie(int id, String name, String gender, String photo_url) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.photo_url = photo_url;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
