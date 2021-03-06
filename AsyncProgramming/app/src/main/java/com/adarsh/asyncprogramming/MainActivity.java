package com.adarsh.asyncprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button button;
    ListView listView;
    ConstraintLayout layout;

    String[] items ={
      "Adarsh",
            "Singh",
            "The",
            "Great",
            "What",
            "Else",
            "Is",
            "Needed",
            "Huh!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    button = findViewById(R.id.button);
    listView = findViewById(R.id.listView);
    layout = findViewById(R.id.constraint);

        ArrayAdapter<String> itemAdapter= new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                android.R.id.text1, items
        );
    listView.setAdapter(itemAdapter);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //waitNsec(4);

            Handler handler = new Handler();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    layout.setBackgroundColor(Color.RED);
                    Log.d(String.valueOf(123), "We have waited 5 sec");
                }
            };
            handler.postDelayed(r,5000);
        }
    });

    }

    /* Used to demostrate the disfunctional ability of Main Thread. The other UI components stop
    in it's runtime.

    private void wait1sec(){
        long cur = System.currentTimeMillis();
        while(System.currentTimeMillis() < cur + 1000);
    }

    private void waitNsec(int sec){
        for(int i=0; i<sec; i++)
            wait1sec();
    }
    */

}
