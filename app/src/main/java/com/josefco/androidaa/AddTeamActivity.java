package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Team;

public class AddTeamActivity extends AppCompatActivity {


    private Button btn_listTeamAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        btn_listTeamAdd = findViewById(R.id.btn_listTeamAdd);

        btn_listTeamAdd.setOnClickListener(v -> {
            Intent intentListTeams = new Intent(v.getContext(),ListTeamsActivity.class);
            startActivity(intentListTeams);
        });

    }


    public void addteam(View view){

        EditText etteamname = findViewById(R.id.team_name);
        EditText etteamcategory = findViewById(R.id.team_category);

        if (etteamname.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.write_team), Toast.LENGTH_SHORT).show();
            return;
        }

        String teamname = etteamname.getText().toString();
        String teamcategory = etteamcategory.getText().toString();

        int id_team = 0;
        Team team = new Team(id_team, teamname, teamcategory);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
        db.teamDao().insert(team);
        Toast.makeText(this,"Equipo añadido",Toast.LENGTH_SHORT).show();

        etteamname.setText("");
        etteamcategory.setText("");

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