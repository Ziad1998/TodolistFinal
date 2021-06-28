package com.example.todolistfinal.ui.activity.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.todolistfinal.R;

public class StopWatchEnd extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseofset;
    private boolean running;

    Button btnstart, btnstop;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timeHere;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatchend);

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        icanchor = findViewById(R.id.icanchor);
        chronometer = findViewById(R.id.chronometer);

        // Create optional Animation


        // Load Animation
        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        // Ipomrting Fonts
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "font/MMedium.ttf");

        // Customize Fonts
        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passing Animation

                    icanchor.startAnimation(roundingalone);



                                // Start Time
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();


            }

        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passing Animation
                icanchor.setAnimation(null);
                chronometer.stop();
                pauseofset = SystemClock.elapsedRealtime() - chronometer.getBase();

            }

        });

    }


}
