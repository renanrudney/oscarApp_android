package com.example.oscarapp.api;

import com.example.oscarapp.models.Director;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DirectorService {
    @GET("diretor")
    Call<List<Director>> getAllDirectors();
}
