package com.example.designdemo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    LatLng MehadipatnamRaithubazar=new LatLng(17.3959,78.4312);
    LatLng ErragaddaRaithubazar=new LatLng(17.4487,78.4377);
    LatLng JNTURaithubazar=new LatLng(17.4952,78.393);
    LatLng KothapetaRaithubazar=new LatLng(17.3703,78.5462);
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setUpMapIfNeeded();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

//                menuItem.setChecked(false);
                if(menuItem.getTitle().toString().contains("Rythubazaar"))
                {
                    finish();
                    menuItem.setChecked(true);
                    startActivity(new Intent(MapActivity.this, MainActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Comparision"))
                {
                    menuItem.setChecked(true);
                    startActivity(new Intent(MapActivity.this, ComparisionActivity.class));
                    finish();
                }
                else if(menuItem.getTitle().toString().contains("Location"))
                {
                    menuItem.setChecked(true);
//                    startActivity(new Intent(MainActivity.this, LocationActivity.class));
//                    startActivity(new Intent(MapActivity.this, LocationActivity.class));
//                    finish();
                }
                else if(menuItem.getTitle().toString().contains("bout")){
//                    startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                    startActivity(new Intent(MapActivity.this, AboutUsActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(MapActivity.this, "doesnt match any activity", Toast.LENGTH_LONG).show();
                }
//                startActivity(new Intent(MainActivity.this, ComparisionActivity.class));
                return true;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Toast.makeText(MapActivity.this,"Title--"+marker.getTitle(),Toast.LENGTH_LONG).show();
                    Toast.makeText(MapActivity.this,"marker.getId()--"+marker.getId(),Toast.LENGTH_LONG).show();
                    Toast.makeText(MapActivity.this,"text--"+marker.getTitle().split(" ")[0],Toast.LENGTH_LONG).show();
                    String s = marker.getTitle().split(" ")[0];

                    Intent intent=new Intent(MapActivity.this,SelectedBazaarActivity.class);
                    if(s.contains("ehadipatnam")) {
                        intent.putExtra("position", 0);
                    }
                    else  if(s.contains("rragadda")) {
                        intent.putExtra("position", 1);
                    }
                    else  if(s.contains("JNTU")) {
                        intent.putExtra("position", 2);
                    }
                    else  {
                        intent.putExtra("position", 3);
                    }
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(17.235416, 78.251463)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(MehadipatnamRaithubazar).title("Mehadipatnam Raithubazar"));
        mMap.addMarker(new MarkerOptions().position(ErragaddaRaithubazar).title("Erragadda Raithubazar"));
        mMap.addMarker(new MarkerOptions().position(JNTURaithubazar).title("JNTU Raithubazar"));
        mMap.addMarker(new MarkerOptions().position(KothapetaRaithubazar).title("Kothapeta Raithubazar"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MehadipatnamRaithubazar, 11.3f));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
