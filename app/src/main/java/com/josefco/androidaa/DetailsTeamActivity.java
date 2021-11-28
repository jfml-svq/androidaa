package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class DetailsTeamActivity extends AppCompatActivity {

    TextView tvidteam, tvnameteam, tvcategory;
    ArrayList<Team> detailsteamlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_team);


        tvidteam = (TextView) findViewById(R.id.tvidteam);
        tvnameteam = (TextView) findViewById(R.id.tvnameteam);
        tvcategory = (TextView) findViewById(R.id.tvcategory);

        /*Intent intent = getIntent();
        String id_team = intent.getStringExtra("id_team");
        String name_team = intent.getStringExtra("team_name");
        String category = intent.getStringExtra("category");

        /*tvidteam.setText(id_team);
        tvnameteam.setText(name_team);
        tvcategory.setText(category);

        getDetailsTeam(id_team);*/

        Bundle objetoEnviado = getIntent().getExtras();
        Team team = null;

        if(objetoEnviado!=null){
            team= (Team) objetoEnviado.getSerializable("team");
            String id = Integer.toString(team.getId_team());
            tvidteam.setText(id);
            tvnameteam.setText(team.getName());
            tvcategory.setText(team.getCategory());
        }

    }

    /*private List<Team> getDetailsTeam(String id_team) {

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();

        Team team = null;
        detailsteamlist =new ArrayList<Team>();

        //Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);
        Cursor cursor=db.teamDao().getTeamByID(id_team);

        while (cursor.moveToNext()) {
            team = new Team();
            team.setId_team(cursor.getInt(0));
            team.setName(cursor.getString(1));
            team.setCategory(cursor.getString(2));
        }

        tvidteam.setText(team.getId_team());
        tvnameteam.setText(team.getName());
        tvcategory.setText(team.getCategory());

        return detailsteamlist;

    }*/

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
            case R.id.addTeam:
                Intent intentAddTeamm= new Intent(this, AddTeamActivity.class);
                startActivity(intentAddTeamm);
                return true;
            case R.id.addPlayer:
                Intent intentAddPlayerr= new Intent(this, AddPlayerActivity.class);
                startActivity(intentAddPlayerr);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}