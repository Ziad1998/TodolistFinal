package com.example.todolistfinal.ui.activity.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todolistfinal.R;


public class StopWatch extends AppCompatActivity {
    TextView tvSplash, tvSubSplash;
    Button btnget;
    Animation atg, btgone, btgtwo;
    ImageView ivSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        tvSplash = findViewById(R.id.tvSplash);
        tvSubSplash = findViewById(R.id.tvSubSplash);
        btnget = findViewById(R.id.btnget);
        ivSplash = findViewById(R.id.ivSplash);

        atg = AnimationUtils.loadAnimation(this,R.anim.atg);
        btgone = AnimationUtils.loadAnimation(this,R.anim.btgone);
        btgtwo = AnimationUtils.loadAnimation(this,R.anim.btgtwo);


        // Passing Animation
        ivSplash.startAnimation(atg);
        tvSplash.startAnimation(btgone);
        tvSubSplash.startAnimation(btgone);
        btnget.startAnimation(btgtwo);

        // Passing Event
        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(StopWatch.this,StopWatchEnd.class);
                startActivity(a);
            }
        });

        // Ipomrting Fonts

        Typeface MLight = Typeface.createFromAsset(getAssets(), "font/MRegular.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "font/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "font/MRegular.ttf");

        // Customize Fonts
        tvSplash.setTypeface(MRegular);
        tvSubSplash.setTypeface(MLight);
        btnget.setTypeface(MMedium);




    }
}