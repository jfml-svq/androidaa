package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addTeam, listTeams, listPlayers, listGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addteambtn = findViewById(R.id.addteambtn);
        Button listTeamsbtn = findViewById(R.id.listTeamsbtn);
        Button listPlayersbtn = findViewById(R.id.listPlayersbtn);
        Button listGamesbtn = findViewById(R.id.listGamesbtn);


        addteambtn.setOnClickListener(v -> {
            Intent intentAddTeam = new Intent(v.getContext(),AddTeamActivity.class);
            startActivity(intentAddTeam);
        });

        listTeamsbtn.setOnClickListener(v -> {
            Intent intentListTeams = new Intent(v.getContext(),ListTeamsActivity.class);
            startActivity(intentListTeams);
        });

        listPlayersbtn.setOnClickListener(v -> {
            Intent intentListPlayers = new Intent(v.getContext(),ListPlayersActivity.class);
            startActivity(intentListPlayers);
        });

        listGamesbtn.setOnClickListener(v -> {
            Intent intentListGames = new Intent(v.getContext(),ListGamesActivity.class);
            startActivity(intentListGames);
        });



    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflado del menu del action bar.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addTeam:
                Intent intentAddTeam = new Intent(this,AddTeamActivity.class);
                startActivity(intentAddTeam);
                return true;
            case R.id.listTeams:
                Intent intentListTeams = new Intent(this, ListTeamsActivity.class);
                startActivity(intentListTeams);
                return true;
            case R.id.listGames:
                Intent intentListGames = new Intent(this, ListGamesActivity.class);
                startActivity(intentListGames);
                return true;
            case R.id.listPlayers:
                Intent intentListPlayers = new Intent(this,ListPlayersActivity.class);
                startActivity(intentListPlayers);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}