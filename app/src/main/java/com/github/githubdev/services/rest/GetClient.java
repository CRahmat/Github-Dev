package com.github.githubdev.services.rest;

import com.github.githubdev.home.MyRepositoryResponse;
import com.github.githubdev.search.SearchRepositoryResponse;
import com.github.githubdev.searchusername.SearchResponse;
import com.github.githubdev.profile.UserProfileResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetClient {
    //List Repository
    @GET("/users/{user}/repos")
    Call<ArrayList<MyRepositoryResponse>> getFacebookRepository(@Path("user") String user, @Query("per_page") int perPage, @Query("page") int page);
    //Profile
    @GET("/users/{username}")
    Call<UserProfileResponse> getUserProfile(@Path("username") String username);
    //Search
    @GET("search/users")
    Call<SearchResponse> getSearchResponse(@Query("q") String q);

    @GET("search/repositories")
    Call<SearchRepositoryResponse> getSearchRepositoryResponse(@Query("q") String q, @Query("per_page") int perPage, @Query("page") int page);
}
