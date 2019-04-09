package com.daninpr.tugascatapp.services;

import com.daninpr.tugascatapp.model.Cat;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatService {
    @GET("/floof/")
    Call<Cat> getRandomCat();
}
