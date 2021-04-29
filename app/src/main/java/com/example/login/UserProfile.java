package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    private ArrayList<Object> userInfo = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userInfo.add("Redhwan Ahmed"); //Name
        userInfo.add("I like Computer Science and I am also super cool."); //bio
        userInfo.add(12); //Matches of BBall
        userInfo.add(144); //Matches of Soccer
        userInfo.add(50); //Badminton
        userInfo.add(43); //Volleyball

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        TextView Name = (TextView) findViewById(R.id.Name);
        TextView bio = (TextView) findViewById(R.id.UserBio);
        TextView Bball = (TextView) findViewById(R.id.BasketballGames);
        TextView Soccer = (TextView) findViewById(R.id.soccer);
        TextView Badminton = (TextView) findViewById(R.id.Badminton);
        TextView Vollyball = (TextView) findViewById(R.id.Vollyball);
        TextView TotalGames = (TextView) findViewById(R.id.totalMatches);

        Name.setText(""+userInfo.get(0));
        bio.setText(""+userInfo.get(1));
        Bball.setText("Basketball Games: "+userInfo.get(2));
        Soccer.setText("Soccer Games: "+userInfo.get(3));
        Badminton.setText("Badminton Games: "+userInfo.get(4));
        Vollyball.setText("Volly Ball Games: "+userInfo.get(5));
        int sumgames = 0;
        for(int i = 2; i < userInfo.size(); i++) {
            sumgames += (int)userInfo.get(i);
        }
        TotalGames.setText("Total Matches Played: "+sumgames);
    }
}