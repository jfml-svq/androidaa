package com.josefco.androidaa.dao;


import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
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

    @Query("SELECT * FROM PLAYER WHERE name_Team = (SELECT name FROM TEAM where id_team = :id_team)")
    Cursor listPlayerbyTeam(int id_team);

    @Delete
    void delete(Player player);

    @Query("UPDATE PLAYER SET name = :name, last_name = :last_name, phone = :phone, name_Team = :name_team, squad_number = :squad_number, image = :image where id_player= :id_player")
    void editPlayer(String name, String last_name, String phone, String name_team, int id_player, int squad_number, byte[]image);

    @Query("SELECT * FROM PLAYER")
    Cursor listPlayerRV();



}
