
package com.example.todolistfinal.ui.activity.expenses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class home extends AppCompatActivity
{
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    private ChildEventListener childEventListener;

    private ArrayList<Expense> expenseList;

    private ExpenseAdapter arrayAdapter;

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
        setContentView(R.layout.home);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Expenses");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        expenseList = new ArrayList<Expense>();


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NotNull DataSnapshot dataSnapshot, String s) {
                arrayAdapter.add(dataSnapshot.getValue(Expense.class));
            }

            @Override
            public void onChildChanged(@NotNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(@NotNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NotNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
                Toast.makeText(home.this,databaseError.toString(),Toast.LENGTH_SHORT).show();

            }
        };

        myRef.addChildEventListener(childEventListener);

        arrayAdapter = new ExpenseAdapter(this, expenseList);
        ListView results = (ListView) findViewById(R.id.listViewResults);
        results.setAdapter(arrayAdapter);




        results.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                final int position = i;
                final AdapterView<?> p = parent;
                new AlertDialog.Builder(home.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int j) {
                                        Expense selectedItem = (Expense) p.getItemAtPosition(position);

                                        // Removes that Expense from firebase
                                        myRef.child(selectedItem.getUid()).removeValue();

                                        // Removes the Expense from the local data structure
                                        expenseList.remove(selectedItem);

                                        // Refreshes the results on the ListView to reflect removal of selected Expense
                                        Intent intent = new Intent(getApplicationContext(), home.class);
                                        startActivity(intent);

                                    }
                                }
                        )
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });


    }

}
