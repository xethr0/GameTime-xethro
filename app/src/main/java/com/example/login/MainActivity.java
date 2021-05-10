package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private TextView Exsisting;
    String currentUserID;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        //currentUserID = mAuth.getCurrentUser().getUid();
        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
        if(mFirebaseUser != null)
        {
            currentUserID = mFirebaseUser.getUid(); //Do what you need to do with the id
        }

        Exsisting = (TextView) findViewById(R.id.Loginlinks);


        Exsisting.setOnClickListener(new  View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SendUserToLogin();
            }
        });

    }

    private void SendUserToLogin()
    {
        Intent loginIntent = new Intent(MainActivity.this, Login.class);
        startActivity(loginIntent);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
            SendUserToLogin();

    }
    }
}
