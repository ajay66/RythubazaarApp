package com.example.designdemo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(menuItem.getTitle().toString().contains("Rythubazaar"))
                {
                    finish();
                    startActivity(new Intent(AboutUsActivity.this, MainActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Comparision"))
                {
                    finish();
                    startActivity(new Intent(AboutUsActivity.this, ComparisionActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Location"))
                {
                    finish();
                    startActivity(new Intent(AboutUsActivity.this, MapActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("bout")){

                     menuItem.setChecked(true);
//                    finish();
//                    startActivity(new Intent(AboutUsActivity.this, AboutUsActivity.class));
                }
                else
                {
                    Toast.makeText(AboutUsActivity.this,"doesnt match any activity",Toast.LENGTH_LONG).show();
                }
//                startActivity(new Intent(MainActivity.this, ComparisionActivity.class));
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_comparision, menu);
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
