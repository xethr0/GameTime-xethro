package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class createEvent extends AppCompatActivity {

    EditText title;
    EditText date;
    EditText time;
    EditText location;
    EditText sport;
    TextView textView2;
    Button addEvent;

    private String current_user_id, eventTitle, eventDate, eventTime, eventAddress, eventSport;
    private FirebaseDatabase rootNode;
    private DatabaseReference userRef, eventRef;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        eventRef = FirebaseDatabase.getInstance().getReference().child("events");

        title = findViewById(R.id.etTitle);
        date  = findViewById(R.id.etDate);
        time = findViewById(R.id.etTime);
        location = findViewById(R.id.etLocation);
        sport = findViewById(R.id.etSport);
        addEvent = findViewById(R.id.btnSaveEvent);
        eventDate = getIntent().getStringExtra("eventDate");
        date.setText(getIntent().getStringExtra("eventDate"));

        addEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (date.getText().toString().isEmpty() || date.getText().toString().length() < 10 || date.getText().toString().length() > 10)
                {
                    Toast.makeText(createEvent.this, "Please enter a valid date (i.e. 03/25/2021)", Toast.LENGTH_SHORT).show();
                }
                else if (time.getText().toString().isEmpty() || time.getText().toString().length() < 5 || time.getText().toString().length() > 5)
                {
                    Toast.makeText(createEvent.this, "Please enter a valid time (i.e. 14:30)", Toast.LENGTH_SHORT).show();
                }
                else if (!title.getText().toString().isEmpty() && !date.getText().toString().isEmpty() && !time.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !sport.getText().toString().isEmpty())
                {
                    //push the data into the database for the user
                    saveEventInformationToDatabase();
                }
                else
                {
                    Toast.makeText(createEvent.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void saveEventInformationToDatabase()
    {
        /*rootNode = FirebaseDatabase.getInstance();
        eventRef =rootNode.getReference("events");

        //Get all values from the user-inputs
        eventTitle = title.getText().toString();
        eventDate = date.getText().toString();
        eventTime = time.getText().toString();
        eventAddress = location.getText().toString();
        eventSport = sport.getText().toString();

        Events eventClass = new Events(eventTitle, eventDate, eventTime, eventAddress, eventSport);

        eventRef.setValue(eventClass);

        Toast.makeText(createEvent.this, "New event has been posted.", Toast.LENGTH_SHORT).show();
        finish();
        */
        userRef.child(current_user_id).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String userFullName = dataSnapshot.child("username").getValue().toString();
                    //String userProfileImage = dataSnapshot.child("profileimage").getValue().toString();
                    eventTitle = title.getText().toString();
                    eventDate = date.getText().toString();
                    eventTime = time.getText().toString();
                    eventAddress = location.getText().toString();
                    eventSport = sport.getText().toString();

                    HashMap eventMap = new HashMap();
                    eventMap.put("uid", current_user_id);
                    eventMap.put("eventTitle", eventTitle);
                    eventMap.put("date", eventDate);
                    eventMap.put("time", eventTime);
                    eventMap.put("address", eventAddress);
                    eventMap.put("sport", eventSport);
                    eventMap.put("username", userFullName);
                    eventRef.child(current_user_id + eventTitle).updateChildren(eventMap)
                            .addOnCompleteListener(new OnCompleteListener()
                            {
                                @Override
                                public void onComplete(@NonNull Task task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(createEvent.this, "New event has been posted.", Toast.LENGTH_SHORT).show();
                                        sendUserToTabActivity();
                                    }
                                    else
                                    {
                                        Toast.makeText(createEvent.this, "Error occurred while updating event information.", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });
                }
            }

            public void onCancelled(DatabaseError databaseError)
            {

            }

        });
    }

    private void sendUserToTabActivity()
    {
        Intent tabIntent = new Intent(createEvent.this, TabActivityMenu.class);
        startActivity(tabIntent);
    }
}