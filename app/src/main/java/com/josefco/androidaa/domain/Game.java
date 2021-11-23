package com.josefco.androidaa.domain;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Game {

    @PrimaryKey(autoGenerate = true)
    private int id_game;
    /*@ColumnInfo
    private Date fecha;*/
    /*@ColumnInfo
    private String lat;
    @ColumnInfo
    private String lon;*/
    @ColumnInfo
    private int id_local_team;
    @ColumnInfo
    private int id_vitit_team;

    public Game(int id_game, int id_local_team, int id_vitit_team) {
        this.id_game = id_game;
        this.id_local_team = id_local_team;
        this.id_vitit_team = id_vitit_team;
    }

    public Game() {
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public int getId_local_team() {
        return id_local_team;
    }

    public void setId_local_team(int id_local_team) {
        this.id_local_team = id_local_team;
    }

    public int getId_vitit_team() {
        return id_vitit_team;
    }

    public void setId_vitit_team(int id_vitit_team) {
        this.id_vitit_team = id_vitit_team;
    }
}
