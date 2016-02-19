package com.example.designdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ComparisionActivity extends AppCompatActivity {

    String[] name={"Rythubazaar1","Rythubazaar2"};
//    String[] price={"20","30"};
    private DrawerLayout mDrawerLayout;
    HashMap<String,String> hm=new HashMap<String,String>();
    HashMap<String,String> hm1=new HashMap<String,String>();
    HashMap<String,String> hm2=new HashMap<String,String>();
    HashMap<String,String> hm3=new HashMap<String,String>();
    ListView list_compare;
    String selected_item="tomato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparision);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Comparing Prices");
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        setHashMap();
        final TextView txt_select_name= (TextView) findViewById(R.id.txt_select_name);
        for(int i=0;i<Constants.vegnamesarray.length;i++)
        {
            hm.put(Constants.vegnamesarray[i].toString(),Constants.price[i]);
            hm1.put(Constants.vegnamesarray[i].toString(),Constants.price1[i]);
            hm2.put(Constants.vegnamesarray[i].toString(),Constants.price2[i]);
            hm3.put(Constants.vegnamesarray[i].toString(),Constants.price3[i]);
        }

        txt_select_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ComparisionActivity.this,AlertDialog.THEME_HOLO_LIGHT);
                // builder.setInverseBackgroundForced(true);
                builder.setTitle("Vegetables List");
                // Collections.sort(dateslist);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,Constants.vegnamesarray) {

                    public View getView(int position, View convertView,android.view.ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);

                        return view;
                    }
                };
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        selected_item = Constants.vegnamesarray[item];
                        txt_select_name.setText(selected_item);

                        MySimpleArrayAdapter adapter=new MySimpleArrayAdapter(ComparisionActivity.this,Constants.nameslist);
                        list_compare.setAdapter(adapter);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });



//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("Comparision Activity");

        list_compare=(ListView)findViewById(R.id.list_compare);
        MySimpleArrayAdapter adapter=new MySimpleArrayAdapter(ComparisionActivity.this,Constants.nameslist);
        list_compare.setAdapter(adapter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(menuItem.getTitle().toString().contains("Rythubazaar"))
                {
                    finish();
                    startActivity(new Intent(ComparisionActivity.this, MainActivity.class));

                }
                else if(menuItem.getTitle().toString().contains("Comparision"))
                {
//                    finish();
//                    startActivity(new Intent(ComparisionActivity.this, ComparisionActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Location"))
                {
                    finish();
                    startActivity(new Intent(ComparisionActivity.this, MapActivity.class));
                }
                else if(menuItem.getTitle().toString().contains("Location")){
                    finish();
                    startActivity(new Intent(ComparisionActivity.this, AboutUsActivity.class));
                }
                else
                {
                    Toast.makeText(ComparisionActivity.this,"doesnt match any activity",Toast.LENGTH_LONG).show();
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


    public void setHashMap()
    {
        for(int i=0;i<Constants.vegnamesarray.length;i++)
        {
            hm.put(Constants.vegnamesarray[i].toString(),Constants.price[i]);
            hm1.put(Constants.vegnamesarray[i].toString(),Constants.price1[i]);
            hm2.put(Constants.vegnamesarray[i].toString(),Constants.price2[i]);
            hm3.put(Constants.vegnamesarray[i].toString(),Constants.price3[i]);
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
            // TODO Auto-generated method stub
            return values[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder;

            // Create a new row view
            if (convertView == null) {

                convertView = inflater.inflate(R.layout.custom_list_compare, null);
                mViewHolder = new MyViewHolder();
                mViewHolder.txt_compare_name = (TextView) convertView.findViewById(R.id.txt_compare_name);
                mViewHolder.txt_compare_price = (TextView) convertView.findViewById(R.id.txt_compare_price);
                convertView.setTag(mViewHolder);
            } // Reuse existing row view
            else {
                mViewHolder = (MyViewHolder) convertView.getTag();
            }
            // Find the child views.
            mViewHolder.txt_compare_name.setText(Constants.nameslist[position]);

//            String str="tomato";
//            HashMap<String,String> map1;
//            HashMap<String,String> map = null;
//            map=Constants.getHashMap(position);
//            mViewHolder.txt_compare_price.setText(""+map.get(str));
//            mViewHolder.txt_compare_price.setText(""+Constants.getHashMap(position).get(str));
//            Toast.makeText(ComparisionActivity.this,"position"+position+"map size"+Constants.getHashMap(position).size(),Toast.LENGTH_LONG).show();

            if(position==0)
            {
                mViewHolder.txt_compare_price.setText(hm.get(selected_item));
            }
            else if(position==1)
            {
                mViewHolder.txt_compare_price.setText(hm1.get(selected_item));
            }

            else if(position==2)
            {
                mViewHolder.txt_compare_price.setText(hm2.get(selected_item));
            }
            else
            {
                mViewHolder.txt_compare_price.setText(hm3.get(selected_item));
            }
//            if(position==0)
//            {
////                mViewHolder.txt_compare_price.setText(price[position]);
////                mViewHolder.txt_compare_price.setText(Constants.price[pos]);
//                map1=Constants.getHashMap(position);
//                mViewHolder.txt_compare_price.setText(""+map1.get(str));
//                Toast.makeText(ComparisionActivity.this,"position"+position+"map size"+map1.size(),Toast.LENGTH_LONG).show();
//            }
//            else if(position==1)
//            {
////                mViewHolder.txt_compare_price.setText(price1[position]);
////                mViewHolder.txt_compare_price.setText(Constants.price1[pos]);
//                map1=Constants.getHashMap(position);
//                mViewHolder.txt_compare_price.setText(""+map1.get(str));
//                Toast.makeText(ComparisionActivity.this,"position"+position+"map size"+map1.size(),Toast.LENGTH_LONG).show();
//            }else if(position==2)
//
//            {
////                mViewHolder.txt_compare_price.setText(price2[position]);
////                mViewHolder.txt_compare_price.setText(Constants.price2[pos]);
//                map1=Constants.getHashMap(position);
//                mViewHolder.txt_compare_price.setText(""+map1.get(str));
//                Toast.makeText(ComparisionActivity.this,"position"+position+"map size"+map1.size(),Toast.LENGTH_LONG).show();
//            }
//            else{
////                mViewHolder.txt_compare_price.setText(price3[position]);
////                mViewHolder.txt_compare_price.setText(Constants.price3[pos]);
//                map1=Constants.getHashMap(position);
//                mViewHolder.txt_compare_price.setText(""+map1.get(str));
//                Toast.makeText(ComparisionActivity.this,"position"+position+"map size"+map1.size(),Toast.LENGTH_LONG).show();
//            }

//            mViewHolder.txt_compare_price.setText(Constants.price[position]);


            return convertView;
        }

        private class MyViewHolder {
            // The child views in each row.
            TextView txt_compare_name,txt_compare_price;
        }
    }
}
