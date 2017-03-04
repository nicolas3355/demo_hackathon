package com.superfeed.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.superfeed.android.dummy.DummyContent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        HomeFragment.OnFragmentInteractionListener, ProductListFragment.OnListFragmentInteractionListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private HomeFragment homeFragment;
    private ProductListFragment listingFragment;
    private HalfPieChartActivity donateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mapFragment = new SupportMapFragment();
        homeFragment = HomeFragment.newInstance();
        listingFragment = ProductListFragment.newInstance(2);
        donateFragment = new HalfPieChartActivity();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, homeFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment;
        String title;

        if (id == R.id.nav_home) {
            fragment = homeFragment;
            title = getString(R.string.app_name);
        } else if (id == R.id.nav_donations) {
            fragment = donateFragment;
            title = getString(R.string.nav_donations_label);
            //startActivity(new Intent(this,HalfPieChartActivity.class));
        } else if (id == R.id.nav_listing) {
            fragment = listingFragment;
            title = getString(R.string.nav_listing_label);
        } else if (id == R.id.nav_map) {
            mapFragment.getMapAsync(this);
            fragment = mapFragment;
            title = getString(R.string.nav_map_label);
        } else if (id == R.id.nav_settings) {
            return false;
        } else {
            return false;
        }

        setTitle(title);
        fragmentManager.beginTransaction()
                .replace(R.id.content_main, fragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        MapsActivity.LatLngName[] supermarkets = new MapsActivity.LatLngName[] {
                new MapsActivity.LatLngName("Spinney's Hamra", 33.8969346,35.4824569),
                new MapsActivity.LatLngName("Carrefour Baadba", 33.8613677,35.5272435),
                new MapsActivity.LatLngName("Coop Hamra", 33.8965259,35.4779778),
        };
        for (MapsActivity.LatLngName location : supermarkets) {
            mMap.addMarker(new MarkerOptions().position(location.location).title(location.name));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(supermarkets[0].location, 17));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // do nothing?
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        // do nothing?
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.ARG_PRODUCT_ID, item.id);
        startActivity(intent);
    }
}
