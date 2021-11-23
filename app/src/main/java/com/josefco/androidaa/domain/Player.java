package com.josefco.androidaa.domain;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Player {

    @PrimaryKey(autoGenerate = true)
    private int id_player;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String last_name;
    @ColumnInfo
    private String phone;
    /*@ColumnInfo
    private Date date_birth;
    @ColumnInfo
    private int id_team;*/

    public Player(int id_player, String name, String last_name, String phone) {
        this.id_player = id_player;
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
    }

    public Player(){

    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
