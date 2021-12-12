package com.josefco.androidaa.domain;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.josefco.androidaa.R;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Game implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id_game;
    @ColumnInfo
    private String fecha;
    /*@ColumnInfo
    private String lat;
    @ColumnInfo
    private String lon;*/
    @ColumnInfo
    private String local_team;
    @ColumnInfo
    private String visit_team;
    @ColumnInfo
    private Boolean played;
    @ColumnInfo
    private double latitudeGame;
    @ColumnInfo
    private double longitudeGame;



    public Game(int id_game, String fecha, String local_team, String visit_team, Boolean played) {
        this.id_game = id_game;
        this.fecha = fecha;
        this.local_team = local_team;
        this.visit_team = visit_team;
        this.played = played;
    }



    public Game() {
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLocal_team() {
        return local_team;
    }

    public void setLocal_team(String local_team) {
        this.local_team = local_team;
    }

    public String getVisit_team() {
        return visit_team;
    }

    public void setVisit_team(String visit_team) {
        this.visit_team = visit_team;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public double getLatitudeGame() {
        return latitudeGame;
    }

    public void setLatitudeGame(double latitudeGame) {
        this.latitudeGame = latitudeGame;
    }

    public double getLongitudeGame() {
        return longitudeGame;
    }

    public void setLongitudeGame(double longitudeGame) {
        this.longitudeGame = longitudeGame;
    }

    @Override
    public String toString() {

        String played;

        if(getPlayed()){
            played = "Yes";
        }else{
            played = "No";
        }

        return "Game nº " + id_game + "\n"+
                "Local: " + local_team + " vs " + visit_team + " :Visitor \n" +
                "Date " + fecha +" - Played: " + played;
    }
}
