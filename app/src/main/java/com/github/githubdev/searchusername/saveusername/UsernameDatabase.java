package com.github.githubdev.searchusername.saveusername;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UsernameData.class}, version = 1)
public abstract class UsernameDatabase extends RoomDatabase {
    public abstract UsernameDAO usernameDAO();
    public static UsernameDatabase usernameDatabase;

    public static UsernameDatabase usernameDatabase(Context context){
        if(usernameDatabase == null){
            usernameDatabase =  Room.databaseBuilder(context, UsernameDatabase.class, "usernameDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return usernameDatabase;
    }
}
