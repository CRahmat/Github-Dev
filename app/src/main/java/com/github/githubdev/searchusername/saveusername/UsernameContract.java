package com.github.githubdev.searchusername.saveusername;

import android.content.Context;


public interface UsernameContract {
    interface presenter{
        void insertUsername(String username, UsernameDatabase usernameDatabase);

        void readUsername(UsernameDatabase usernameDatabase, Context context);

        void deleteUsername(UsernameData usernameData, UsernameDatabase usernameDatabase);
    }
}
