package com.adarsh.listviewpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class RecyclerViewEx extends AppCompatActivity {

    private RecyclerView rv;
    ArrayList<Course> list = Course.randomList(50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rv =  (RecyclerView)findViewById(R.id.rev);

        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)); //second
        // parameter for orientation,
        // either Horizontal or vertical.

        rv.setAdapter(new AdapterCourse(this, list));



    }

}
