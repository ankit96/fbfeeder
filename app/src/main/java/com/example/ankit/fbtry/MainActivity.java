package com.example.ankit.fbtry;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    String USER_AGENT = "Mozilla/5.0";
    String responsedata;
    Button nytimes,toi,facebook,cristiano,shakira,eminem,sachin,ghosal,bachchan;
    String list[]=new String[100];
    String content[]=new String[100];
    String Edata[]=new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
    * facebook
cristiano
shakira
vin diesel
coco-cola
fc barcelonna
eminem
real madrid
leo messi*/
    @Override
    protected void onStart() {
        super.onStart();
        nytimes = (Button) findViewById(R.id.fetchnytimes);
        nytimes.setOnClickListener(this); // calling onClick() method
        toi = (Button) findViewById(R.id.fetchtoi);
        toi.setOnClickListener(this);
        facebook = (Button) findViewById(R.id.fetchfacebook);
        facebook.setOnClickListener(this);
        cristiano = (Button) findViewById(R.id.fetchcristiano);
        cristiano.setOnClickListener(this);
        shakira = (Button) findViewById(R.id.fetchshakira);
        shakira.setOnClickListener(this);
        eminem = (Button) findViewById(R.id.fetcheminem);
        eminem.setOnClickListener(this);
        sachin = (Button) findViewById(R.id.fetchsachin);
        sachin.setOnClickListener(this);
        ghosal = (Button) findViewById(R.id.fetchghoshal);
        ghosal.setOnClickListener(this);
        bachchan = (Button) findViewById(R.id.fetchbachchan);
        bachchan.setOnClickListener(this);





        //datatext.setText(responsedata.toString());

    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.fetchnytimes:
                fetchdata("nytimes");
                break;

            case R.id.fetchtoi:
                fetchdata("TimesofIndia");
                break;

            case R.id.fetchfacebook:
                fetchdata("facebook");
                break;

            case R.id.fetchcristiano:
                fetchdata("cristiano");
                break;

            case R.id.fetchshakira:
                fetchdata("shakira");
                break;



            case R.id.fetcheminem:
                fetchdata("eminem");
                break;
            case R.id.fetchghoshal:
                fetchdata("shreyaghoshal");
                break;
            case R.id.fetchsachin:
                fetchdata("SachinTendulkar");
                break;
            case R.id.fetchbachchan:
                fetchdata("AmitabhBachchan");
                break;



            default:
                break;
        }
    }

        public void handleMessage(String msg) {
            //Log.d("tikna","1  "+msg);
            Intent intent = new Intent(MainActivity.this, page1.class);
            //intent.putExtra("data", msg);
            intent.putExtra("datalist",list);
            intent.putExtra("datacontent",content);
            startActivity(intent);

        }


    public void fetchdata(final String page)
    {


        final String url = "https://postscrapper.herokuapp.com/post";

        new Thread() {
            public void run() {


                try {

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


                } catch (Exception e1) {
                    Log.e("tikna", "er" +e1.toString());
                }

            }
        }.start();

    }
}
