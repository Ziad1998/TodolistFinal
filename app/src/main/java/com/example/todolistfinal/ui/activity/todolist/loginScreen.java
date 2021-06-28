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

public class loginScreen extends AppCompatActivity {
    EditText emailId, password;
    Button btn;
    TextView tvSignin;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.Username);
        progressBar=findViewById(R.id.prog);
        linearLayout=findViewById(R.id.parent_layout2);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.button_SignIn);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#89CFF0"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        tvSignin=findViewById(R.id.dont);
        tvSignin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intToRegister = new Intent(loginScreen.this, register.class);
        startActivity(intToRegister);
    }
});
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){

                    Toast.makeText(loginScreen.this,"Login Please ",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(loginScreen.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty() && pwd.isEmpty()){
                    emailId.requestFocus();
                    password.requestFocus();

                    Toast.makeText(loginScreen.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    emailId.setError("Please enter email");
                    emailId.requestFocus();
                }



                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }


                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    progressBar.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(loginScreen.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful()){
                                progressBar.setVisibility(View.INVISIBLE);
                                linearLayout.setVisibility(View.VISIBLE);
                                Toast.makeText(loginScreen.this,"Invalid email or password",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progressBar.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.VISIBLE);
                                Intent intToHome = new Intent(loginScreen.this, ChooserActivity.class);
                                startActivity(intToHome);
                                finish();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(loginScreen.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

                if(email.matches("")&& pwd.matches(""))
                {

                    Toast.makeText(loginScreen.this,"FIELDS ARE REQUIRED",Toast.LENGTH_SHORT).show();

                }



            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}