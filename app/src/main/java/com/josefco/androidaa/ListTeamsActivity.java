package com.josefco.androidaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.josefco.androidaa.dao.GameDao;
import com.josefco.androidaa.dao.PlayerDao;
import com.josefco.androidaa.dao.TeamDao;
import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class ListTeamsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener  {

    public List<Team> teams;
    private ArrayAdapter<Team> teamAdapter;
    Button btnAddTeam, btnAddPlayer;

    private Button addteambtn;
    ListView lvlistTeams;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teams);


        //btnAddPlayer = findViewById(R.id.btnAddPlayer);
        addteambtn = findViewById(R.id.btnAddTeam);
        lvlistTeams = findViewById(R.id.lvListTeams);

        teams = new ArrayList<>();
        teamAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teams);
        lvlistTeams.setAdapter(teamAdapter);





        lvlistTeams.setOnItemClickListener(this);

        /*ListView lvLista = (ListView) findViewById(R.id.con);
        // Registra el menú contextual a la lista de elementos*/
        registerForContextMenu(lvlistTeams);

        Toast.makeText(this, R.string.long_click_lists, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }


    public void refreshList() {
        loadTeams();
        teamAdapter.notifyDataSetChanged();
    }

    private void loadTeams() {
        teams.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "team").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        teams.addAll(db.teamDao().getAll());
    }




    //botones arriba activity
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnAddTeam:
                miIntent=new Intent(this,AddTeamActivity.class);
                break;
            case R.id.btnAddPlayer:
                miIntent=new Intent(this,AddPlayerActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
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
                Intent intentListPlayerr = new Intent(this, ListPlayersActivity.class);
                startActivity(intentListPlayerr);
                return true;
            case R.id.listTeams:
                Intent intentListGamee = new Intent(this, ListGamesActivity.class);
                startActivity(intentListGamee);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_teams, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Team team = teamAdapter.getItem(info.position);

        switch (item.getItemId()) {
            case R.id.delete_team:
                deleteTeam(team);
                //Toast.makeText(this,"Borrar",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.edit_team:
                team = teams.get(info.position);
                Intent intent = new Intent(ListTeamsActivity.this, AddTeamActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("team", team);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(this,getString(R.string.edit),Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    private void deleteTeam(Team team) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();

        String name_Team = team.getName();
        db.playerDao().deletePlayerByNameTeam(name_Team);
        db.teamDao().delete(team);
        //teamAdapter.notifyDataSetChanged();
        refreshList();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String informacion="id: "+teams.get(position).getId_team()+"\n";
        informacion+="Nombre: "+teams.get(position).getName()+"\n";
        informacion+="Telefono: "+teams.get(position).getCategory()+"\n";

        Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

        Team team = teams.get(position);
        Intent intent = new Intent(ListTeamsActivity.this, DetailsTeamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("team", team);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}