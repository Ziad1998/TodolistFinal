

package com.example.todolistfinal.ui.activity.expenses;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistfinal.R;
import com.example.todolistfinal.data.expenses.Expense;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity
{
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Expenses");

    }

    public void addExpense(View view)
    {
        EditText editItem = findViewById(R.id.editTextItem);
        String item = editItem.getText().toString();
        EditText editCat = findViewById(R.id.editTextDate);
        String date = (editCat.getText().toString()).toUpperCase();


        EditText editprice = findViewById(R.id.editTextPrice);
        String price = editprice.getText().toString();
        EditText editNote = findViewById(R.id.editTextnote);
        String note = (editNote.getText().toString()).toUpperCase();



        if( item.length() > 0 )
        {
            String key = myRef.push().getKey(); // Generates unique random key
            Expense e = new Expense(item, date, key,price,note);
            myRef.child(key).setValue(e);
            Toast.makeText(this, e.getItem() + " successfully added.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomePage.class);
            startActivity( intent);
            finish();
        }

        editItem.setText("");
    }


    public void goHome( View view )
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity( intent);
        finish();
    }

}
