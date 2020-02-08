package com.example.restapiwithkey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {


    @GET("plants")
    Call<List<plantpojo>> getALLPlants (@Query("token") String token);
}
