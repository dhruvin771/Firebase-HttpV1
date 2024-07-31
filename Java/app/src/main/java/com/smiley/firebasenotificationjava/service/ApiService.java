package com.smiley.firebasenotificationjava.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("users")
    Call<UserResponse> createUser(@Body User user);
}
