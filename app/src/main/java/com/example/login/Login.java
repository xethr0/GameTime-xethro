package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity
{
    private Button Loginbutton;
    private EditText userEmail, userPassword;
    private TextView NeednewAccountLink;
    private FirebaseAuth mAuth;
    private ImageView google;

    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "Login";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        NeednewAccountLink = (TextView) findViewById(R.id.Loginlinks);
        userEmail = (EditText) findViewById(R.id.Email);
        userPassword = (EditText) findViewById(R.id.Password);
        Loginbutton = (Button) findViewById(R.id.Login_button);
        google = (ImageView) findViewById(R.id.GoogleLog);

        NeednewAccountLink.setOnClickListener(new  View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SendUserToRegister(); // uncomment when you fix the rest of your section
            }
        });

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToTab();
            }
        });


    }



    private void SendUserToTab()
    {
      String email = userEmail.getText().toString();
      String password = userPassword.getText().toString();

      if(TextUtils.isEmpty(email))
      {
          Toast.makeText(this, "Please wrtie email", Toast.LENGTH_SHORT).show();
      }
      else if(TextUtils.isEmpty(password))
      {
          Toast.makeText(this, "Please write password" , Toast.LENGTH_SHORT).show();
      }
      else
      {
          mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful())
                  {
                      Intent loginIntent = new Intent(Login.this, TabActivityMenu.class);
                      startActivity(loginIntent);
                      Toast.makeText(Login.this, "you are logged in", Toast.LENGTH_SHORT).show();

                  }
                  else
                  {
                      String message = task.getException().getMessage();
                      Toast.makeText(Login.this, "Error" + message, Toast.LENGTH_SHORT).show();
                  }

              }
          });
      }
    }

    private void OpenProfileActivity (){
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }

    private void SendUserToRegister()
    {
        Intent registerIntent = new Intent(Login.this, Register.class);
        startActivity(registerIntent);

    }
}