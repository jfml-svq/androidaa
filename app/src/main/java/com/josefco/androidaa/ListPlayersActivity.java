package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class ListPlayersActivity extends AppCompatActivity {

    public List<Player> players;
    private ArrayAdapter<Player> playerAdapter;
    private Button addteambtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_players);

        addteambtn = findViewById(R.id.addteambtn);



        players = new ArrayList<>();
        ListView lvlistPlayer = findViewById(R.id.lvListPlayers);
        playerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        lvlistPlayer.setAdapter(playerAdapter);






    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }


    public void refreshList() {
        loadPlayers();
        playerAdapter.notifyDataSetChanged();
    }

    private void loadPlayers() {
        players.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "team").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        players.addAll(db.playerDao().listPlayers());
    }


    /*public void addPlayer(View v){
        startActivity(new Intent(this, AddPlayerActivity.class));
    }*/

    /*private void addPlayer(){
          Intent intentAddPlayers = new Intent(this,AddPlayerActivity.class);
          startActivity(intentAddPlayers);
          return;
    }*/


    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.addteambtn:
                miIntent=new Intent(ListPlayersActivity.this,AddPlayerActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.players_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.listTeams:
                Intent intentListTeam = new Intent(this, ListTeamsActivity.class);
                startActivity(intentListTeam);
                return true;
            case R.id.listGames:
                Intent intentListGames = new Intent(this, ListGamesActivity.class);
                startActivity(intentListGames);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}