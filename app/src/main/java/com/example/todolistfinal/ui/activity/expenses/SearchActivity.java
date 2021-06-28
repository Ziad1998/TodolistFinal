

package com.example.todolistfinal.ui.activity.expenses;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistfinal.R;
import com.example.todolistfinal.data.expenses.Expense;
import com.example.todolistfinal.ui.adapter.expenses.ExpenseAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<Expense> expenseList;
    private ArrayList<Expense> searchResults;


    private ExpenseAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Expenses");
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        expenseList = new ArrayList<Expense>();
        searchResults = new ArrayList<Expense>();

        childEventListener = new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                expenseList.add(dataSnapshot.getValue(Expense.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        myRef.addChildEventListener(childEventListener);

        arrayAdapter = new ExpenseAdapter( this, searchResults);
        ListView results = findViewById(R.id.listvue);
        results.setAdapter(arrayAdapter);


    }

    public void search(View view)
    {
        arrayAdapter.clear();
        boolean found = false;
        EditText text = (EditText)findViewById(R.id.editTextDate);

        for(Expense c: expenseList)
        {
            String search = text.getText().toString();
            if(c.getDate().equalsIgnoreCase(search))
            {
                arrayAdapter.add(c);
                found = true;
            }
        }

        if(!found) {
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
    }



    public void goHome( View view )
    {
        Intent intent = new Intent(this, home.class);
        startActivity( intent);
    }

}
