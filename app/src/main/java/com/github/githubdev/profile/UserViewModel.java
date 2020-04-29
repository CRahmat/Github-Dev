package com.github.githubdev.profile;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.githubdev.services.base.APIClient;
import com.github.githubdev.searchusername.saveusername.UsernameDAO;
import com.github.githubdev.searchusername.saveusername.UsernameData;
import com.github.githubdev.searchusername.saveusername.UsernameDatabase;
import com.google.firebase.database.DatabaseReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private APIClient apiClient;
    private MutableLiveData<UserProfileResponse> liveData = new MutableLiveData<>();
    DatabaseReference databaseReference;
    UsernameDatabase usernameDatabase;
    UsernameData usernameData;
    UsernameDAO usernameDAO;
    String reciveUsername;
    public void setUser(Context context){
        if(usernameDatabase == null){
            usernameDatabase = UsernameDatabase.usernameDatabase(context);
            usernameDAO = usernameDatabase.usernameDAO();
            usernameData = usernameDatabase.usernameDAO().getUsername();
        }
        reciveUsername = usernameData.getUsername();
        if(this.apiClient == null){
            apiClient = new APIClient();
        }
        apiClient.getAPIClient().getUserProfile(reciveUsername).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                UserProfileResponse userProfileResponse = response.body();
                if (userProfileResponse != null){
                    liveData.postValue(userProfileResponse);
                }
            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
            }
        });
    }
    public LiveData<UserProfileResponse> getUser() {
        return liveData;
    }
}
