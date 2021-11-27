package com.josefco.androidaa.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.josefco.androidaa.domain.Player;

import java.util.Collection;
import java.util.List;

@Dao
public interface PlayerDao {

    @Insert
    void insert(Player player);

    @Query("DELETE FROM player WHERE name_Team = :name_Team")
    void deletePlayerByNameTeam(String name_Team);

    @Query("SELECT * FROM PLAYER")
    List<Player> listPlayers();

}
