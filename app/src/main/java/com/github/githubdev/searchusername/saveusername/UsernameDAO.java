package com.github.githubdev.searchusername.saveusername;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UsernameDAO {
    @Insert()
    Long insertUsername(UsernameData usernameData);

    @Query("SELECT * FROM usernameSave")
    UsernameData getUsername();

    @Delete
    void deleteUsernameData(UsernameData usernameData);

}
