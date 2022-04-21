package com.example.oscarapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExternalConfig {
    public final Retrofit retrofit;

    public ExternalConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://wecodecorp.com.br/ufpr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MovieService getMovieService() {
        return this.retrofit.create(MovieService.class);
    }
}
