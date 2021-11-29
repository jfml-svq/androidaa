package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Player;
import com.josefco.androidaa.domain.Team;

import java.util.ArrayList;

public class AddPlayerActivity extends AppCompatActivity {


    Spinner spinnerTeams;
    EditText etNamePlayer, etLastNamePlayer, etPhone;
    TextView tvTeam;
    ArrayList<String> ListTeamsSpinner;
    ArrayList<Team> TeamsListSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);


        spinnerTeams = findViewById(R.id.spTeams);
        etNamePlayer = findViewById(R.id.etNamePlayer);
        etLastNamePlayer = findViewById(R.id.etLastNamePlayer);
        etPhone = findViewById(R.id.etPhone);
        tvTeam = findViewById(R.id.tvTeam);

        Bundle objetoEnviado = getIntent().getExtras();
        Player player = null;
        if(objetoEnviado!=null){
            player= (Player) objetoEnviado.getSerializable("player");
            //String id = Integer.toString(player.getId_player());
            //tvidteam.setText(id);
            etNamePlayer.setText(player.getName());
            etLastNamePlayer.setText(player.getLast_name());
            etPhone.setText(player.getPhone());
            tvTeam.setText(player.getName_Team());
        }



        rellenarSpinner();

    }

    private void rellenarSpinner() {

        //Relleno de los equipos en el adaptador del spinner
        listTeams();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ListTeamsSpinner);
        spinnerTeams.setAdapter(adapter);
    }


    public void addPlayer (View view){

        if (etNamePlayer.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.write_name_player), Toast.LENGTH_SHORT).show();
            return;
        }

        String namePlayer = etNamePlayer.getText().toString();
        String lastNamePlayer = etLastNamePlayer.getText().toString();
        String phonePlayer = etPhone.getText().toString();


        int id_player = 0;
        final String team_name;
    //SPINNERNENRNENRER
        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        int idSpinner= (int) spinnerTeams.getSelectedItemId();
        /**
         * Valida la seleccion del spinner de equipos, si selecciona 0(selecciona) sale, si no entra con el id elegido.
         */
        if (idSpinner!=0) {
            Log.i("size", ListTeamsSpinner.size() + "");
            Log.i("id spinner", idSpinner + "");
            Log.i("id spinner - 1", (idSpinner - 1) + "");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
            team_name = TeamsListSpinner.get(idSpinner - 1).getName();
            Log.i("name team", team_name + "");

        Player player = new Player(id_player, namePlayer, lastNamePlayer, phonePlayer, team_name);

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
        db.playerDao().insert(player);

            Toast.makeText(this, getString(R.string.player_added),Toast.LENGTH_SHORT).show();

            etNamePlayer.setText("");
            etLastNamePlayer.setText("");
            etPhone.setText("");
            tvTeam.setText("");
            rellenarSpinner();

        }else{

        Toast.makeText(getApplicationContext(), getString(R.string.choose_team),Toast.LENGTH_LONG).show();
        }
    }

    private void editPlayer(View view) {

        etNamePlayer = findViewById(R.id.etNamePlayer);
        etLastNamePlayer = findViewById(R.id.etLastNamePlayer);
        etPhone = findViewById(R.id.etPhone);

        Bundle objetoEnviado = getIntent().getExtras();
        Player player = null;

        if(objetoEnviado!=null){
            player= (Player) objetoEnviado.getSerializable("player");
            String id = Integer.toString(player.getId_player());
        }


        try {

            if (etNamePlayer.getText().toString().equals("")){
                Toast.makeText(this, getString(R.string.write_name_player), Toast.LENGTH_SHORT).show();
                return;
            }

            String playerName = etNamePlayer.getText().toString();
            String playerLastName = etLastNamePlayer.getText().toString();
            String playerPhone = etPhone.getText().toString();
            final String team_name;

            int idSpinner= (int) spinnerTeams.getSelectedItemId();
            /**
             * Valida la seleccion del spinner de equipos, si selecciona 0(selecciona) sale, si no entra con el id elegido.
             */
            if (idSpinner!=0) {
                Log.i("size", ListTeamsSpinner.size() + "");
                Log.i("id spinner", idSpinner + "");
                Log.i("id spinner - 1", (idSpinner - 1) + "");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
                team_name = TeamsListSpinner.get(idSpinner - 1).getName();
                Log.i("name team", team_name + "");


            //String playerTeam = spinnerTeams.getSelectedItem().toString();

            int id_team = player.getId_player();

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
            db.playerDao().editPlayer(playerName, playerLastName , playerPhone, team_name, id_team);
            Toast.makeText(this,"Player edited",Toast.LENGTH_SHORT).show();

            etNamePlayer.setText("");
            etLastNamePlayer.setText("");
            etPhone.setText("");
            tvTeam.setText("");
            rellenarSpinner();
            }else{
                Toast.makeText(getApplicationContext(), getString(R.string.choose_team),Toast.LENGTH_LONG).show();
            }


        } catch(Exception e) {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }

    }

    //botones arriba activity
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btn_listPlayerAdd:
                miIntent=new Intent(this,ListPlayersActivity.class);
                break;
            case R.id.addPlayer:
                addPlayer(view);
                break;
            case R.id.editPlayer:
                editPlayer(view);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

    //Creacion del spinner con los equips de la base de datos
    private void listTeams() {

        Team team = null;
        TeamsListSpinner = new ArrayList<Team>();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "team").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        TeamsListSpinner.addAll(db.teamDao().getNameTeams());
        listTeamsToSpinner();
    }

    private void listTeamsToSpinner(){

        ListTeamsSpinner = new ArrayList<String>();
        ListTeamsSpinner.add(getString(R.string.select_team));
        for (int i=0;i<TeamsListSpinner.size();i++){
            ListTeamsSpinner.add(TeamsListSpinner.get(i).getId_team()+" - " + TeamsListSpinner.get(i).getName());
        }
    }

    //rellenar spinner con el equipo para editarlo


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