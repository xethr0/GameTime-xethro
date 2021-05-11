package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editEvent extends AppCompatActivity {

    EditText title;
    EditText date;
    EditText time;
    EditText location;
    EditText sport;
    Button saveEvent;
    Button deleteEvent;

    private DatabaseReference eventRef, eventKeyRef;
    private FirebaseAuth mAuth;
    private String currentUserID, eventKey;
    private int deleteDec;

    //Global Variables to compare the user input to the database
    String _TITLE, _DATE, _TIME, _LOCATION, _SPORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        eventRef = FirebaseDatabase.getInstance().getReference().child("events");
        eventKey = getIntent().getExtras().get("eventKey").toString();
        eventKeyRef = FirebaseDatabase.getInstance().getReference().child("events").child(eventKey);

        title = findViewById(R.id.etTitle);
        date  = findViewById(R.id.etDate);
        time = findViewById(R.id.etTime);
        location = findViewById(R.id.etLocation);
        sport = findViewById(R.id.etSport);
        saveEvent = findViewById(R.id.btnSaveEvent);
        deleteEvent = findViewById(R.id.btnDeleteEvent);

        //fill in the editText fields with the currently selected event's information

        eventKeyRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    title.setText(snapshot.child("eventTitle").getValue().toString());
                    date.setText(snapshot.child("date").getValue().toString());
                    time.setText(snapshot.child("time").getValue().toString());
                    location.setText(snapshot.child("address").getValue().toString());
                    sport.setText(snapshot.child("sport").getValue().toString());

                    _TITLE = (snapshot.child("eventTitle").getValue().toString());
                    _DATE = (snapshot.child("date").getValue().toString());
                    _TIME = (snapshot.child("time").getValue().toString());
                    _LOCATION = (snapshot.child("address").getValue().toString());
                    _SPORT = (snapshot.child("sport").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

        deleteEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //show are you sure option, along with the buttons yes and no
                //if yes, delete event
                //if no, return to beginning of editEventPage
                /*openDialog();
                if (deleteDec == 1)
                {
                    eventKeyRef.removeValue();
                    sendUserToTabActivity();
                }*/
                DeleteCurrentEvent();
            }
        });


        saveEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!title.getText().toString().isEmpty() && !date.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !sport.getText().toString().isEmpty())
                {
                    //save event to the database
                    update(v);
                }
                else
                {
                    Toast.makeText(editEvent.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /*public void openDialog()
    {
        editEventDialog editEventDialog = new editEventDialog();
        editEventDialog.show(getSupportFragmentManager(), "DeleteEvent Dialog");
    }

    @Override
    public void applyDeleteNum(int deleteThing)
    {
        deleteDec = deleteThing;
    }
    */

    public void update(View view)
    {
        if (isDateChanged())
        {
        }
        if (isTimeChanged())
        {
        }
        if (isLocationChanged())
        {
        }
        if (isSportChanged())
        {
        }
        if (isTitleChanged() || isDateChanged() || isTimeChanged() || isLocationChanged() || isSportChanged())
        {
            Toast.makeText(this, "Event has been updated", Toast.LENGTH_LONG).show();
        }
        sendUserToTabActivity();
    }

    private boolean isTitleChanged()
    {
        if (!_TITLE.equals(title.getText().toString()))
        {
            eventKeyRef.child("eventTitle").setValue(title.getText().toString());
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isDateChanged()
    {
        if (!_DATE.equals(date.getText().toString()))
        {
            eventKeyRef.child("date").setValue(date.getText().toString());
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isTimeChanged()
    {
        if (!_TIME.equals(time.getText().toString()))
        {
            eventKeyRef.child("time").setValue(time.getText().toString());
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isLocationChanged()
    {
        if (!_LOCATION.equals(location.getText().toString()))
        {
            eventKeyRef.child("address").setValue(location.getText().toString());
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isSportChanged()
    {
        if (!_SPORT.equals(sport.getText().toString()))
        {
            eventKeyRef.child("sport").setValue(sport.getText().toString());
            return true;
        }
        else
        {
            return false;
        }
    }


    private void DeleteCurrentEvent()
    {
        eventKeyRef.removeValue();
        sendUserToTabActivity();

    }

    private void sendUserToTabActivity()
    {
        Intent tabIntent = new Intent(editEvent.this, TabActivityMenu.class);
        startActivity(tabIntent);
    }

}