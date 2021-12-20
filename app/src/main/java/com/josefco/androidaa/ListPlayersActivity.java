package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Player;
import com.josefco.androidaa.domain.Team;

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

        lvlistPlayer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*String informacion="id: "+players.get(position).getId_player()+"\n";
                informacion+="Nombre: "+players.get(position).getName()+"\n";
                informacion+="Apellido: "+players.get(position).getLast_name()+"\n";
                informacion+="Telefono: "+players.get(position).getPhone()+"\n";*/

                //Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

                Player player = players.get(position);
                Intent intent = new Intent(ListPlayersActivity.this, DetailsPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("player", player);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        registerForContextMenu(lvlistPlayer);

        Toast.makeText(this, R.string.long_click_lists, Toast.LENGTH_SHORT).show();

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

    //MENU CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_players, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Player player = playerAdapter.getItem(info.position);

        switch (item.getItemId()) {
            case R.id.delete_player:
                deleteplayer(player);
                Toast.makeText(this,"Borrar",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.edit_player:
                player = players.get(info.position);
                Intent intent = new Intent(ListPlayersActivity.this, AddPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("player", player);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(this,"Editar",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteplayer(Player player) {

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
        db.playerDao().delete(player);
        refreshList();

    }


    //MENU ACTION BAR
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