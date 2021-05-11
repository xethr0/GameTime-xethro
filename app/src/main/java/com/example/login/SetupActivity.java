package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.os.storage.StorageManager;
import android.content.Intent;
//import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


//import de.hdodenhof.circleimageview.CircleImageView;


public class SetupActivity extends AppCompatActivity
{

    private EditText UserName, City, Age;
    private Button SaveProfileButton, baseball, football, soccer, basketball;
    HashMap userMap = new HashMap();
   // private CircleImageView ProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
   // private StorageReference UserProfileImageRef;
    String currentUserID;
    final static int Gallery_Pick = 1;
    static int baseballstat = 0;
    static int basketballstat = 0;
    static int footballstat = 0;
    static int soccerstat = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        //FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);
       // UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");


        UserName = (EditText) findViewById(R.id.setup_username);
        City = (EditText) findViewById(R.id.setup_city);
        Age = (EditText) findViewById(R.id.setup_age);
        baseball = (Button) findViewById(R.id.basbeall);
        football = (Button) findViewById(R.id.football);
        soccer = (Button) findViewById(R.id.soccer);
        basketball = (Button) findViewById(R.id.basketball);
        SaveProfileButton = (Button) findViewById(R.id.setup_create_pofile_button);
        //ProfileImage = (CircleImageView) findViewById(R.id.setup_profile_image);



        soccer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userMap.put("Soccer", soccerstat);
                UsersRef.updateChildren(userMap);
            }
        });

        football.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userMap.put("Football", footballstat);
                UsersRef.updateChildren(userMap);
            }
        });

        basketball.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
            {
                userMap.put("Basketball", basketballstat);
                UsersRef.updateChildren(userMap);
            }
        });

        baseball.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userMap.put("Baseball", baseballstat);
                UsersRef.updateChildren(userMap);
            }
        });

        SaveProfileButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SaveAccountInformation();
            }
        });

    } // end of oncreate

    private void SaveAccountInformation()
    {
        String username = UserName.getText().toString();
        String city = City.getText().toString();
        String age = Age.getText().toString();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Please write your username...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(city))
        {
            Toast.makeText(this, "Please write your city...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(age))
        {
            Toast.makeText(this, "Please write your age...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            userMap.put("username", username);
            userMap.put("city", city);
            userMap.put("age", age);
            UsersRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener()
            {
                @Override
                public void onComplete(@NonNull Task task)
                {
                    if(task.isSuccessful())
                    {
                        SendUserToTabs();
                    }
                }
            });


        }

    }

    private void SendUserToTabs()
    {
        Intent tabintent = new Intent(SetupActivity.this, TabActivityMenu.class);
        startActivity(tabintent);
        //finish();

    }

}