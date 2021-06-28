package com.example.todolistfinal.ui.activity.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistfinal.R;
import com.example.todolistfinal.data.todolist.model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class addTask extends AppCompatActivity implements View.OnClickListener{
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    String useriid;
    DatePicker picker;
    Button btnDatePicker;
    Button save,cancel;
    int mYear, mMonth, mDay;
    String myeear,mmonth,mmday;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        firebaseAuth=FirebaseAuth.getInstance();
save=findViewById(R.id.saveee);
cancel=findViewById(R.id.canceeel);
        useriid=firebaseAuth.getUid();
        EditText task=findViewById(R.id.task);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        btnDatePicker=(Button)findViewById(R.id.btn_date);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("tasks").child(useriid);
        String id =databaseReference.push().getKey();

        btnDatePicker.setOnClickListener((View.OnClickListener) this);
        Toast.makeText(addTask.this, "Please Choose Specefic date", Toast.LENGTH_LONG).show();

        EditText desc=findViewById(R.id.description);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mtask =task.getText().toString().trim();
                String descr =desc.getText().toString().trim();
                model contact = new model(mtask,descr,useriid,mmday,mmonth,myeear,id);
                if(TextUtils.isEmpty(mtask))
                {
                    task.setError("Task Required");
                    return;
                }
                if(TextUtils.isEmpty(descr))
                {
                    desc.setError("description Required");
                    return;
                }
                if(!mtask.isEmpty() && !descr.isEmpty() &&mmday!=null &&myeear!=null&&mmonth!=null) {
                    // Do what you have to do
                    Toast.makeText(addTask.this, "Adding Data", Toast.LENGTH_LONG).show();
                    databaseReference.child(id).setValue(contact);
                    Toast.makeText(addTask.this, "data added", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(addTask.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(addTask.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            mmday= String.valueOf(dayOfMonth);
                            mmonth= String.valueOf(monthOfYear);
                            myeear= String.valueOf(year);
                            Toast.makeText(addTask.this,mmonth,Toast.LENGTH_LONG).show();


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}