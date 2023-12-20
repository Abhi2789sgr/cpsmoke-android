package com.example.cp100;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int DELAY_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Request for no title of the app on screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // For using full screen of the android phone...
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set content to show on screen
        setContentView(R.layout.activity_main);

        //hide Action bar of the screen
        getSupportActionBar().hide();


        //Start another activity after some time using Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,DevicesActivity.class);
                startActivity(intent);
                // Finish this activity
                finish();
            }
        },DELAY_TIME);
    }
}