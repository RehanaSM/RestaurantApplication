package com.restaurantapplication.google_map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.restaurantapplication.R;
import java.util.ArrayList;





public class GoogleMapActivity extends FragmentActivity {

    GoogleMap map;
    ArrayList<LatLng> markerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        markerPoints = new ArrayList<LatLng>();

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        double latitude = Double.valueOf(getIntent().getExtras().getString("latitude"));
        double longitude = Double.valueOf(getIntent().getExtras().getString("longitude"));


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(latitude, longitude), 16));
        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.loc_icon)).anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(latitude, longitude))); //Iasi, Romania
        try {
            map.setMyLocationEnabled(true);
        }catch (SecurityException e)
        {
            e.printStackTrace();
        }

    }


}
/*
.icon(BitmapDescriptorFactory.fromBitmap(bit))
        .position(coordinate)
        .title(info)
        ).setSnippet(name);*/
