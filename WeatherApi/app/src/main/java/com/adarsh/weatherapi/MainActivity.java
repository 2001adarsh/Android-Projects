package com.adarsh.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView mainTv, descTv, desc, tempTv, minTv, maxTv, pressureTv, feelsTv, humidityTv,
            visibilityTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.editText);
        Button search = findViewById(R.id.search);
        mainTv = findViewById(R.id.mainTv);
        descTv = findViewById(R.id.descTV);
        desc = findViewById(R.id.textView4);
        tempTv = findViewById(R.id.tempTv);
        minTv = findViewById(R.id.minTv);
        maxTv = findViewById(R.id.maxTv);
        pressureTv = findViewById(R.id.pressureTv);
        feelsTv = findViewById(R.id.feelsTv);
        humidityTv = findViewById(R.id.humidityTv);
        visibilityTv = findViewById(R.id.visibiltyTv);

        mainTv.setVisibility(View.INVISIBLE);
        desc.setVisibility(View.INVISIBLE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editText.getText().toString();

                String weatherApi = "https://openweathermap.org/data/2" +
                        ".5/weather?q="+ city + "&appid=439d4b804bc8187953eb36d2a8c26a02";

                Weather weather = new Weather();
                weather.execute(weatherApi);

                }
            });
        }

    class Weather extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... voids) {
            try {
                URL url = new URL(voids[0]);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = httpsURLConnection.getInputStream();

                Scanner sc = new Scanner(in);
                sc.useDelimiter("\\A");
                if(sc.hasNext()){
                    String s = sc.next();
                    return s;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "No city with this name found! Try another";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

           // Log.d(TAG, "onPostExecute: " + s);

            String main = null, description=null;
            //JSON PARSE
            try {
                JSONObject top = new JSONObject(s);


                //For weather Part

                String weather = top.getString("weather");
                JSONArray weatherArray = top.getJSONArray("weather");

                for(int i=0; i<weatherArray.length(); i++){
                    JSONObject object = weatherArray.getJSONObject(i);
                    main = object.getString("main");
                    description = object.getString("description");
                }

                mainTv.setText(main);
                descTv.setText(description);
                mainTv.setVisibility(View.VISIBLE);
                desc.setVisibility(View.VISIBLE);


                //Visibilty
                Double visibility = Double.parseDouble(top.getString("visibility"));
                visibilityTv.setText(visibility.toString());

                //For Main part
                String weather2 = top.getString("main");

                Log.d(TAG, "onPostExecute: "+ weather2);
                JSONObject formain = new JSONObject(weather2);

                tempTv.setText(String.valueOf(formain.getDouble("temp")));
                feelsTv.setText(String.valueOf(formain.getDouble("feels_like")));
                minTv.setText(String.valueOf(formain.getDouble("temp_min")));
                maxTv.setText(String.valueOf(formain.getDouble("temp_max")));
                pressureTv.setText(String.valueOf(formain.getDouble("pressure")));
                humidityTv.setText(String.valueOf(formain.getDouble("humidity")));

            } catch (JSONException e) {

                e.printStackTrace();
                descTv.setText(s);
                mainTv.setVisibility(View.INVISIBLE);
                desc.setVisibility(View.INVISIBLE);

            }
        }



    }
}
