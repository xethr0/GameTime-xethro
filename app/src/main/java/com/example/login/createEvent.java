package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class createEvent extends AppCompatActivity {

    EditText title;
    EditText date;
    EditText location;
    EditText sport;
    Button addEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        title = findViewById(R.id.etTitle);
        date  = findViewById(R.id.etDate);
        location = findViewById(R.id.etLocation);
        sport = findViewById(R.id.etSport);
        addEvent = findViewById(R.id.btnSaveEvent);

        addEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!title.getText().toString().isEmpty() && !date.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !sport.getText().toString().isEmpty())
                {
                    //push the data into the database for the user
                }
                else
                {
                    Toast.makeText(createEvent.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}