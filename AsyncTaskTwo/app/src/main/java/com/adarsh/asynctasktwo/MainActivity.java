package com.adarsh.asynctasktwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);
        final Context context = this;

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button)
        {
            new ExampleTask(this).execute(10);
        }
    }

    private static class ExampleTask extends AsyncTask<Integer, Integer, String>{

        private WeakReference<MainActivity> mainActivityWeakReference;

        ExampleTask(MainActivity mainActivity) {
            mainActivityWeakReference = new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for(int i=0; i<integers[0]; ++i){
                onProgressUpdate((i*100)/integers[0]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Finished";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           MainActivity activity = mainActivityWeakReference.get();
           if(activity ==null || activity.isFinishing()){
                return;
           }
           else{
               activity.progressBar.setVisibility(View.VISIBLE);
           }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity activity = mainActivityWeakReference.get();
            if(activity== null || activity.isFinishing()){
                return;
            }else{
                activity.progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            MainActivity activity = mainActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {

            } else {
                activity.progressBar.setProgress(values[0]);
            }
        }
    }
}
