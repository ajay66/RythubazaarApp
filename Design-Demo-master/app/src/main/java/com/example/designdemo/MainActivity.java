package com.example.designdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

//    HashMap<String,String> hmap1=new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        startActivity(new Intent(this,MapActivity.class));

//        startActivity(new Intent(this,LoginActivity.class));

//        startActivity(new Intent(this,SecondActivity.class));

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
//                    finish();
//                    menuItem.setChecked(true);
//                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Comparision"))
                {
                    menuItem.setChecked(true);
                    startActivity(new Intent(MainActivity.this, ComparisionActivity.class));
                    finish();
                }
                else if(menuItem.getTitle().toString().contains("Location"))
                {
                    menuItem.setChecked(true);
//                    startActivity(new Intent(MainActivity.this, LocationActivity.class));
                    startActivity(new Intent(MainActivity.this, MapActivity.class));
                    finish();
                }
                else if(menuItem.getTitle().toString().contains("bout")){
//                    startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                    startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"doesnt match any activity",Toast.LENGTH_LONG).show();
                }
//                startActivity(new Intent(MainActivity.this, ComparisionActivity.class));
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.coordinator), "Get My Current Location", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Your Current Location is ", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(MainActivity.this,SecondActivity.class));
                    }
                }).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.coordinator), "Go to Second Activity", Snackbar.LENGTH_LONG).setAction("Go", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,SecondActivity.class));
                    }
                }).show();
            }
        });

//        DesignDemoPagerAdapter adapter = new DesignDemoPagerAdapter(getSupportFragmentManager());
//        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
//        viewPager.setAdapter(adapter);
//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
//        tabLayout.setupWithViewPager(viewPager);

        GridView grid=(GridView)findViewById(R.id.grid);
        MySimpleArrayAdapter adapter1=new MySimpleArrayAdapter(MainActivity.this,Constants.nameslist);
        grid.setAdapter(adapter1);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this,"clicked grid item--"+view.toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,"int value--"+i,Toast.LENGTH_LONG).show();
//                startActivity(new Intent(MainActivity.this,SelectedBazaarActivity.class));

                Intent intent = new Intent (MainActivity.this,SelectedBazaarActivity.class);
//                intent.putExtra("bazaarNameValue",Constants.nameslist[i]);
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DesignDemoFragment extends Fragment {
        private static final String TAB_POSITION = "tab_position";

        public DesignDemoFragment() {
        }

        public static DesignDemoFragment newInstance(int tabPosition) {
            DesignDemoFragment fragment = new DesignDemoFragment();
            Bundle args = new Bundle();
            args.putInt(TAB_POSITION, tabPosition);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Bundle args = getArguments();
            int tabPosition = args.getInt(TAB_POSITION);

            ArrayList<String> items = new ArrayList<String>();
            for (int i = 0; i < 50; i++) {
                items.add("Tab #" + tabPosition + " item #" + i);
            }

            View v =  inflater.inflate(R.layout.fragment_list_view, container, false);
            RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(new DesignDemoRecyclerAdapter(items));

            return v;
        }
    }

    static class DesignDemoPagerAdapter extends FragmentStatePagerAdapter {

        public DesignDemoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DesignDemoFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }
    }

    public class MySimpleArrayAdapter extends BaseAdapter {

        private final Context context;
        private final String[] values;
        LayoutInflater inflater;

        public MySimpleArrayAdapter(Context context, String[] values) {
            this.context = context;
            this.values = values;
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return values.length;
        }

        @Override
        public String getItem(int position) {
            return values[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder;

            // Create a new row view
            if (convertView == null) {

                // inflating row layout
                convertView = inflater.inflate(R.layout.custom_grid, null);

                // Find the child views.
                mViewHolder = new MyViewHolder();

                mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.txt_grid);
                mViewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.img_grid);

                // Optimization: Tag the row with it's child views, so we don't
                // have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(mViewHolder);

            } // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call
                // findViewById().
                mViewHolder = (MyViewHolder) convertView.getTag();
            }
            // Find the child views.
            mViewHolder.tvTitle.setText(values[position]);

//                mViewHolder.ivIcon.setImageResource(android.R.drawable.btn_star_big_off);
            if(position%2==0)
            {
                mViewHolder.ivIcon.setImageResource(R.drawable.r1);
            }
            else
            {
                mViewHolder.ivIcon.setImageResource(R.drawable.r1);
            }

            return convertView;
        }

        private class MyViewHolder {
            // The child views in each row.
            TextView tvTitle;
            ImageView ivIcon;
        }
    }
}

