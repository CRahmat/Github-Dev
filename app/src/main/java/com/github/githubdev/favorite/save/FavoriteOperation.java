package com.github.githubdev.favorite.save;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class FavoriteOperation implements FavoriteContract.presenter {
    private FavoriteContract.view viewContract;
    FavoriteDAO favoriteDAO;
    List<FavoriteData> favoriteData = new ArrayList<>();
    public FavoriteOperation(FavoriteContract.view viewContract) {
        this.viewContract = viewContract;
    }

    public FavoriteOperation(Context applicationContext) {
    }

    class InsertData extends AsyncTask<Void, Void, Long>{
        private FavoriteDatabase favoriteDatabase;
        private FavoriteData favoriteData;

        public InsertData(FavoriteDatabase favoriteDatabase, FavoriteData favoriteData) {
            this.favoriteDatabase = favoriteDatabase;
            this.favoriteData = favoriteData;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return favoriteDatabase.repositoryDAO().insertRepository(favoriteData);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            viewContract.successAdd();
        }

    }

    @Override
    public void insertRepositoryData(String title, String description, String fork, String star, String language, String created, String updated, String watch, String url, final FavoriteDatabase favoriteDatabase) {
        if(favoriteData.size()==0){
            final FavoriteData favoriteData = new FavoriteData();
            favoriteData.setFullName(title);
            favoriteData.setDescription(description);
            favoriteData.setForksCount(fork);
            favoriteData.setStarGazersCount(star);
            favoriteData.setLanguage(language);
            favoriteData.setCreatedAt(created);
            favoriteData.setUpdatedAt(updated);
            favoriteData.setWatchersCount(watch);
            favoriteData.setLink(url);
            new InsertData(favoriteDatabase, favoriteData).execute();
        }else{
            for(int i = 0; i < favoriteData.size() ; i++){
             if(favoriteData.get(i).getFullName() == title){
                 viewContract.dataAlready();
             }else {
                 final FavoriteData favoriteData = new FavoriteData();
                 favoriteData.setFullName(title);
                 favoriteData.setDescription(description);
                 favoriteData.setForksCount(fork);
                 favoriteData.setStarGazersCount(star);
                 favoriteData.setLanguage(language);
                 favoriteData.setLink(url);
                 new InsertData(favoriteDatabase, favoriteData).execute();
             }
            }
        }
    }

    @Override
    public void readRepositoryData(FavoriteDatabase favoriteDatabase, Context context) {
        favoriteDatabase = FavoriteDatabase.database(context);
        favoriteDAO = favoriteDatabase.repositoryDAO();
        favoriteData = favoriteDatabase.repositoryDAO().getRepositoryData();
        viewContract.getData(favoriteData);
    }

    class DeleteRepositoryData extends AsyncTask<Void, Void, Void>{
        private FavoriteDatabase favoriteDatabase;
        private FavoriteData favoriteData;

        public DeleteRepositoryData(FavoriteDatabase favoriteDatabase, FavoriteData favoriteData) {
            this.favoriteDatabase = favoriteDatabase;
            this.favoriteData = favoriteData;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteDatabase.repositoryDAO().deleteRepositoryData(favoriteData);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewContract.successDelete();
        }

    }

    @Override
    public void deleteRepositoryData(final FavoriteData favoriteData,
                           final FavoriteDatabase favoriteDatabase) {
        new DeleteRepositoryData(favoriteDatabase, favoriteData).execute();
    }

}