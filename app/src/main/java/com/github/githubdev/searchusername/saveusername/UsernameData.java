package com.github.githubdev.searchusername.saveusername;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "usernameSave")
public class UsernameData {
    @NonNull

    @PrimaryKey()
    @ColumnInfo(name = "username")
    private String username;

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }
}
