package com.example.todolistfinal.ui.activity.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistfinal.R;
import com.example.todolistfinal.todolist.adapter;
import com.example.todolistfinal.todolist.deleteNode;
import com.example.todolistfinal.data.todolist.model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements deleteNode {
RecyclerView recyclerView;
FloatingActionButton floatingActionButton;
    FirebaseDatabase database,database2;
    DatabaseReference databaseReference,databaseReference2;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
String useriid;
Button taskAdd;
String searchTitle;
EditText searchbar;
ProgressBar progressBar;
    com.example.todolistfinal.todolist.adapter adapter;
    LinearLayout layout;
    RecyclerView.LayoutManager layoutManager;
    List<model> modelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.prog);
        firebaseAuth=FirebaseAuth.getInstance();
        layout=findViewById(R.id.parent_layout);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        taskAdd=findViewById(R.id.taskadd);
useriid=firebaseAuth.getUid();
        intiFirebaseTools();


        intiRecycler();

        getAllContactsWithStream();
       taskAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intenttoAddtask = new Intent(MainActivity.this, addTask.class);
               startActivity(intenttoAddtask);
           }
       });
    }

    public void intiFirebaseTools() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("tasks").child(useriid);
    }
    private void intiRecycler() {
        recyclerView = findViewById(R.id.recyclerViewLists);
        adapter = new adapter(getApplicationContext(),this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void getAllContactsWithStream() {
        databaseReference.
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        modelList.clear();
                        if (dataSnapshot.exists()) {
                            progressBar.setVisibility(View.GONE);
                            layout.setVisibility(View.VISIBLE);
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                modelList.add(snapshot.getValue(model.class));
                            }
                            adapter.setResult(modelList);
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            layout.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        Log.d("dddddddddd", "onCancelled: " + error.getMessage());
                    }
                });


    }


    @Override
    public void delete(String taskid) {
Toast.makeText(MainActivity.this,"deleted",Toast.LENGTH_LONG).show();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("tasks").child(useriid).child(taskid);
     rootRef.removeValue();

        Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
    startActivity(myIntent);
    finish();


    }
}