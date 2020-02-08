package com.example.restapiwithkey;

import android.content.Context;


import com.example.restapiwithkey.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


        private  static Retrofit retrofit;
    private static  String BASE_URL = "";


    public static Retrofit getRetrofitInstance(Context context){

        BASE_URL = context.getResources().getString(R.string.base_url);

        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
