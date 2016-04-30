package com.example.ankit.fbtry;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ArrayList<String> data = new ArrayList<String>();
        Bundle extras = getIntent().getExtras();

        String x = extras.getString("datacontent");
        String inter = x;
        if (x.contains("http")) {
            int y = x.indexOf("http");
            String sub = x.substring(0, y);

            String remainder = x.substring(y);
            int last = remainder.indexOf("\n");
            String mainlink=remainder.substring(0,last);
            String link = remainder.substring(last);


            //inter = sub + "Html.fromHtml(<a href=" + mainlink + ">Link</a>)" + link;
            data.add(extras.getString("datalist"));
            if(sub.length()>3)
            data.add(sub);
            data.add(mainlink);
            //data.add("<a href=\"" + mainlink + "\">Link</a>)");

            data.add(link);
        }
        else {

            data.add(extras.getString("datalist"));
            data.add(extras.getString("datacontent"));
        }


        ListView listview = (ListView) findViewById(R.id.display);
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);
       // listview.setMovementMethod(LinkMovementMethod.getInstance());

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {


                String item = (String) parent.getItemAtPosition(position);

                if (item.contains("http"))
                //Toast.makeText(details.this,
                //     "hmm hmm go on and click this link", Toast.LENGTH_LONG).show();
                {

                /*
                */
                    List<String> extractedUrls = extractUrls(item);


                    Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(extractedUrls.get(0)));
                    startActivity(myWebLink);
                }

            }

        });
    }

    public static List<String> extractUrls(String text)
    {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        private int[] colors = new int[] { Color.parseColor("#D2E4FC"),Color.parseColor("#F0F0F0"),Color.parseColor("#D2E4FC"),Color.parseColor("#F0F0F0")  };
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
