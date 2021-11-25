package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AddTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teams_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addPlayer:
                Intent intentAddPlayer = new Intent(this, AddPlayerActivity.class);
                startActivity(intentAddPlayer);
                return true;
            case R.id.listTeams:
                Intent intentListGames = new Intent(this, ListGamesActivity.class);
                startActivity(intentListGames);
                return true;
            case R.id.listPlayers:
                Intent intentListPlayers = new Intent(this, ListPlayersActivity.class);
                startActivity(intentListPlayers);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}