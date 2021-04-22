package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private  TextView Exsisting;
    private EditText Fullname, Password, Age, Email;
    private Button CreateProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Fullname = (EditText) findViewById(R.id.FullName);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        Age = (EditText) findViewById(R.id.Age);
        CreateProfile = (Button) findViewById(R.id.Createme);

        // This is the button that creates the new account
        CreateProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CreateNewAccount();
            }
        });



        // this is the text view on the bottom to send the user if they already have a login
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

    private void CreateNewAccount()
    {
        String name = Fullname.getText().toString();
        String age = Age.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        // checks parms if they are vaild entites
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email..", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please wrtie your name...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(age))
        {
            Toast.makeText(this, "Please write your age....", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                //create another method and call when the create button is complete and link it to the sport selection java
                                SendUsertoSportSlection();
                            }
                        }
                    });
        }


    }

    private void SendUsertoSportSlection()
    {
        Intent sportselection = new Intent(Register.this, SportSelection.class);
        startActivity(sportselection);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }


    private void SendUserToLogin()
    {
        Intent loginIntent = new Intent(Register.this, Login.class);
        startActivity(loginIntent);
        finish();
    }
}