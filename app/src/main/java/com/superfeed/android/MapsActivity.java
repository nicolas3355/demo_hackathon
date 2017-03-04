package com.superfeed.android;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public static class LatLngName {
        LatLng location;
        String name;
        public LatLngName(String name, double lat, double lng) {
            this.name = name;
            this.location = new LatLng(lat, lng);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLngName[] supermarkets = new LatLngName[] {
                new LatLngName("Spinney's Hamra", 33.8969346,35.4824569),
                new LatLngName("Carrefour Baadba", 33.8613677,35.5272435),
                new LatLngName("Coop Hamra", 33.8965259,35.4779778),
        };
        for (LatLngName location : supermarkets) {
            mMap.addMarker(new MarkerOptions().position(location.location).title(location.name));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(supermarkets[0].location, 17));
    }
}
