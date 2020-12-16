package com.example.logintask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboard extends AppCompatActivity {
    Button signOut;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    ProgressBar progressBar;
    TextView emailText;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        progressBar = (ProgressBar) findViewById(R.id.progresSign);
        signOut = (Button) findViewById(R.id.btnSignout);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        emailText = (TextView) findViewById(R.id.email_conf);
        username = (TextView) findViewById(R.id.username_conf);
        String name = user.getDisplayName();


        username.setText(name);
        emailText.setText(user.getEmail());
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(dashboard.this, login.class));
                    finish();
                }
            }
        };
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                auth.signOut();
                startActivity(new Intent(dashboard.this, login.class));
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }




}
