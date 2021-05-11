package com.example.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class EventDialog extends AppCompatDialogFragment {

    private TextView eventTitleText;
    private TextView eventTypeText;
    private TextView eventDateText;
    private TextView eventTimeText;
    private TextView eventLocationText;
    private DatabaseReference ClickEventRef;
    private FirebaseAuth mAuth;

    private String currentUserID, databaseUserID;


    Event event ;

    EventDialog(Event event){
        this.event = event;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        databaseUserID = this.event.getUid();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.event_dailog, null);

        if (currentUserID.equals(databaseUserID))
        {
            builder.setView(view)
                    .setTitle(this.event.getTitle())
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Edit Event", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendToEdit();
                        }
                    });
        }

        else
        {
           builder.setView(view)
                    .setTitle(this.event.getTitle())
                    /*.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })*/
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
        /*builder.setView(view)
                .setTitle(this.event.getTitle())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Edit Event", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendToEdit();
                    }
                });
        */
        eventDateText = view.findViewById(R.id.idEventDateDialog);
        eventLocationText = view.findViewById(R.id.idEventLocationDialog);
        eventTimeText = view.findViewById(R.id.idEventTimeDialog);
        eventTypeText = view.findViewById(R.id.idEventTypeDialog);

        eventDateText.setText(this.event.getDate());
        eventTypeText.setText(this.event.getSportType());
        eventLocationText.setText(this.event.getLocation());
        eventTimeText.setText(this.event.getTime());

        return builder.create();
    }

    private void sendToEdit()
    {
        Intent editIntent = new Intent(getContext(), editEvent.class);
        editIntent.putExtra("eventKey", this.event.getEventKey());
        startActivity(editIntent);
    }
}
