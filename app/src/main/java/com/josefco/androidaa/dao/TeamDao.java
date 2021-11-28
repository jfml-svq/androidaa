package com.josefco.androidaa.dao;


import android.database.Cursor;
import android.widget.AdapterView;

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

    @Query("Select * FROM TEAM")
    List<Team> getNameTeams();

    @Query("SELECT * FROM team WHERE id_team = :id_team")
    Cursor getTeamByID(String id_team);


    @Insert
    void insert (Team team);

    @Delete
    void delete(Team team);


}
