package com.example.oscarapp.api;

import com.example.oscarapp.models.Choice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ChoiceService {
    @POST("choices/")
    Call<Choice> sendChoice(@Header ("login") String login, @Header("token") String token, @Body Choice choice);

    @GET("choices/")
    Call<Choice> retrieveChoice(@Header ("login") String login, @Header("token") String token);
}
