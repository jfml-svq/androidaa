package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Game;
import com.josefco.androidaa.domain.Team;

import java.util.ArrayList;

public class AddGameActivity extends AppCompatActivity {

    EditText etdate;
    Spinner spinnerlocalteam, spinnervisitorteam;
    ArrayList<String> ListTeamsSpinner;
    ArrayList<Team> TeamsListSpinner;
    CheckBox played;
    Button addgame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        etdate = findViewById(R.id.etdate);
        spinnerlocalteam = findViewById(R.id.splocalteam);
        spinnervisitorteam = findViewById(R.id.spvisitorteam);
        played = findViewById(R.id.cbplayed);


        rellenarSpinner(spinnerlocalteam);
        rellenarSpinner(spinnervisitorteam);
    }


    public void addGame(View view) {

        if (etdate.getText().toString().equals("")) {
            Toast.makeText(this, R.string.date_needed, Toast.LENGTH_SHORT).show();
            return;
        }

        String date = etdate.getText().toString();
        boolean gameplayed = played.isChecked();

        int id_game = 0;
        String local_team_name = null;
        String visitor_team_name = null;

        //spinner local
        int idSpinnerlocal = (int) spinnerlocalteam.getSelectedItemId();
        if (idSpinnerlocal != 0) {
            Log.i("size", TeamsListSpinner.size() + "");
            Log.i("id spinner", idSpinnerlocal + "");
            Log.i("id spinner - 1", (idSpinnerlocal - 1) + "");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
            local_team_name = TeamsListSpinner.get(idSpinnerlocal - 1).getName();
            Log.i("name team", local_team_name + "");
        }
        //spinner visitor
        int idSpinnervisitor = (int) spinnervisitorteam.getSelectedItemId();
        if (idSpinnervisitor != 0) {
            Log.i("size", TeamsListSpinner.size() + "");
            Log.i("id spinner", idSpinnervisitor + "");
            Log.i("id spinner - 1", (idSpinnervisitor - 1) + "");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
            visitor_team_name = TeamsListSpinner.get(idSpinnervisitor - 1).getName();
            Log.i("name team", visitor_team_name + "");
        }


        if (local_team_name.equals(visitor_team_name)) {
            Toast.makeText(this, R.string.must_dif_team, Toast.LENGTH_SHORT).show();
            return;
        } else {
            Game game = new Game(id_game, date, local_team_name, visitor_team_name, gameplayed);

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "team").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            db.gameDao().insert(game);
            Toast.makeText(this, R.string.game_added , Toast.LENGTH_SHORT).show();
        }

        etdate.setText("");
        rellenarSpinner(spinnerlocalteam);
        rellenarSpinner(spinnervisitorteam);
        played.setChecked(false);

    }


    //Creacion de los spinner con los equipos de la base de datos
    private void listTeams() {

        Team team = null;
        TeamsListSpinner = new ArrayList<Team>();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "team").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        TeamsListSpinner.addAll(db.teamDao().getNameTeams());
        listTeamsToSpinner();
    }

    private void listTeamsToSpinner() {

        ListTeamsSpinner = new ArrayList<String>();
        ListTeamsSpinner.add(getString(R.string.select_team));
        for (int i = 0; i < TeamsListSpinner.size(); i++) {
            ListTeamsSpinner.add(/*"ID: "+TeamsListSpinner.get(i).getId_team()+*/"Team: " + TeamsListSpinner.get(i).getName());
        }
    }

    private void rellenarSpinner(Spinner spinner) {

        //Relleno de los equipos en el adaptador del spinner
        listTeams();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListTeamsSpinner);
        spinner.setAdapter(adapter);
        //spinnervisitorteam.setAdapter(adapter);
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()) {
            case R.id.btn_addgame:
                addGame(view);
                break;
        }
        if (miIntent != null) {
            startActivity(miIntent);
        }
    }


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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}