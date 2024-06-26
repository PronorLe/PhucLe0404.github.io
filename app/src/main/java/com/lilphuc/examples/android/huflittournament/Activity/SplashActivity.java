package com.lilphuc.examples.android.huflittournament.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lilphuc.examples.android.huflittournament.R;

public class SplashActivity extends AppCompatActivity {


    private TextView name_text;
    private TextView tag_text;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        name_text = findViewById(R.id.app_name_text);
        tag_text = findViewById(R.id.app_name_tag);
        progressBar = findViewById(R.id.progressBar);


        name_text.setVisibility(View.INVISIBLE);
        tag_text.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);


        int START_TIME = 1500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
                name_text.startAnimation(fade);
                tag_text.startAnimation(fade);
                name_text.setVisibility(View.VISIBLE);
                tag_text.setVisibility(View.VISIBLE);

            }
        }, START_TIME);


        int LAUNCH_TIME = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                Intent intentMain = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intentMain);


                finish();

            }
        }, LAUNCH_TIME);
    }
}