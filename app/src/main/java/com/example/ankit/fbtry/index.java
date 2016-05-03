package com.example.ankit.fbtry;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class index extends AppCompatActivity {


    private String [] name={"news","sports","music","entertainment","government & politics","TV","food","fashion & lifestyle","fitness","Education","Companies","Anime"};
    private static ArrayList<String> topics = new ArrayList<>();
    private GridView topicgrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        /*

        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        topics.clear();
        for (int i = 0; i < name.length; ++i) {

                topics.add(name[i]);
            //Log.e("tikna",name[i]);

        }
        topicgrid = (GridView) findViewById(R.id.topicgrid);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics){
            private int[] colors = new int[] { Color.parseColor("#D2E4FC"),Color.parseColor("#ccffe6"),Color.parseColor("#ffffcc"),Color.parseColor("#ffe6ff") ,Color.parseColor("#CCF0DB") ,Color.parseColor("#e6ffb3") ,Color.parseColor("#b3ffec") ,Color.parseColor("#4dd2ff") ,Color.parseColor("#ffccff")  };
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                int colorPos = position % colors.length;
                view.setBackgroundColor(colors[colorPos]);
                return view;
            }

        };
        //Log.e("tikna",adapter.toString());
        topicgrid.setAdapter(adapter1);

        topicgrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(index.this, MainActivity.class);
                intent.putExtra("group",item);

                startActivity(intent);

            }

        });

    }
}
