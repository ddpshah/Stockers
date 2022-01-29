package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView logo, splashImg;
    TextView appName;
    LottieAnimationView lottieAnimationView;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.bg);
        lottieAnimationView = findViewById(R.id.lottie);

        splashImg.animate().translationY(-1800).setDuration(4000).setStartDelay(5000);
        logo.animate().translationY(2200).setDuration(4000).setStartDelay(5000);
        appName.animate().translationY(2200).setDuration(4000).setStartDelay(5000);
        lottieAnimationView.animate().translationX(1600).setDuration(4000).setStartDelay(6000);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), OnboardingActivity.class);
                startActivity(intent);
                finish();
            }
        }, 10000);

    }
}