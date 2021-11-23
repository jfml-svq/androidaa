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
    private Date fecha;
    @ColumnInfo
    private String lat;
    @ColumnInfo
    private String lon;
    @ColumnInfo
    private int id_local_team;
    @ColumnInfo
    private int id_vitit_team;*/

}
