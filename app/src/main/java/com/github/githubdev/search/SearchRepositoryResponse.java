package com.github.githubdev.search;

import com.github.githubdev.searchusername.SearchModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchRepositoryResponse {
    @SerializedName("total_count")
    String totalResult;
    @SerializedName("items")
    ArrayList<SearchRepositoryModel> result;

}
