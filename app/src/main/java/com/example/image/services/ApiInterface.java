package com.example.image.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("task-m-001/list.php/")
    Call<List<String>> getAllImages();
}
