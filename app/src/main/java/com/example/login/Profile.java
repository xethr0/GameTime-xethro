package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    private ArrayList<Object> userInfo = new ArrayList<Object>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userInfo.add("Redhwan Ahmed"); //Name
        userInfo.add("I like Computer Science and I am also super cool."); //bio
        userInfo.add(12); //Matches of BBall
        userInfo.add(144); //Matches of Soccer
        userInfo.add(50); //Badminton
        userInfo.add(43); //Volleyball


        TextView Name = (TextView) view.findViewById(R.id.Name);
        TextView bio = (TextView) view.findViewById(R.id.UserBio);
        TextView Bball = (TextView) view.findViewById(R.id.BasketballGames);
        TextView Soccer = (TextView) view.findViewById(R.id.soccer);
        TextView Badminton = (TextView) view.findViewById(R.id.Badminton);
        TextView Vollyball = (TextView) view.findViewById(R.id.Vollyball);
        TextView TotalGames = (TextView) view.findViewById(R.id.totalMatches);

        Name.setText(""+userInfo.get(0));
        bio.setText(""+userInfo.get(1));
        Bball.setText("Basketball Games: "+userInfo.get(2));
        Soccer.setText("Soccer Games: "+userInfo.get(3));
        Badminton.setText("Badminton Games: "+userInfo.get(4));
        Vollyball.setText("Vollyball Games: "+userInfo.get(5));

        int gameSum = 0;
        TotalGames.setText("Total Matches Played: "+ gameSum);



        return view;
    }
}