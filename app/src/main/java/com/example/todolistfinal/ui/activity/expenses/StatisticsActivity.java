
package com.example.todolistfinal.ui.activity.expenses;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistfinal.data.expenses.Expense;
import com.example.todolistfinal.data.expenses.Calculate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import com.example.todolistfinal.R;

public class StatisticsActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<Expense> expenseList;
    private ArrayList<Expense> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
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

        childEventListener = new ChildEventListener() {
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
    }


    public void calculate(View view)
    {
        boolean found = false;
        EditText text = (EditText)findViewById(R.id.editTextDate);
        ArrayList<Expense> items = new ArrayList<>();
        Calculate c = new Calculate();

        for( Expense e: expenseList)
        {
            String search = text.getText().toString();
            if(e.getDate().equalsIgnoreCase(search))
            {
                items.add(e);
                found = true;
            }
            double[] answers = c.getStacks(items);
            TextView textTotal = findViewById( R.id.textViewTint );
            textTotal.setText( "$" + Double.toString(answers[0]));
            TextView textAvg = findViewById( R.id.textViewAvgInt );
            textAvg.setText( "$" + Double.toString(answers[1]));
            TextView textHigh = findViewById( R.id.textViewHInt );
            textHigh.setText( "$" + Double.toString(answers[2]));
            TextView textLow = findViewById( R.id.textViewLInt);
            textLow.setText( "$" + Double.toString(answers[3]));

        }

        if(!found) {
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }

    }



    public void goHome( View view )
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity( intent);
        finish();
    }



}

