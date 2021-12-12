package com.josefco.androidaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.josefco.androidaa.domain.Game;

public class SeeGameLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_game_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_see);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

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
                Toast.makeText(this, R.string.game_need_location, Toast.LENGTH_SHORT).show();

            } else {
                map.addMarker(new MarkerOptions().position(partido)
                        .title(getString(R.string.game_num) + (game.getId_game()))
                        .snippet(getString(R.string.local) + game.getLocal_team() + " vs " + game.getVisit_team() + getString(R.string.visitor)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(partido));
            }
        }
    }
}