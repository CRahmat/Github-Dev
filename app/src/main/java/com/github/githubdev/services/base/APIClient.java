package com.github.githubdev.services.base;

import com.github.githubdev.services.rest.GetClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    //Retrofit untuk menerjemahkan API ke dalam antar muka aplikasi
    Retrofit retrofit;
    public GetClient getAPIClient(){
        //BASE URL
        String BASE_URL = "https://api.github.com/";
        //Convert from base url to gson
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Mengembalikan nilai ke getAPIClient()
        return retrofit.create(GetClient.class);
    }
}
