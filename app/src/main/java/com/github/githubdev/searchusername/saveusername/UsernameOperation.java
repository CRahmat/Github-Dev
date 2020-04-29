package com.github.githubdev.searchusername.saveusername;

import android.content.Context;
import android.os.AsyncTask;

import com.github.githubdev.favorite.save.FavoriteOperation;

public class UsernameOperation implements UsernameContract.presenter {
    Context context;
    UsernameDAO usernameDAO;
    UsernameDatabase usernameDatabase;
    UsernameData usernameData;
    public UsernameOperation(Context context) {
        this.context = context;
    }
    public UsernameOperation(){

    }
    @Override
    public void insertUsername(String username, UsernameDatabase usernameDatabase) {
        final UsernameData usernameData = new UsernameData();
        usernameData.setUsername(username);
        new InsertData(usernameDatabase, usernameData).execute();
    }
    class InsertData extends AsyncTask<Void, Void, Long> {
        private UsernameDatabase usernameDatabase;
        private UsernameData usernameData;

        public InsertData(UsernameDatabase usernameDatabase, UsernameData usernameData) {
            this.usernameDatabase = usernameDatabase;
            this.usernameData = usernameData;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return usernameDatabase.usernameDAO().insertUsername(usernameData);
        }
    }
    @Override
    public void readUsername(UsernameDatabase usernameDatabase, Context context) {
        usernameDatabase = UsernameDatabase.usernameDatabase(context);
        usernameDAO = usernameDatabase.usernameDAO();
        usernameDAO.getUsername();
    }

    @Override
    public void deleteUsername(UsernameData usernameData, UsernameDatabase usernameDatabase) {
        new DeleteRepositoryData(usernameDatabase, usernameData).execute();
    }

    class DeleteRepositoryData extends AsyncTask<Void, Void, Void>{
        private UsernameDatabase usernameDatabase;
        private UsernameData usernameData;

        public DeleteRepositoryData(UsernameDatabase usernameDatabase, UsernameData usernameData) {
            this.usernameDatabase = usernameDatabase;
            this.usernameData = usernameData;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            usernameDatabase.usernameDAO().deleteUsernameData(usernameData);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }
}
