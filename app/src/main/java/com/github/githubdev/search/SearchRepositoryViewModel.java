package com.github.githubdev.search;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.githubdev.home.MyRepositoryResponse;
import com.github.githubdev.searchusername.saveusername.UsernameDAO;
import com.github.githubdev.searchusername.saveusername.UsernameData;
import com.github.githubdev.searchusername.saveusername.UsernameDatabase;
import com.github.githubdev.services.base.APIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepositoryViewModel extends ViewModel {
    private APIClient apiClient;
    private MutableLiveData<SearchRepositoryResponse> liveData = new MutableLiveData<>();
    private String repositoryName;
    Context context;
    public void setRepository(Context context, String repositoryName){
        this.context = context;
        this.repositoryName = repositoryName;
        setRepository(1);
    }
    public void setRepository(final int page) {
        //API Check and Instansiasi
        if (this.apiClient == null) {
            apiClient = new APIClient();
        }
        //Request data from API
            apiClient.getAPIClient().getSearchRepositoryResponse(repositoryName, 100, page).enqueue(new Callback<SearchRepositoryResponse>() {
                @Override
                public void onResponse(Call<SearchRepositoryResponse> call, Response<SearchRepositoryResponse> response) {
                    SearchRepositoryResponse repositoryResponse = response.body();
                    if (repositoryResponse != null) {
                        liveData.postValue(repositoryResponse);
                    }
                }

                @Override
                public void onFailure(Call<SearchRepositoryResponse> call, Throwable t) {
                    Log.d(t.getMessage(), "Failed to request data from server!!!");
                }
            });
        }
    public LiveData<SearchRepositoryResponse> getRepository(){
        return liveData;
    }
}
