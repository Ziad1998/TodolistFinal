package com.example.todolistfinal.ui.activity.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistfinal.R;


public class splash_screen extends AppCompatActivity {
    Animation anim;


    ImageView imageView,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        i2=findViewById(R.id.imageSignIn);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade); // Create the animation.
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        i2.setAnimation(anim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, loginScreen.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
        i2.startAnimation(anim);

    }

}