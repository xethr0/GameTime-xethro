package com.example.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EventDialog extends AppCompatDialogFragment {

    private TextView eventTitleText;
    private TextView eventTypeText;
    private TextView eventDateText;
    private TextView eventTimeText;
    private TextView eventLocationText;

    Event event ;

    EventDialog(Event event){
        this.event = event;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.event_dailog, null);

        builder.setView(view)
                .setTitle(this.event.getTitle())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

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
}
