package com.adarsh.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.aud);

    }

    public void playAudio(View view){

        if(!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    public void pauseAudio(View view){
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

}
