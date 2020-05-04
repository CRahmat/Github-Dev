package com.github.githubdev.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.githubdev.searchusername.saveusername.UsernameOperation;
import com.github.githubdev.services.base.APIClient;
import com.github.githubdev.searchusername.saveusername.UsernameDAO;
import com.github.githubdev.searchusername.saveusername.UsernameData;
import com.github.githubdev.searchusername.saveusername.UsernameDatabase;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepositoryViewModel extends ViewModel {
    private APIClient apiClient;
    private MutableLiveData<ArrayList<MyRepositoryResponse>> liveData = new MutableLiveData<>();
    private String reciveUsername;
    UsernameData usernameData ;
    UsernameDatabase usernameDatabase;
    UsernameDAO usernameDAO;
    Context context;
    public void setRepository(Context context){
        this.context = context;
        if(usernameDatabase == null){
            usernameDatabase = UsernameDatabase.usernameDatabase(context);
            usernameDAO = usernameDatabase.usernameDAO();
            usernameData = usernameDatabase.usernameDAO().getUsername();
        }
        reciveUsername = usernameData.getUsername();
        setRepository(1);
    }
    public void setRepository(final int page) {
        //API Check and Instansiasi
        if (this.apiClient == null) {
            apiClient = new APIClient();
        }
        //Request data from API
            apiClient.getAPIClient().getFacebookRepository(reciveUsername, 100, page).enqueue(new Callback<ArrayList<MyRepositoryResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<MyRepositoryResponse>> call, Response<ArrayList<MyRepositoryResponse>> response) {
                    ArrayList<MyRepositoryResponse> repositoryResponse = response.body();
                    if (repositoryResponse.size() != 0 && repositoryResponse != null) {
                        liveData.postValue(repositoryResponse);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<MyRepositoryResponse>> call, Throwable t) {
                    Log.d(t.getMessage(), "Failed to request data from server!!!");
                }
            });
        }
    public LiveData<ArrayList<MyRepositoryResponse>> getRepository(){
        return liveData;
    }
}
