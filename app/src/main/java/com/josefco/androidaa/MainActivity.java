package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addTeam:
                // hacer algo
                return true;
            case R.id.listTeams:
                // hacer algo
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