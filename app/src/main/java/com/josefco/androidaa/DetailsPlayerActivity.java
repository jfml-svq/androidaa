package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.josefco.androidaa.domain.Player;
import com.josefco.androidaa.domain.Team;

public class DetailsPlayerActivity extends AppCompatActivity {

    TextView tvidplayer, tvnameplayer, tvlastnameplayer, tvphoneplayer, tvnameteamplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_player);

        //tvidplayer = findViewById(R.id.tvidteam);
        tvnameplayer = findViewById(R.id.tvnameplayer);
        tvlastnameplayer = findViewById(R.id.tvlastnameplayer);
        tvphoneplayer = findViewById(R.id.tvphoneplayer);
        tvnameteamplayer = findViewById(R.id.tvnameteamplayer);

        Bundle objetoEnviado = getIntent().getExtras();
        Player player = null;

        if(objetoEnviado!=null){
            player= (Player) objetoEnviado.getSerializable("player");
            tvnameplayer.setText(player.getName());
            tvlastnameplayer.setText(player.getLast_name());
            tvphoneplayer.setText(player.getPhone());
            tvnameteamplayer.setText(player.getName_Team());
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