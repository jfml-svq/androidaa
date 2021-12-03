package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.josefco.androidaa.util.ImageUtils;
import com.squareup.picasso.Picasso;
import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Team;

public class AddTeamActivity extends AppCompatActivity {


    private Button btn_listTeamAdd, btn_editteam;

    ImageView ivimageTeamView;


    private int SELECT_PICTURE_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        btn_listTeamAdd = findViewById(R.id.btn_listTeamAdd);
        btn_editteam = findViewById(R.id.btn_editteam);

        EditText etteamname = findViewById(R.id.team_name);
        EditText etteamcategory = findViewById(R.id.team_category);
        ImageView ivimageTeamView = findViewById(R.id.ivimageteamAT);

        btn_listTeamAdd.setOnClickListener(v -> {
            Intent intentListTeams = new Intent(v.getContext(),ListTeamsActivity.class);
            startActivity(intentListTeams);
        });

        Bundle objetoEnviado = getIntent().getExtras();
        Team team = null;
        if(objetoEnviado!=null){
            team= (Team) objetoEnviado.getSerializable("team");
            String id = Integer.toString(team.getId_team());
            //tvidteam.setText(id);
            etteamname.setText(team.getName());
            etteamcategory.setText(team.getCategory());
            //ivimageTeamView.setImageBitmap(ImageUtils.fromBitmapToByteArray(team.getImage())););
            // /*editarteam();*/
            Bitmap bmp = BitmapFactory.decodeByteArray(team.getImage(), 0, team.getImage().length);
            ImageView image= (ImageView) findViewById(R.id.ivimageteamAT);
            image.setImageBitmap(Bitmap.createScaledBitmap(bmp,250,250,  false));


        }
    }


    //botones arriba activity
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btn_listTeamAdd:
                miIntent=new Intent(this,AddTeamActivity.class);
                break;
            case R.id.btn_addteam:
                addteam(view);
                break;
            case R.id.btn_editteam:
                editTeam(view);
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

    private void editTeam(View view) {

        EditText etteamname = findViewById(R.id.team_name);
        EditText etteamcategory = findViewById(R.id.team_category);
        ImageView ivimageTeamView= (ImageView) findViewById(R.id.ivimageteamAT);

        Bundle objetoEnviado = getIntent().getExtras();
        Team team = null;

        if(objetoEnviado!=null){
            team= (Team) objetoEnviado.getSerializable("team");
            String id = Integer.toString(team.getId_team());
            //tvidteam.setText(id);
            /*etteamname.setText(team.getName());
            etteamcategory.setText(team.getCategory());*/
        }

        /*String newTeamName = etteamname.getText().toString();
        String newTeamCategory = etteamcategory.getText().toString();*/

        try {

            if (etteamname.getText().toString().equals("")){
                Toast.makeText(this, getString(R.string.write_team), Toast.LENGTH_SHORT).show();
                return;
            }

            String teamname = etteamname.getText().toString();
            String teamcategory = etteamcategory.getText().toString();
            byte[] ivimageTeam = ImageUtils.fromImageViewToByteArray(ivimageTeamView);

            int id_team = team.getId_team();

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
            db.teamDao().editTeam(teamname, teamcategory , id_team, ivimageTeam);
            Toast.makeText(this,"Equipo editado",Toast.LENGTH_SHORT).show();

            etteamname.setText("");
            etteamcategory.setText("");
            ivimageTeamView.setImageResource(android.R.color.transparent);
        } catch(Exception e) {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }


    public void addteam(View view){

        EditText etteamname = findViewById(R.id.team_name);
        EditText etteamcategory = findViewById(R.id.team_category);
        ImageView ivimageTeamView = findViewById(R.id.ivimageteamAT);

        if (etteamname.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.write_team), Toast.LENGTH_SHORT).show();
            return;
        }

        String teamname = etteamname.getText().toString();
        String teamcategory = etteamcategory.getText().toString();
        byte[] ivimageTeam = ImageUtils.fromImageViewToByteArray(ivimageTeamView);

        int id_team = 0;
        Team team = new Team(id_team, teamname, teamcategory, ivimageTeam);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
        db.teamDao().insert(team);
        Toast.makeText(this,"Equipo añadido",Toast.LENGTH_SHORT).show();

        etteamname.setText("");
        etteamcategory.setText("");
        ivimageTeamView.setImageResource(android.R.color.transparent);
    }

    public void selectPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
        if ((requestCode == 1) && (resultCode == RESULT_OK)
                && (data != null)) {
            Picasso.get().load(data.getData()).noPlaceholder().centerCrop().fit()
                    .into((ImageView) findViewById(R.id.ivimageteamAT));
        }else if ((requestCode == 2) && (resultCode == RESULT_OK)&&(data != null)) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView ivimageTeamView = findViewById(R.id.ivimageteamAT);
            ivimageTeamView.setImageBitmap(imageBitmap);
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