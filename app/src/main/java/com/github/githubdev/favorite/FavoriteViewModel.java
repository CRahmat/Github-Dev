package com.github.githubdev.favorite;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.githubdev.favorite.save.FavoriteDAO;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavoriteViewModel extends ViewModel {
    FavoriteDAO favoriteDAO;
    FavoriteDatabase favoriteDatabase;
    List<FavoriteData> favoriteData = new ArrayList<>();
    private MutableLiveData<List<FavoriteData>> liveData = new MutableLiveData<>();

    public void setRepository(Context context) {
        if(favoriteDatabase == null){
            favoriteDatabase = FavoriteDatabase.database(context);
            favoriteDAO = favoriteDatabase.repositoryDAO();
            favoriteData = favoriteDatabase.repositoryDAO().getRepositoryData();
        }
        liveData.postValue(favoriteData);
    }

    public LiveData<List<FavoriteData>> getRepository(){
        return liveData;
    }

}
