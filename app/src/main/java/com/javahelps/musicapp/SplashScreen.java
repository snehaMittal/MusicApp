package com.javahelps.musicapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

/**
 * Created by Sneha on 22-10-2017.
 */

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity2.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#000000"))
                .withLogo(R.drawable.logo)
                .withHeaderText("Welcomee!!")
                .withFooterText("MUSIC APP");

        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);

        View view = config.create();
        setContentView(view);
    }
}
