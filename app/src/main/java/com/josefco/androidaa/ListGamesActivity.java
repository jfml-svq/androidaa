package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Game;
import com.josefco.androidaa.domain.Player;
import com.josefco.androidaa.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class ListGamesActivity extends AppCompatActivity {

    ListView lvlistgames;
    private ArrayList<String> listgamesInfo;
    //private ArrayList<Game> listgames;
    private List<Game> listgames;
    private ArrayAdapter<Game> gamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_games);


        lvlistgames = (ListView) findViewById(R.id.lvlistgames);
        //getlistgames();

        listgames = new ArrayList<>();

        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listgamesInfo);
        gamesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listgames);
        lvlistgames.setAdapter(gamesAdapter);


        registerForContextMenu(lvlistgames);

        lvlistgames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game game = listgames.get(position);
                Intent intent = new Intent(ListGamesActivity.this, DetailsGamesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("game", game);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }


    public void refreshList() {
        loadGames();
        gamesAdapter.notifyDataSetChanged();
    }

    private void loadGames() {
        listgames.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "team").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        listgames.addAll(db.gameDao().getAll());
    }



    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.addGame:
                miIntent=new Intent(ListGamesActivity.this,AddGameActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_games, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Game game = gamesAdapter.getItem(info.position);

        switch (item.getItemId()) {
            case R.id.edit_game:
                game = listgames.get(info.position);
                Intent intent = new Intent(ListGamesActivity.this, EditGamesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("game", game);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(this,"Editar",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_game:
            deleteGame(game);
            Toast.makeText(this,"Borrar",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteGame(Game game) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();

        //String name_Team = team.getName();
        db.gameDao().delete(game);
        //teamAdapter.notifyDataSetChanged();
        refreshList();

    }


    //ACTION BAR
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.games_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.listTeams:
                Intent intentListTeam = new Intent(this, ListTeamsActivity.class);
                startActivity(intentListTeam);
                return true;
            case R.id.listPlayers:
                Intent intentListPlayerr = new Intent(this, ListPlayersActivity.class);
                startActivity(intentListPlayerr);
                return true;
            case R.id.addGames:
                Intent intentListGames = new Intent(this, AddGameActivity.class);
                startActivity(intentListGames);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}