package com.github.githubdev.favorite.save;
import android.content.Context;

import java.util.List;

public interface FavoriteContract {
    interface view {
        void successAdd();

        void successDelete();

        void getData(List<FavoriteData> listFavoriteDatabase);

        void deleteRepositoryData(FavoriteData favoriteData);

        void dataAlready();
    }

    // interfaace presenter digunakan untuk kodingan database nya
    interface presenter {
        void insertRepositoryData(String title, String description, String fork, String star, String language, String url, FavoriteDatabase favoriteDatabase);

        void readRepositoryData(FavoriteDatabase favoriteDatabase, Context context);

        void deleteRepositoryData(FavoriteData favoriteData, FavoriteDatabase favoriteDatabase);
    }
}
