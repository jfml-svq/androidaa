package com.josefco.androidaa.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.google.android.gms.maps.model.LatLng;
import com.josefco.androidaa.domain.Game;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM GAME")
    Cursor listAllGames();

    @Query("SELECT * FROM GAME")
    List<Game> getAll();

    @Insert
    void insert(Game game);

    @Delete
    void delete(Game game);

    @Query("UPDATE GAME SET /*id_game = :id_game,*/ fecha = :fecha, local_team = :local_team, visit_team = :visit_team, played = :played WHERE id_game = :id_game")
    void update(String fecha, String local_team,String visit_team ,boolean played, int id_game);

    //@Query("INSERT INTO GAME (latitudeGame,longitudeGame) VALUES (:latitudGame,:longitudGame) WHERE id_game =")
    @Query("UPDATE GAME SET latitudeGame = :latitudeGame, longitudeGame = :longitudeGame WHERE id_game = :id_game")
    void insertLocation(double latitudeGame, double longitudeGame, int id_game);

}
