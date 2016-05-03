package com.example.ankit.fbtry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity //implements View.OnClickListener
{


    String USER_AGENT = "Mozilla/5.0";
    String responsedata;
   // Button nytimes,toi,facebook,cristiano,shakira,eminem,sachin,ghosal,bachchan,grid;
    String list[]=new String[100];
    String content[]=new String[100];
    String Edata[]=new String[100];
    private ProgressBar spinner;

    //private String [] name={"nytimes","TOI","facebook","cr7","eminem","shreya","sachin","amitabh"};
    //private   String [] pageid={"nytimes","TimesofIndia","facebook","cristiano","eminem","shreyaghoshal","SachinTendulkar","AmitabhBachchan"};

    private String [] newsname={"nytimes(USA)","TOI(IND)","BBC","thehindu(IND)","thegaurdian(UK)","Wall Street journal(USA)","Washington Post(USA)","China Daily(CHN)","Sydney morning herald(AUS)","Asahi shimbun(JPN)","Dawn(PAK)"};
    private String [] newsid={"nytimes","TimesofIndia","bbcnews","thehindu","theguardian","wsj","TheWashingtonTimes","chinadaily","sydneymorningherald","asahicom","dawndotcom"};

    private String [] sportsname={"CricTracker","SportsKeeda","cricbuzz","Cricinfo","cr7","sachin"};
    private String [] sportsid={"CricTrackerIndia","SportsKeeda","cricbuzz","Cricinfo","cristiano","SachinTendulkar"};

    private String [] musicname={"eminem","shreya","jervyhou","paradigmshift","bryanadams","YOYOhoneysingh","AtifAslam","backstreetboys","AKON","MJ","Eagles","TaylorSwift","katyperry"};
    private String [] musicid={"eminem","shreyaghoshal","jervyhou","paradigmshiftraaga","bryanadamsofficial","YOYOhoneysingh","AtifAslamOfficialFanPage","backstreetboys","AKON","michaeljackson","EaglesBand","TaylorSwift","katyperry"};

    private String [] entertainmentname={"ChaaiBreak","passionconnect","Quoralicious","Lunarbaboon","TheBestMovieLines","DIE","9gag","TVF","AIB","Sarcastic","BC Billi","aapengineerhain","ROFLIndia","thebackbenchers","fakingnews","cartoon network"};
    private String [] entertainmentid={"ChaaiBreak","passionconnect.in","Quoralicious","Lunarbaboon","TheBestMovieLines","6DIE9","9gag","sabqtiyapahai","IndiaBakchod","SarcasticWorld","BC.Billi","aapengineerhain","ROFLIndia","thebackbenchersofficial","fakingnews","MissOldCartoonNetwork"};

    private String [] politicsname={"cnn","worldpolitics"};
    private String [] politicsid={"cnnpolitics","worldpoliticsreview"};

    private String [] foodname={"Kitkat","KFC","monginis","Dominos","pizzahut"};
    private String [] foodid={"KitKatIndia","KFCIndia","monginis","Dominos","pizzahutindia"};

    private String [] tvname={"TheMentalist","xmen","lordoftherings","TheIncredibles","TBBT","ZNMD","stepup","PiratesoftheCaribbean","TheLionKing","TheMask","Hulk","Rocky","jackie chan","Discovery","bindass"};
    private String [] tvid={"TheMentalist","xmenmoviesindia","lordoftheringstrilogy","PixarTheIncredibles","TheBigBangTheory","ZNMDthemovie","stepupmovie","PiratesoftheCaribbean","TheLionKingSimba","TheMaskOfficial","Hulk","RockyMovie","jackie","Discovery","bindass"};

    private String [] fashionname={"cars","BuckarooLifestyle"};
    private String [] fashionid={"valvethebond","BuckarooLifestyle"};

    private String [] fitnessname={"frankmedrano99","fitnessblender","FitnessLovrs"};
    private String [] fitnessid={"frankmedrano99","fitnessblender","FitnessLovrs"};

    private String [] comanyname={"gameeon","BucketBolt","pykih","PepsiIndia"};
    private String [] companyid={"gameeon","BucketBolt","pykih","PepsiIndia"};

    private String [] eduname={"khanacademy","MyCodeSchool","topcoder","hackerrank","HackerEarth","codeforces","CodeChef","cpptovlee","wtf facts","TechNews","Thoughts"};
    private String [] eduid={"khanacademy","MyCodeSchool","topcoder","hackerrank","HackerEarth","codeforces","CodeChef","cpptovlee","facts.wtf","TechNewsFanPage","GPeopleGThoughts"};

    private String [] animename={"GokuAndNaruto","DBSuper"};
    private String [] animeid={"GokuAndNaruto","OfficialDBSuperPage"};


    Map<String, String> map = new HashMap<String, String>();


    private static ArrayList<String> ArrayofName = new ArrayList<String>();
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();

// Extract data using passed keys
        String response = extras.getString("group");
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        switch (response) {
//private String [] name={"news","sports","music","entertainment","government & politics","TV","food","fashion & lifestyle"};
            case "news":
                taskdoer(newsname,newsid);
                break;

            case "sports":
                taskdoer(sportsname,sportsid);
                break;

            case "music":
                taskdoer(musicname,musicid);
                break;

            case "entertainment":
                taskdoer(entertainmentname,entertainmentid);
                break;

            case "government & politics":
                taskdoer(politicsname,politicsid);
                break;

            case "TV":
                taskdoer(tvname,tvid);
                break;

            case "food":
                taskdoer(foodname,foodid);
                break;

            case "fashion & lifestyle":
                taskdoer(fashionname,fashionid);
                break;

            case "fitness":
                taskdoer(fitnessname,fitnessid);
                break;

            case "Education":
                taskdoer(eduname,eduid);
                break;

            case "Companies":
                taskdoer(comanyname,companyid);
                break;

            case "Anime":
                taskdoer(animename,animeid);
                break;




            default:
                break;
        }

    }

        public void taskdoer(String[] name,String[] pageid)
        {

            ArrayofName.clear();
            for (int i = 0; i < name.length; ++i) {
                if (name[i] != null )
                    ArrayofName.add(name[i]);
                map.put(name[i],pageid[i] );
            }
            gridView = (GridView) findViewById(R.id.maingrid);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, ArrayofName) {
                private int[] colors = new int[]{Color.parseColor("#D2E4FC"), Color.parseColor("#ccffe6"), Color.parseColor("#ffffcc"), Color.parseColor("#ffe6ff"), Color.parseColor("#CCF0DB"), Color.parseColor("#e6ffb3"), Color.parseColor("#b3ffec"), Color.parseColor("#4dd2ff"), Color.parseColor("#ffccff")};

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    int colorPos = position % colors.length;
                    view.setBackgroundColor(colors[colorPos]);
                    return view;
                }


            };

            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);

                    spinner.setVisibility(View.VISIBLE);
                    fetchdata(map.get(item));

                }

            });
        }
        public void handleMessage(String msg) {
            //Log.d("tikna","1  "+msg);
            Intent intent = new Intent(MainActivity.this, page1.class);

            //intent.putExtra("data", msg);
            intent.putExtra("datalist",list);
            intent.putExtra("datacontent", content);

            startActivity(intent);


        }


    public void fetchdata(final String page)
    {

        Log.e("tikna",page);
        final String url = "https://postscrapper.herokuapp.com/post";

        new Thread() {
            public void run() {


                try {
                    //spinner = (ProgressBar)findViewById(R.id.progressBar1);

                    URL obj = new URL(url);
                    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

                    //add reuqest header
                    con.setRequestMethod("POST");
                    con.setRequestProperty("User-Agent", USER_AGENT);
                    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                    String urlParameters = "text="+page;

                    // Send post request
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();

                    int responseCode = con.getResponseCode();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    int i=0;
                    list[0]="";
                   content[0]="";
                    Edata[0]="";
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        if(!inputLine.equals(""))
                        {
                            if(!inputLine.contains(":")&&!inputLine.contains("-")&&!inputLine.contains("/"))
                            list[i] =list[i]+"\n\n"+ inputLine;
                            else
                            content[i]=content[i]+"\n\n"+inputLine;


                            if(inputLine.contains(":")&&inputLine.contains("-")&&!inputLine.contains("/"))
                            {i=i+1;
                            list[i]="";
                                content[i]="";
                                Edata[i]="";

                            }
                        }


                    }


                    //print result

                   Log.e("tikna", response.toString());
                    //print result
                    handleMessage(response.toString());
                    in.close();

                    responsedata=response.toString();
                    //datatext.setText(responsedata.toString());
                    ///System.out.println(response.toString());

                    spinner.setVisibility(View.GONE);
                } catch (Exception e1) {
                    Log.e("tikna", "er" +e1.toString());
                }

            }
        }.start();

    }
}
