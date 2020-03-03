package com.adarsh.bookgenre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<GenreBook> genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genre = new ArrayList<>();
        addingFiles();

        recyclerView = findViewById(R.id.rv);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLLM = layoutManager;
        recyclerView.setLayoutManager(rvLLM);

        GenreAdapter adapter = new GenreAdapter(this, genre);
        recyclerView.setAdapter(adapter);

    }

    private void addingFiles(){
        genre.add(new GenreBook(R.drawable.romance,"Romantic","amit dega") );
        genre.add(new GenreBook(R.drawable.horror,"Horror","amit dega") );
        genre.add(new GenreBook(R.drawable.crime,"Crime","amit dega") );
        genre.add(new GenreBook(R.drawable.scifi,"Science Fiction","amit dega") );
        genre.add(new GenreBook(R.drawable.act,"Thriller","amit dega") );
    }

}
