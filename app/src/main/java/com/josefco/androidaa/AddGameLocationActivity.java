package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.josefco.androidaa.db.AppDatabase;
import com.josefco.androidaa.domain.Game;

public class AddGameLocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_add);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }
    private boolean contadorMarker = true;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnMapLongClickListener(this);

        Bundle objetoEnviado = getIntent().getExtras();
        Game game = null;
        if(objetoEnviado!=null) {
            game = (Game) objetoEnviado.getSerializable("game");

            LatLng partido = new LatLng(game.getLatitudeGame(), game.getLongitudeGame());
            Marker nuevoMarker;

            if ((game.getLatitudeGame() == 0.0) && (game.getLongitudeGame() == 0.0)) {
                nuevoMarker = map.addMarker(new MarkerOptions().position(partido)
                        .title(getString(R.string.game_num) + (game.getId_game()))
                        .snippet(getString(R.string.local) + game.getLocal_team() + getString(R.string.vs) + game.getVisit_team() + getString(R.string.visitor)));
                nuevoMarker.remove();
                contadorMarker = true;

            }else{
                map.addMarker(new MarkerOptions().position(partido)
                        .title(getString(R.string.game_num) + (game.getId_game()))
                        .snippet(getString(R.string.local) + game.getLocal_team() + getString(R.string.vs) + game.getVisit_team() + getString(R.string.visitor)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(partido));
                contadorMarker = false;
            }

        }
    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        Bundle objetoEnviado = getIntent().getExtras();
        Game game = null;
        if(objetoEnviado!=null){
            game= (Game) objetoEnviado.getSerializable("game");

            while (contadorMarker){

                map.addMarker(new MarkerOptions().position(latLng));

                double latitud = latLng.latitude;
                double longitud = latLng.longitude;

                System.out.println("lat "+latitud);
                System.out.println("lon " +longitud);
                contadorMarker = false;
            }
            Toast.makeText(this, R.string.one_marker,Toast.LENGTH_SHORT).show();
            try {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "team").allowMainThreadQueries().build();
                db.gameDao().insertLocation(latLng.latitude,latLng.longitude, game.getId_game());
                Toast.makeText(this, R.string.game_location_created,Toast.LENGTH_SHORT).show();

            }catch (Exception e){

                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }
        }
    }
}