package com.example.designdemo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class LocationActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("Comparision Activity");


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                Toast.makeText(LocationActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                if(menuItem.getTitle().toString().contains("Rythubazaar"))
                {
                    finish();
                    startActivity(new Intent(LocationActivity.this, MainActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Comparision"))
                {
                    finish();
                    startActivity(new Intent(LocationActivity.this, ComparisionActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Location"))
                {
//                    finish();
//                    startActivity(new Intent(LocationActivity.this, LocationActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("bout")){
                    finish();
                    startActivity(new Intent(LocationActivity.this, AboutUsActivity.class));
                }
                else
                {
                    Toast.makeText(LocationActivity.this,"doesnt match any activity",Toast.LENGTH_LONG).show();
                }
//                startActivity(new Intent(MainActivity.this, ComparisionActivity.class));
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
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
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}