package com.example.oscarapp.models;

public class Choice {
    private int remote_director_id, remote_movie_id;
    private String remote_director_name, remote_movie_name;

    public Choice(int remote_director_id, int remote_movie_id, String remote_director_name, String remote_movie_name) {
        this.remote_director_id = remote_director_id;
        this.remote_movie_id = remote_movie_id;
        this.remote_director_name = remote_director_name;
        this.remote_movie_name = remote_movie_name;
    }

    public int getRemote_director_id() {
        return remote_director_id;
    }

    public void setRemote_director_id(int remote_director_id) {
        this.remote_director_id = remote_director_id;
    }

    public int getRemote_movie_id() {
        return remote_movie_id;
    }

    public void setRemote_movie_id(int remote_movie_id) {
        this.remote_movie_id = remote_movie_id;
    }

    public String getRemote_director_name() {
        return remote_director_name;
    }

    public void setRemote_director_name(String remote_director_name) {
        this.remote_director_name = remote_director_name;
    }

    public String getRemote_movie_name() {
        return remote_movie_name;
    }

    public void setRemote_movie_name(String remote_movie_name) {
        this.remote_movie_name = remote_movie_name;
    }
}
