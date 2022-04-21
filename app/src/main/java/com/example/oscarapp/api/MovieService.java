package com.example.oscarapp.api;


import com.example.oscarapp.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("filme")
    Call<List<Movie>> getAllMovies();
}
