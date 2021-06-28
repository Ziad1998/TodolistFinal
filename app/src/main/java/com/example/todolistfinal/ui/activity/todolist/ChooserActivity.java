package com.example.todolistfinal.ui.activity.todolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.todolistfinal.R;
import com.example.todolistfinal.ui.activity.expenses.HomePage;
import com.example.todolistfinal.ui.activity.stopwatch.StopWatch;

public class ChooserActivity extends AppCompatActivity {
    ImageButton imgb1,imgb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        
            setContentView(R.layout.activity_chooser);


        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

    }
    public void todolist(View view) { startActivity(new Intent(ChooserActivity.this, MainActivity.class));}
    public void expenses(View view) { startActivity(new Intent(ChooserActivity.this, HomePage.class));}
    public void stopWatch(View view) { startActivity(new Intent(ChooserActivity.this, StopWatch.class));}

}
