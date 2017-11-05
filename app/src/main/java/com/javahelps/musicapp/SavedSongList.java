package com.javahelps.musicapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import static com.javahelps.musicapp.R.drawable.pause;

public class SavedSongList extends AppCompatActivity implements View.OnClickListener{

    ImageButton bt1 , bt2 ;
    MediaPlayer mySong1 , mySong2 ;
    boolean isPlaying1 = false , isPlaying2 = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_song_list);
        bt1 = (ImageButton) findViewById(R.id.imageButton);
        bt2 = (ImageButton) findViewById(R.id.imageButton2);
        bt2.setOnClickListener(this);
        bt1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v == bt1){
            if (isPlaying1 == false && isPlaying2 == false){
                bt1.setBackgroundResource(R.drawable.pause);
                isPlaying1 = true ;
                mySong1 = MediaPlayer.create(this , R.raw.permanent_stain);
                mySong1.start();
            }
            else if (isPlaying1 == false && isPlaying2 == true){
                mySong2.release();
                bt1.setBackgroundResource(R.drawable.pause);
                bt2.setBackgroundResource(R.drawable.play);
                isPlaying2 = false;
                isPlaying1 = true ;
                mySong1 = MediaPlayer.create(this , R.raw.permanent_stain);
                mySong1.start();
            }
            else if (isPlaying1 == true && isPlaying2 == false){
                isPlaying1 = false ;
                mySong1.release();
                bt1.setBackgroundResource(R.drawable.play);
            }
        }
        if (v == bt2){
            if (isPlaying1 == false && isPlaying2 == false){
                bt2.setBackgroundResource(R.drawable.pause);
                isPlaying2 = true ;
                mySong2 = MediaPlayer.create(this , R.raw.american_idiot);
                mySong2.start();
            }
            else if (isPlaying2 == false && isPlaying1 == true){
                mySong1.release();
                bt2.setBackgroundResource(R.drawable.pause);
                bt1.setBackgroundResource(R.drawable.play);
                isPlaying1 = false;
                isPlaying2 = true ;
                mySong2 = MediaPlayer.create(this , R.raw.american_idiot);
                mySong2.start();
            }
            else if (isPlaying2 == true && isPlaying1 == false){
                isPlaying2 = false ;
                mySong2.release();
                bt2.setBackgroundResource(R.drawable.play);
            }
        }

    }
}
