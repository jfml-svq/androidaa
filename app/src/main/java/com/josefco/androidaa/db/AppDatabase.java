package com.josefco.androidaa.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.josefco.androidaa.dao.GameDao;
import com.josefco.androidaa.dao.PlayerDao;
import com.josefco.androidaa.dao.TeamDao;
import com.josefco.androidaa.domain.Game;
import com.josefco.androidaa.domain.Player;
import com.josefco.androidaa.domain.Team;

@Database(entities = {Player.class, Team.class, Game.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GameDao gameDao();
    public abstract TeamDao teamDao();
    public abstract PlayerDao playerDao();

}
