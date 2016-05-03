package com.example.ankit.fbtry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class page1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);



        }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();

// Extract data using passed keys
        // String response = extras.getString("data");

        final String responselist[]=extras.getStringArray("datalist");
        final String responsestring[]=extras.getStringArray("datacontent");
        //Log.d("tikna1",responselist[0]);
            /*
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, responselist);

            ListView listView = (ListView) findViewById(R.id.datalist);
            listView.setAdapter(adapter);

            */
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        for (int i = 0; i < 10; ++i) {
            if(responselist[i]!=null&&responselist[i].length()>3)
                list.add(responselist[i]);
            else
                list.add("Content");
            if(responsestring[i]!=null&&responsestring[i].length()>3)
                list2.add(responsestring[i]);
            else
                list2.add(".");
            /*
            if(responselist[i]!=null&&responselist[i].length()>3)
            list.add(responselist[i]);
            else if(responselist[i]!=null&&responselist[i].length()<=3&&responsestring[i]!=null)
                list.add(responsestring[i]);

            if(responsestring[i]!=null&&responsestring[i].length()>3)
                list2.add(responsestring[i]);
            else if(responsestring[i]!=null&&responsestring[i].length()<=3)
                list2.add(responselist[i]);
                */

        }

        ListView listview = (ListView) findViewById(R.id.datalist);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {


                final String item = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(page1.this, details.class);
                //intent.putExtra("data", msg);
                intent.putExtra("datalist", list.get(position));

                intent.putExtra("datacontent", list2.get(position));
                startActivity(intent);
                    /*view.animate().setDuration(2000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    list.remove(item);
                                    adapter.notifyDataSetChanged();
                                    view.setAlpha(1);
                                }
                            });
                            */
            }

        });

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        private int[] colors = new int[]{Color.parseColor("#D2E4FC"), Color.parseColor("#ccffe6"), Color.parseColor("#ffffcc"), Color.parseColor("#ffe6ff"), Color.parseColor("#CCF0DB"), Color.parseColor("#e6ffb3"), Color.parseColor("#b3ffec"), Color.parseColor("#4dd2ff"), Color.parseColor("#ffccff")};

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            int colorPos = position % colors.length;
            view.setBackgroundColor(colors[colorPos]);
            return view;
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }




}
