package com.josefco.androidaa.domain;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Team implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id_team;
    @ColumnInfo
    private String name;
    /*@ColumnInfo
    private Date create_date;*/
    @ColumnInfo
    private String category;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Team(int id_team, String name /*Date create_date*/, String category, byte[] image) {
        this.id_team = id_team;
        this.name = name;
        /*this.create_date = create_date;*/
        this.category = category;
        this.image = image;
    }

    /*public Team(String name, String category) {
        this.name = name;
        this.category = category;
    }*/

    public Team() {
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Name: " + name + " - Category: " + category;
    }
}


