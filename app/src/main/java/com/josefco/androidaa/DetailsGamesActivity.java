package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.josefco.androidaa.domain.Game;
import com.josefco.androidaa.domain.Player;

public class DetailsGamesActivity extends AppCompatActivity {

    TextView tvidgame, tvdategame, tvlocalteam, tvvisitorteam;
    CheckBox cbplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_games);

        tvidgame = findViewById(R.id.tvidgameGD);
        tvdategame = findViewById(R.id.tvdateDG);
        tvlocalteam = findViewById(R.id.tvlocalteamDG);
        tvvisitorteam = findViewById(R.id.tvvisitorteamDG);
        cbplayed = findViewById(R.id.cbplayedDG);

        Bundle objetoEnviado = getIntent().getExtras();
        Game game = null;

        if(objetoEnviado!=null) {
            game = (Game) objetoEnviado.getSerializable("game");
            String idgame = String.valueOf(game.getId_game());
            tvidgame.setText(idgame);
            tvdategame.setText(game.getFecha());
            tvlocalteam.setText(game.getLocal_team());
            tvvisitorteam.setText(game.getVisit_team());
            cbplayed.setChecked(game.getPlayed());
            cbplayed.setEnabled(false);
        }

    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btn_seeLocation:
                Intent intent = new Intent(DetailsGamesActivity.this, SeeGameLocationActivity.class);
                Bundle objetoEnviado = getIntent().getExtras();
                Game game = null;
                if(objetoEnviado!=null){
                    game= (Game) objetoEnviado.getSerializable("game");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("game", game);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    //Toast.makeText(this,game.getVisit_team(), Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
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