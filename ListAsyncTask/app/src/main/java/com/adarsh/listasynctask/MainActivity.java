package com.adarsh.listasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String[] items = {
            "Adarsh",
            "Sinngh",
            "Last",
            "Day",
            "What",
            "is",
            "yourr",
            "Name",
            "huh?"
    };

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new ArrayList<String>()));
        new ExampleTask().execute();

    }

    class ExampleTask extends AsyncTask<Void, String, String> {

        ArrayAdapter<String> arrayAdapter;
        ProgressBar progressBar;
        int count =0;
        @Override
        protected void onPreExecute() {
             progressBar= findViewById(R.id.progressBar);
            arrayAdapter = (ArrayAdapter<String>) listView.getAdapter();

            progressBar.setMax(9);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            for(String i: items){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "All work done.";
        }

        @Override
        protected void onProgressUpdate(String... values) {
             arrayAdapter.add(values[0]);
             count++;
             progressBar.setVisibility(View.VISIBLE);
             progressBar.setProgress(count);
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

}
