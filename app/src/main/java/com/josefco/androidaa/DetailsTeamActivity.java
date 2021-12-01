package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Player;
import com.josefco.androidaa.domain.Team;
import com.josefco.androidaa.util.ImageUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsTeamActivity extends AppCompatActivity {

    TextView tvidteam, tvnameteam, tvcategory;
    ImageView ivimageteam;
    ArrayList<Team> detailsteamlist;
    public List<Player> players;
    private ArrayAdapter<Player> playerAdapter;


    //Players 2 try
    ListView listViewplayers;
    private ArrayList<String> listPlayerInfo;
    private ArrayList<Player> listplayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_team);


        //tvidteam = (TextView) findViewById(R.id.tvidteam);
        tvnameteam = (TextView) findViewById(R.id.tvnameteam);
        tvcategory = (TextView) findViewById(R.id.tvcategory);
        //ivimageteam = (ImageView) findViewById(R.id.ivImageTeam);



        Bundle objetoEnviado = getIntent().getExtras();
        Team team = null;

        if(objetoEnviado!=null){
            team= (Team) objetoEnviado.getSerializable("team");
            //String id = Integer.toString(team.getId_team());
            //tvidteam.setText(id);
            tvnameteam.setText(team.getName());
            tvcategory.setText(team.getCategory());

            //ivimageteam.setImageBitmap(ImageUtils.fromBitmapToByteArray(team.getImage()));

            Bitmap bmp = BitmapFactory.decodeByteArray(team.getImage(), 0, team.getImage().length);
            ImageView image = (ImageView) findViewById(R.id.ivImageTeam);
            image.setImageBitmap(Bitmap.createScaledBitmap(bmp, 99, 99, false));

            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),image);

            // Set ImageView image as a Bitmap
            //ivimageteam.setImageBitmap(image);

            //Picasso.get().load(team.getImage()).into(ivimageteam);

            //ivimageteam.setImageBitmap(ImageUtils.fromBitmapToByteArray(team.getImage()));
        }



        /*players = new ArrayList<>();
        ListView lvlistPlayer = findViewById(R.id.lvListPlayers);
        playerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        lvlistPlayer.setAdapter(playerAdapter);*/

        listViewplayers = (ListView) findViewById(R.id.lvListPlayers);
        getlistplayers();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listPlayerInfo);
        listViewplayers.setAdapter(adapter);

        //refreshList();

    }

    /*public void refreshList() {
        loadPlayers();
        playerAdapter.notifyDataSetChanged();
    }*/

    /*private void loadPlayers() {


        Bundle objetoEnviado = getIntent().getExtras();
        Team team = null;

        if(objetoEnviado!=null) {
            team = (Team) objetoEnviado.getSerializable("team");
            String id = Integer.toString(team.getId_team());


            players.clear();
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "team").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            players.addAll(db.playerDao().listPlayerbyTeam(Integer.parseInt(id)));

        }
    }*/

    //SEGUNDO TRY PLAYERS

    private void getlistplayers() {

        Bundle objetoEnviado = getIntent().getExtras();
        Team team = null;

        if(objetoEnviado!=null) {
            team = (Team) objetoEnviado.getSerializable("team");
            String id = Integer.toString(team.getId_team());

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "team").allowMainThreadQueries().build();

            Player player = null;
            listplayers = new ArrayList<Player>();

            Cursor cursor = db.playerDao().listPlayerbyTeam(Integer.parseInt(id));

            while (cursor.moveToNext()) {

                player = new Player();
                player.setName(cursor.getString(1));
                player.setLast_name(cursor.getString(2));
                player.setPhone(cursor.getString(3));
                player.setSquad_number(cursor.getInt(4));
                player.setName_Team(cursor.getString(5));
                player.setImage(cursor.getBlob(6));

                listplayers.add(player);
            }
        }
        getlist();
    }

    private void getlist() {

        listPlayerInfo= new ArrayList<String>();

        for (int i = 0; i<listplayers.size();i++){

            listPlayerInfo.add("Player: "+listplayers.get(i).getName()+" "
                    +listplayers.get(i).getLast_name()+" - Squad Number: "
                    +listplayers.get(i).getSquad_number());
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
            /*case R.id.addTeam:
                Intent intentAddTeamm= new Intent(this, AddTeamActivity.class);
                startActivity(intentAddTeamm);
                return true;*/
            case R.id.addPlayer:
                Intent intentAddPlayerr= new Intent(this, AddPlayerActivity.class);
                startActivity(intentAddPlayerr);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}