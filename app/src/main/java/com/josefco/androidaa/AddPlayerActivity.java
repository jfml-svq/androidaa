package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Player;
import com.josefco.androidaa.domain.Team;
import com.josefco.androidaa.util.ImageUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AddPlayerActivity extends AppCompatActivity {


    Spinner spinnerTeams;
    EditText etNamePlayer, etLastNamePlayer, etPhone, etSquadNumber;
    TextView tvTeam;
    ImageView ivimagePlayer;
    ArrayList<String> ListTeamsSpinner;
    ArrayList<Team> TeamsListSpinner;

    private int SELECT_PICTURE_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);


        spinnerTeams = findViewById(R.id.spTeams);
        etNamePlayer = findViewById(R.id.etNamePlayer);
        etLastNamePlayer = findViewById(R.id.etLastNamePlayer);
        ivimagePlayer = findViewById(R.id.image_player);
        etPhone = findViewById(R.id.etPhone);
        etSquadNumber = findViewById(R.id.etSquadNumber);
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
            String squad_number = Integer.toString(player.getSquad_number());
            etSquadNumber.setText(squad_number);
            tvTeam.setText(player.getName_Team());

            Bitmap bmp = BitmapFactory.decodeByteArray(player.getImage(), 0, player.getImage().length);
            ImageView image= (ImageView) findViewById(R.id.image_player);
            image.setImageBitmap(Bitmap.createScaledBitmap(bmp,250,250,  false));
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
        String squad_number = etSquadNumber.getText().toString();
        ImageView ivimagePlayerView = findViewById(R.id.image_player);




        int id_player = 0;
        final String team_name;
        //SPINNERNENRNENRER
        /*spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/

        byte[] ivimagePlayer = ImageUtils.fromImageViewToByteArray(ivimagePlayerView);

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



            Player player = new Player(id_player, namePlayer, lastNamePlayer, phonePlayer, team_name, Integer.parseInt(squad_number), ivimagePlayer);

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
            db.playerDao().insert(player);

            Toast.makeText(this, getString(R.string.player_added),Toast.LENGTH_SHORT).show();

            etNamePlayer.setText("");
            etLastNamePlayer.setText("");
            etPhone.setText("");
            tvTeam.setText("");
            etSquadNumber.setText("");
            rellenarSpinner();
            ivimagePlayerView.setImageResource(android.R.color.transparent);

        }else{

            Toast.makeText(getApplicationContext(), getString(R.string.choose_team),Toast.LENGTH_LONG).show();
        }
    }

    private void editPlayer(View view) {

        etNamePlayer = findViewById(R.id.etNamePlayer);
        etLastNamePlayer = findViewById(R.id.etLastNamePlayer);
        etPhone = findViewById(R.id.etPhone);
        etSquadNumber = findViewById(R.id.etSquadNumber);
        ImageView ivimagePlayerView = findViewById(R.id.image_player);

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
            String squad_number = etSquadNumber.getText().toString();
            byte[] ivImagePlayer = ImageUtils.fromImageViewToByteArray(ivimagePlayerView);
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

                //byte[] ivimageTeam = ImageUtils.fromImageViewToByteArray(ivimagePlayer);

                int id_team = player.getId_player();

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
                db.playerDao().editPlayer(playerName, playerLastName , playerPhone, team_name, id_team, Integer.parseInt(squad_number), ivImagePlayer);
                Toast.makeText(this,"Player edited",Toast.LENGTH_SHORT).show();

                etNamePlayer.setText("");
                etLastNamePlayer.setText("");
                etPhone.setText("");
                tvTeam.setText("");
                etSquadNumber.setText("");
                rellenarSpinner();
            }else{
                Toast.makeText(getApplicationContext(), getString(R.string.choose_team),Toast.LENGTH_LONG).show();
            }


        } catch(Exception e) {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }

    }

    //botones abajo activity
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
            case R.id.btn_addImage:
                selectPicture(view);
                break;
            case R.id.btn_addPhoto:
                openCamera();
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

    public void selectPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //startActivityForResult(intent, SELECT_PICTURE_RESULT);
        startActivityForResult(intent, 1);
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 2);
        //}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1/*SELECT_PICTURE_RESULT*/) && (resultCode == RESULT_OK)
                && (data != null)) {
            Picasso.get().load(data.getData()).noPlaceholder().centerCrop().fit()
                    .into((ImageView) findViewById(R.id.image_player));

        }else if ((requestCode == 2) && (resultCode == RESULT_OK)&&(data != null)) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivimagePlayer.setImageBitmap(imageBitmap);
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