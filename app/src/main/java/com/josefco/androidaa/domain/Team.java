package com.josefco.androidaa.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Team {

    @PrimaryKey(autoGenerate = true)
    private int id_team;
    @ColumnInfo
    private String name;
    /*@ColumnInfo
    private Date create_date;*/
    @ColumnInfo
    private String category;

    public Team(int id_team, String name /*Date create_date*/, String category) {
        this.id_team = id_team;
        this.name = name;
        /*this.create_date = create_date;*/
        this.category = category;
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

    @Override
    public String toString() {
        return "Team name=" + name + " category= " + category;
    }
}


