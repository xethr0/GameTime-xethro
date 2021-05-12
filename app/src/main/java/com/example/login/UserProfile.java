package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class UserProfile {
    public int Baseball, Basketball, Football, Soccer;
    public String age, city, username;

    public UserProfile () {}

    public UserProfile (int bsb, int bb, int fb, int sc, String ag, String ct, String un) {
        this.Baseball = bsb;
        this.Basketball = bb;
        this.Football = fb;
        this.Soccer = sc;
        this.age = ag;
        this.city = ct;
        this.username = un;
    }


}