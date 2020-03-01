package com.adarsh.googlenewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        data = new ArrayList<>();

        getDatafromGoogle();

    }

    public void getDatafromGoogle(){

        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                //Code that uses AsyncHttpClient in your case ConsultaCaract()
                /*
                Whenever creating an AsyncHttpClient always put it inside a handler
                handler will create a thread to run it. Android has main thread and it uses only
                for UI working and hence we can't use that.
                We need to create another thread.
                 */

                try {
                    String url = "http://newsapi.org/v2/everything?q=bitcoin&from=2020-01-28&sortBy=publishedAt&apiKey=d9e709ee37414aaab5709baa83038d4a";
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    client.get(url, params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);

                            try {
                                JSONArray articles = response.getJSONArray("articles");
                                Log.e("title", String.valueOf(articles.length()));
                                for(int i=0; i<articles.length(); i++)
                                {
                                    JSONObject article = (JSONObject) articles.get(i);

                                    Model articleModel = new Model(article.getString("title"),
                                            article.getString("urlToImage"), article.getString(
                                                    "description"), article.getString("author"),
                                            article.getString("content"));
                                    data.add(articleModel);

                                    Log.e("title", article.getString("title"));

                                }
                                RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this,
                                        data);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                recyclerView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("Exception", e.toString());
                            }

                            Log.e("jsonData", response.toString());

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);

                            Toast.makeText(MainActivity.this, "Failure: " + Integer.toString(statusCode),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("Exception", e.toString());
                }

            }
        };
        mainHandler.post(myRunnable);


    }
}
