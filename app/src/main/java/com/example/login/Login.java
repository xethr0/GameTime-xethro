package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity
{
    private Button Loginbutton;
    private EditText userEmail, userPassword;
    private TextView NeednewAccountLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        NeednewAccountLink = (TextView) findViewById(R.id.Loginlinks);
        userEmail = (EditText) findViewById(R.id.Email);
        userPassword = (EditText) findViewById(R.id.Password);
        Loginbutton = (Button) findViewById(R.id.Login_button);

        NeednewAccountLink.setOnClickListener(new  View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SendUserToRegister();
            }
        });


    }

    private void SendUserToRegister()
    {
        Intent registerIntent = new Intent(Login.this, Register.class);
        startActivity(registerIntent);

    }
}