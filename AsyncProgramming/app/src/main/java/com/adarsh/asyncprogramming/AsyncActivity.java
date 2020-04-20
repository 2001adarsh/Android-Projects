package com.adarsh.asyncprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class AsyncActivity extends AppCompatActivity {

    TextView textView;
    Button start , random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        textView = findViewById(R.id.textView);
        start = findViewById(R.id.button2);
        random = findViewById(R.id.button3);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountTask countTask =  new CountTask();
                countTask.execute(5);
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                textView.setText(String.valueOf(r.nextInt(100)));
            }
        });

    }

    class CountTask extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d("At", "Background Work started");
            int n = integers[0];
            //waitNsec(n);
            for(int i=0; i<n; i++){
                wait1sec();
                publishProgress(i);
            }
            Log.d("At", "Background Work ended");
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(String.valueOf(values[0]));
        }
    }

    private void wait1sec(){
        long cur = System.currentTimeMillis();
        while(System.currentTimeMillis() < cur + 1000);
    }

    private void waitNsec(int sec){
        for(int i=0; i<sec; i++)
            wait1sec();
    }
}
