package com.example.login;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventCalendar#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class EventCalendar extends Fragment implements AdapterView.OnItemClickListener {

    EditText _editText;
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    ListView eventList;
    String[] events;
    String eventDate;
    public EventCalendar() {
        // Required empty public constructor
    }

    public static EventCalendar newInstance(String param1, String param2) {

        return new EventCalendar();
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    private void updateLabel() {
        String myFormat = "EEE, d MMM yyyy"; //In which you need put here
        String createEventDateFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        SimpleDateFormat mdy = new SimpleDateFormat(createEventDateFormat, Locale.US);

        _editText.setText(sdf.format(calendar.getTime()));
        eventDate = mdy.format(calendar.getTime());
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        eventList  = view.findViewById(R.id.EventList);
        _editText = (EditText) view.findViewById(R.id.EventDate);
        FloatingActionButton fab = view.findViewById(R.id.addEventButton);

        events = new DateFormatSymbols().getMonths();

        ArrayAdapter<String> eventAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,events);
        eventList.setAdapter(eventAdapter);

        eventList.setOnItemClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create New Event", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent createNewEvent = new Intent(getActivity(), createEvent.class);
                createNewEvent.putExtra("eventDate", eventDate);
                startActivity(createNewEvent);
            }
        });

        this._editText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String event  = parent.getItemAtPosition(position).toString();
        Snackbar.make(view, event, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}