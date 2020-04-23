package com.adarsh.networking;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

    }

    private void updateData(){
        //Make the network call here
        //NetworkTask networkTask = new NetworkTask(); -used for asyncTask
        String source ="https://api.github.com/search/users?q=";

        if(editText.getText().toString()!= null)
        {
            String pass = source + editText.getText().toString();
            // networkTask.execute(pass);   --- used for AsyncTask
            UsingOkHttp(pass);
        }
    }


    // 2 method -> using OkHttp
    private void UsingOkHttp(String url){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        /* execute here is done for Synchronous task. THat is, It will run in main Thread.
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        */
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            //Similar to doInBackground method. Doesn't run on UI method
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final ArrayList<Details> users = parseJson(response.body().string());

                //To make changes now in Ui thread, like progressUpdate in async
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        RecyclerView rv = findViewById(R.id.rv);
                        rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        rv.setAdapter(new DetailsAdapter(users));
                    }
                });
            }

        });

    }


    // 1 method -> using AsyncTask
     class NetworkTask extends AsyncTask<String, Void, String>{

        RecyclerView rv = findViewById(R.id.rv);
        @Override
        protected String doInBackground(String... strings) {
            String stringurl = strings[0];
            try {
                URL url = new URL(stringurl);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

                InputStream inputStream = httpsURLConnection.getInputStream();
                Scanner sc = new Scanner(inputStream);

                sc.useDelimiter("\\A");
                if(sc.hasNext()){
                    String got = sc.next();
                    return got;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Failed Connection";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Details> users = parseJson(s);

            Log.d(TAG, "onPostExecute: "+ users.size());
            Log.e(TAG, "onPostExecute: "+ users.get(2).getHtml_url());


            rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            rv.setAdapter(new DetailsAdapter(users));

//            TextView textView = findViewById(R.id.textview);
//            textView.setText(s);
              }
    }

    ArrayList<Details> parseJson(String s){
        ArrayList<Details> users = new ArrayList<>();

        // parse the JSON
        try {
            JSONObject root = new JSONObject(s);
            JSONArray items = root.getJSONArray("items");

            for(int i=0; i<items.length(); i++)
            {
                JSONObject values = items.getJSONObject(i);
                String login = values.getString("login");
                int id = values.getInt("id");
                String avatar_url = values.getString("avatar_url");
                String html_url = values.getString("html_url");
                Double score = values.getDouble("score");

                Details ofUser = new Details(login,id,html_url,score,avatar_url);
                users.add(ofUser);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
