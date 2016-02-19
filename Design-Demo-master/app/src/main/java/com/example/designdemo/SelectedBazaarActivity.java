package com.example.designdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedBazaarActivity extends AppCompatActivity {

    Button btn_veg,btn_fruits;
    ListView veg_list;
    String[] veg={"Tomato","Cucumber"};
    String[] vegarray={"Tomato","Carrot","Brinjal","Potato"};

    HashMap<String,String> pricemap=new HashMap<String,String>();
    HashMap<String,String> valuemap=new HashMap<String,String>();
    int selected_position=0;
    Button btn_filter;
    String bazaarNameValue;
//    String[] nameslist={"Mehadipatnam","Erragadda","JNTU","Kothapeta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_bazaar);

        TextView txt_bazaar=(TextView)findViewById(R.id.txt_bazaar);

//        bazaarNameValue=getIntent().getExtras().getString("bazaarNameValue");
//        bazaarNameValue=getIntent().getStringExtra("bazaarNameValue");
        selected_position=getIntent().getIntExtra("position",2);
//        int pos=Integer.parseInt(getIntent().getStringExtra("position").toString());
//        Toast.makeText(SelectedBazaarActivity.this,"position--"+getIntent().getIntExtra("position",10),Toast.LENGTH_LONG).show();
//        Toast.makeText(SelectedBazaarActivity.this,"rythubazar name--"+Constants.nameslist[getIntent().getIntExtra("position",10)],Toast.LENGTH_LONG).show();
        txt_bazaar.setText("Welcome to "+Constants.nameslist[getIntent().getIntExtra("position",10)] +" Rythubazaar");

//		btn_veg=(Button)findViewById(R.id.btn_veg);
//		btn_fruits=(Button)findViewById(R.id.btn_fruits);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(""+Constants.nameslist[selected_position]+" Rythubazar");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        veg_list=(ListView)findViewById(R.id.veg_list);

        btn_filter=(Button) findViewById(R.id.btn_filter);

//        pricemap.put("Tomato", "10");
//        pricemap.put("Potato", "20");
//        pricemap.put("Brinjal", "15");

        for(int i=0;i<Constants.vegnamesarray.length;i++)
        {
            if(selected_position==0) {
                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price[i]);
            }else if(selected_position==1)
            {
                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price1[i]);
            }else if(selected_position==2)
            {
                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price2[i]);
            }
            else{
                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price3[i]);
            }
        }
        Toast.makeText(SelectedBazaarActivity.this,"pricemap1 size--"+pricemap.size(),Toast.LENGTH_LONG).show();
//or this can be done
//        pricemap.clear();
//        if(selected_position==0) {
//            for(int i=0;i<Constants.vegnamesarray.length;i++) {
//                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price[i]);
//            }
//        }else if(selected_position==1)
//        {
//            for(int i=0;i<Constants.vegnamesarray.length;i++) {
//                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price1[i]);
//            }
//        }else if(selected_position==2)
//        {
//            for(int i=0;i<Constants.vegnamesarray.length;i++) {
//                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price2[i]);
//            }
//        }
//        else{
//            for(int i=0;i<Constants.vegnamesarray.length;i++) {
//                pricemap.put(Constants.vegnamesarray[i].toString(), Constants.price3[i]);
//            }
//        }
//        Toast.makeText(SelectedBazaarActivity.this,"pricemap2 size--"+pricemap.size(),Toast.LENGTH_LONG).show();

        final Map<String,String> map=new TreeMap<String,String>(pricemap);

//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,veg);
//		veg_list.setAdapter(adapter);

        btn_filter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

//				MySimpleArrayAdapter adapter=new MySimpleArrayAdapter(SelectedBazaarActivity.this, map);
//				veg_list.setAdapter(adapter);

                Set<Entry<String, String>> set = map.entrySet();
                List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(set);

                Collections.sort( list, new Comparator<Entry<String, String>>()
                {
                    public int compare( Entry<String, String> o1, Entry<String, String> o2 )
                    {
                        return (o1.getValue()).compareTo( o2.getValue() );
                    }
                } );
                for(Entry<String, String> entry:list){
                    System.out.println(entry.getKey()+" ==== "+entry.getValue());
                    valuemap.put(entry.getKey().toString(), entry.getValue().toString());
                }

                //descending order valuemap

                MySimpleArrayAdapter adapter=new MySimpleArrayAdapter(SelectedBazaarActivity.this, valuemap);
                veg_list.setAdapter(adapter);
            }
        });
		MySimpleArrayAdapter adapter=new MySimpleArrayAdapter(SelectedBazaarActivity.this, pricemap);
//        MySimpleArrayAdapter adapter=new MySimpleArrayAdapter(SelectedBazaarActivity.this, map);
        veg_list.setAdapter(adapter);

        veg_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // TODO Auto-generated method stub

                //Shows clicked item value with position
                Toast.makeText(getApplicationContext(), position+1+" Position "+Constants.vegnamesarray[position] ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MySimpleArrayAdapter extends BaseAdapter {

        private final Context context;
        private final Map<String,String> values;
        LayoutInflater inflater;

        public MySimpleArrayAdapter(Context context, Map<String, String> map) {
            this.context = context;
            this.values =  map;
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return values.size();
        }

        @Override
        public String getItem(int position) {
            // TODO Auto-generated method stub
            return values.get(position);
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

                // inflating row layout
                convertView = inflater.inflate(R.layout.custom_list, null);

                // Find the child views.
                mViewHolder = new MyViewHolder();

                mViewHolder.txt_pname = (TextView) convertView.findViewById(R.id.txt_pname);
                mViewHolder.img_pname = (ImageView) convertView.findViewById(R.id.img_pname);
                mViewHolder.txt_value = (TextView) convertView.findViewById(R.id.txt_value);

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
            Set<String> keys=values.keySet();

//			for(int i=0;i<keys.size();i++)
//			{
//				keys.
//			}
//
//			for(String key:keys)
//			{
////					mViewHolder.txt_pname.setText(values.get(key));
//				mViewHolder.txt_pname.setText(key);
//			}
//			mViewHolder.txt_pname.setText(values.get(position));
            mViewHolder.txt_pname.setText((CharSequence) values.keySet().toArray()[position]);
            mViewHolder.txt_value.setText(pricemap.get((CharSequence) values.keySet().toArray()[position]));

//            mViewHolder.txt_pname.setText(Constants.vegnamesarray[position]);
//            mViewHolder.txt_value.setText(Constants.price[position]);

            mViewHolder.img_pname.setImageResource(android.R.drawable.btn_star_big_off);
//			mViewHolder.txt_value.setText(price[position]);
//			mViewHolder.txt_value.setText("10");

            return convertView;
        }

        private class MyViewHolder {
            // The child views in each row.
            TextView txt_pname;
            ImageView img_pname;
            TextView txt_value;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comparision, menu);
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
//                mDrawerLayout.openDrawer(GravityCompat.START);
                startActivity(new Intent(SelectedBazaarActivity.this,MainActivity.class));
                return true;
            case R.id.action_settings:
                return true;
            case R.id.sort_alpha:
                Toast.makeText(SelectedBazaarActivity.this,"U clicked alphabatical sorting",Toast.LENGTH_LONG).show();
                return true;
            case R.id.sort_num:
                Toast.makeText(SelectedBazaarActivity.this,"U clicked alphabatical sorting",Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
