package com.example.todolistfinal.ui.activity.expenses;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.todolistfinal.R;
import com.example.todolistfinal.data.expenses.Calculate;
import com.example.todolistfinal.data.expenses.Expense;
import com.example.todolistfinal.ui.adapter.expenses.ExpenseAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    Button button;
    TextView textView;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;



    private ArrayList<Expense> expenseList;

     ExpenseAdapter arrayAdapter;

    // Menu //
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;

        switch (item.getItemId())
        {
            case R.id.search_item:
                intent = new Intent(this, SearchActivity.class );
                startActivity(intent);
                return true;
            case R.id.add_item:
                intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                return true;
            case R.id.stats_item:
                intent = new Intent(this, StatisticsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        button = findViewById(R.id.button2);
        textView = findViewById(R.id.totalImage);
        expenseList = new ArrayList<Expense>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, home.class);
                startActivity(intent);
            }
        });
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Expenses");

        expenseList = new ArrayList<Expense>();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                expenseList.add(dataSnapshot.getValue(Expense.class));


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        myRef.addChildEventListener(childEventListener);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        ArrayList<Expense> expenseList;
        expenseList = new ArrayList<Expense>();
        ArrayList<Expense> et;
        et = new ArrayList<Expense>();


    }

    public void calculate(View view) {

    }


    public void evaluate(View view) {

        ArrayList<Expense> items = new ArrayList<>();
        Calculate c = new Calculate();

        for (Expense e : expenseList) {

            items.add(e);

            double[] answers = c.getStacks(items);


            textView.setText("$" + Double.toString(answers[0]));

        }
    }
}