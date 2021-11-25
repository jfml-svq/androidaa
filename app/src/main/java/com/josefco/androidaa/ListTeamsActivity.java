package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListTeamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teams);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teams_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listPlayers:
                Intent intentListPlayerr = new Intent(ListTeamsActivity.this, ListPlayersActivity.class);
                startActivity(intentListPlayerr);
                return true;
            case R.id.listTeams:
                Intent intentListGamee = new Intent(ListTeamsActivity.this, ListGamesActivity.class);
                startActivity(intentListGamee);
                return true;
            case R.id.addPlayer:
                Intent intentAddPlayerr= new Intent(ListTeamsActivity.this, AddPlayerActivity.class);
                startActivity(intentAddPlayerr);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}