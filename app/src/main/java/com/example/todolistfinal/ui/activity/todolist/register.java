package com.example.todolistfinal.ui.activity.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolistfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    EditText emailId, password,usernam,mobilee;
    EditText Editphone,Editcode;
    String codesent;
    Button btnSignUp,verify;
    TextView tvSignIn;
    private FirebaseAuth mFirebaseAuth;

    int flag2=0;
    private static int flag4;
    int flag1=0;   int flag3=0;
    DatabaseReference mDatabase;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirebaseAuth = FirebaseAuth.getInstance();
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        progressBar=findViewById(R.id.prog);
        linearLayout=findViewById(R.id.parent_layout);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        emailId = findViewById(R.id.email_signup);
        password = findViewById(R.id.password_signup);
        usernam=findViewById(R.id.Username_signup);



        btnSignUp = findViewById(R.id.button_SignUp);

tvSignIn=findViewById(R.id.have);
tvSignIn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(register.this, loginScreen.class);
        startActivity(i);
    }
});


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailId.getText().toString();
                final String pwd = password.getText().toString();
                final String name = usernam.getText().toString();

                if(name.isEmpty() &&pwd.isEmpty() &&email.isEmpty())
                {
                    Toast.makeText(register.this,"Fields are empty",Toast.LENGTH_SHORT).show();


                }

                if(!name.isEmpty() && !email.isEmpty() && !pwd.isEmpty()) {

                    progressBar.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(register.this, "please validate password or email " +task.getException(), Toast.LENGTH_SHORT).show();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    linearLayout.setVisibility(View.VISIBLE);
                                    String user_id = mFirebaseAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = mDatabase.child(user_id);
                                    current_user_db.child("email").setValue(email);
                                    current_user_db.child("password").setValue(pwd);

                                    current_user_db.child("username").setValue(name);


                                    FirebaseUser user = mFirebaseAuth.getCurrentUser();

                                    Intent i = new Intent(register.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }

                            }

                        });

                    }
                }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
    }

}
