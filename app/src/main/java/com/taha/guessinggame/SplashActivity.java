package com.taha.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView splashAppNameTextView;
    ImageView splashAppImageView;

    Animation imageAnimation;
    Animation textAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashAppNameTextView = findViewById(R.id.splash_screen_app_name_txt);
        splashAppImageView = findViewById(R.id.splash_screen_imgview);

        imageAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.splash_screen_image_view_animation);

        textAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.splash_screen_text_view_animation);

        splashAppNameTextView.setAnimation(textAnimation);
        splashAppImageView.setAnimation(imageAnimation);

        CountDownTimer countDownTimer = new CountDownTimer(5000,5000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        }.start();
    }
}