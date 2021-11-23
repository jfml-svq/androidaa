package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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


        listTeamsbtn.setOnClickListener(v -> {
            Intent intentListTeam = new Intent(v.getContext(),ListTeamsActivity.class);
            startActivity(intentListTeam);
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
                // hacer algo
                return true;
            case R.id.listTeams:
                Intent intentListTeam = new Intent(this, ListTeamsActivity.class);
                startActivity(intentListTeam);
                return true;
            case R.id.listGames:
                // hacer algo
                return true;
            case R.id.listPlayers:
                // hacer algo
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}