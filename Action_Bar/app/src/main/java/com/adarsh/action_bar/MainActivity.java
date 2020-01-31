package com.adarsh.action_bar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.helpID:
                        //Do something for help button.
                        return true;
            case R.id.bookmarkID:
                        //Add this to bookmark
                //BookmarkID();
                return true;
            case R.id.refreshID:
                        //Reload this page
                       // RefreshID();
                        return true;
            case R.id.searchID:
                        //SearchID();
                        return true;
            case R.id.saveID:
                       //Save this item somewhere
                        SaveID();
                        return true;
            case R.id.locationID:
                       LocationID();
                       return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LocationID(){
        Intent it = new Intent(getApplicationContext(), LocationFound.class);
        startActivity(it);
    }

    private void SaveID(){
        Intent it = new Intent(getApplicationContext(), SaveID.class);
        startActivity(it);
    }
}
