package com.example.oscarapp.api;

import com.example.oscarapp.models.Token;
import com.example.oscarapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("auth/")
    Call<Token> getToken(@Body User user);
}
