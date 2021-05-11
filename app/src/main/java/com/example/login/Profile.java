package com.example.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reff;
    private String userID;

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

        user = FirebaseAuth.getInstance().getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        TextView Name = (TextView) view.findViewById(R.id.Name);
        TextView bio = (TextView) view.findViewById(R.id.UserAge);
        TextView Bball = (TextView) view.findViewById(R.id.BasketballGames);
        TextView Soccer = (TextView) view.findViewById(R.id.soccer);
        TextView Football = (TextView) view.findViewById(R.id.Football);
        TextView Baseball = (TextView) view.findViewById(R.id.Baseball);
        TextView TotalGames = (TextView) view.findViewById(R.id.totalMatches);

        reff.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Name.setText(UserProfile.UserName);
                bio.setText("Lives in "+UserProfile.city+" and is "+UserProfile.age+" years old.");
                Bball.setText("Basketball: "+UserProfile.bBall);
                Soccer.setText("Soccer: "+UserProfile.socc);
                Football.setText("Football: "+UserProfile.fBall);
                Baseball.setText("Baseball: "+UserProfile.baseBall);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                bio.setText("Error");
            }
        });

        int gameSum = 0;
        TotalGames.setText("Total Matches Played: "+ gameSum);



        return view;
    }
}