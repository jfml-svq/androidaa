package com.josefco.androidaa.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.josefco.androidaa.domain.Team;

import java.util.List;

@Dao
public interface TeamDao {
    @Query("SELECT * FROM team")
    List<Team> getAll();

    @Insert
    void insert (Team team);

    @Delete
    void delete (Team team);
}
