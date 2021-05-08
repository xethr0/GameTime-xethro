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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private  TextView Exsisting;
    private EditText Fullname, Password, Email;
    private Button CreateProfile;

    private DatabaseReference UserRef;

    String currentuserid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            currentuserid = mAuth.getCurrentUser().getUid();
            UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuserid);

        }


        Fullname = (EditText) findViewById(R.id.FullName);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
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

    // checks the users inputs and meats parameteres.
    private void CreateNewAccount()
    {
        String name = Fullname.getText().toString();
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
        else
        {
            if(mAuth.getCurrentUser() != null)
            {


                SendUsertoSportSlection();

            }

        }

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



    //sends user to the sports tab
    private void SendUsertoSportSlection()
    {
        Intent sportselection = new Intent(Register.this, SetupActivity.class);
        startActivity(sportselection);
        sportselection.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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