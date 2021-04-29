package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SportSelection extends AppCompatActivity
{
    private Button baseball ,basketball, football, soccer, finish;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_selection);
        baseball  = (Button) findViewById(R.id.baseballbut);
        basketball = (Button) findViewById(R.id.basketballbut);
        football = (Button) findViewById(R.id.footballbut);
        soccer = (Button) findViewById(R.id.soccerbut);
        finish = (Button) findViewById(R.id.Finishbut);

        baseball.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        basketball.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        football.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        soccer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        finish.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SendyoutoTab();
            }
        });


    }

    private void SendyoutoTab()
    {
        Intent tabintent = new Intent(SportSelection.this, TabActivityMenu.class);
        startActivity(tabintent);

    }
}