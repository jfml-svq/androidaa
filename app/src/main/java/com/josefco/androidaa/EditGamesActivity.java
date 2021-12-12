package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Game;
import com.josefco.androidaa.domain.Player;

public class EditGamesActivity extends AppCompatActivity {

    TextView tvidgame, tvlocalteam, tvvisitorteam;
    EditText etdate;
    CheckBox cbplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_games);

        tvidgame = findViewById(R.id.tvidgameEG);
        etdate = findViewById(R.id.etdateEG);
        tvlocalteam = findViewById(R.id.tvlocalteamEG);
        tvvisitorteam = findViewById(R.id.tvvisitorteamEG);
        cbplayed = findViewById(R.id.cbplayedEG);



        Bundle objetoEnviado = getIntent().getExtras();
        Game game = null;
        if(objetoEnviado!=null){
            game= (Game) objetoEnviado.getSerializable("game");
            String id_game = String.valueOf(game.getId_game());
            tvidgame.setText(id_game);
            etdate.setText(game.getFecha());
            tvlocalteam.setText(game.getLocal_team());
            tvvisitorteam.setText(game.getVisit_team());
            cbplayed.setChecked(game.getPlayed());
        }

    }

    private void editGame() {

        String id_team = tvidgame.getText().toString();
        String date = etdate.getText().toString();
        String tvlocal_team = tvlocalteam.getText().toString();
        String tvvisitor_team = tvvisitorteam.getText().toString();
        boolean played = cbplayed.isChecked();

        try {

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
            db.gameDao().update(date , tvlocal_team, tvvisitor_team, played,Integer.parseInt(id_team));
            Toast.makeText(this,getString(R.string.player_edited),Toast.LENGTH_SHORT).show();

        }catch (Exception e){

            Toast.makeText(this, R.string.Error,Toast.LENGTH_SHORT).show();

        }



    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btn_edit_gameEG:
                editGame();
                break;
            case R.id.btn_addLocation:
                Intent intent = new Intent(EditGamesActivity.this, AddGameLocationActivity.class);
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


}