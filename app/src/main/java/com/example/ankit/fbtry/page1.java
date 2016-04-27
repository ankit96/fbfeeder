package com.example.ankit.fbtry;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        Bundle extras = getIntent().getExtras();

// Extract data using passed keys
        String response = extras.getString("data");

        String responselist[]=extras.getStringArray("datalist");

        //Log.d("tikna1",responselist[0]);
            /*
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, responselist);

            ListView listView = (ListView) findViewById(R.id.datalist);
            listView.setAdapter(adapter);
            */
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < responselist.length; ++i) {
            if(responselist[i]!=null)
            list.add(responselist[i]);
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
                    view.animate().setDuration(2000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    list.remove(item);
                                    adapter.notifyDataSetChanged();
                                    view.setAlpha(1);
                                }
                            });
                }

            });
        }







    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
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
