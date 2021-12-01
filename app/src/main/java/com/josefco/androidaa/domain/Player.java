package com.josefco.androidaa.domain;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.josefco.androidaa.R;

import java.io.Serializable;

@Entity
public class Player implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id_player;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String last_name;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private int squad_number;
    /*@ColumnInfo
    private Date birthday;*/
    @ColumnInfo
    private String name_Team;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    /*public Player(int id_player, String name, String last_name, String phone) {
        this.id_player = id_player;
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
    }*/

    public Player(int id_player, String name, String last_name, String phone, String name_Team, int squad_number, byte[] image) {
        this.id_player = id_player;
        this.name = name;
        this.last_name = last_name;
        this.phone = phone;
        this.name_Team = name_Team;
        this.squad_number = squad_number;
        this.image = image;
    }

    public Player(){

    }

    @Override
    public String toString() {
        return "Name: "+name + " " + last_name + " / Team: " + name_Team;
    }

    public int getSquad_number() {
        return squad_number;
    }

    public void setSquad_number(int squad_number) {
        this.squad_number = squad_number;
    }

    /*public Date getDate_birth() {
        return birthday;
    }

    public void setDate_birth(Date date_birth) {
        this.birthday = date_birth;
    }*/

    public String getName_Team() {
        return name_Team;
    }

    public void setName_Team(String name_Team) {
        this.name_Team = name_Team;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
