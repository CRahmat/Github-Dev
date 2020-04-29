package com.github.githubdev.searchusername;

import com.github.githubdev.searchusername.SearchUsername;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {
    @SerializedName("items")
    ArrayList<SearchModel> result;
}
