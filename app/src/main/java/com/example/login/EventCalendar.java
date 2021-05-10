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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Queue;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventCalendar#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class EventCalendar extends Fragment implements AdapterView.OnItemClickListener {

    EditText _editText;
    TextView noItemText;
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    String eventDate;
    private DatabaseReference userRef, eventRef;
    private RecyclerView eventRv;
    private ArrayList<Event> eventArrayList = new ArrayList<>();
    EventAdapter eventAdapter = new EventAdapter(getActivity(), eventArrayList);
    private ProgressBar spinner;


    public EventCalendar()
    {
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }

    private void getEvents(String date)
    {
        eventArrayList.clear();
        eventAdapter.notifyDataSetChanged();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("events");
        spinner.setVisibility(View.VISIBLE);
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                eventArrayList.clear();
                eventAdapter.notifyDataSetChanged();
                for (DataSnapshot userSnapshot : snapshot.getChildren())
                {
                    HashMap<String, String> hash_map = (HashMap<String, String>) userSnapshot.getValue();
                    final String eventKey = userSnapshot.getRef().getKey();
                    System.out.println(hash_map.get("eventTitle"));
                    if(hash_map.get("date").equals(date))
                     eventArrayList.add(new Event(hash_map.get("date"), hash_map.get("address") , hash_map.get("sport"), hash_map.get("time"), hash_map.get("eventTitle"), hash_map.get("uid"), eventKey));

                }
                eventAdapter.notifyDataSetChanged();
                if(eventArrayList.isEmpty())
                {
                    noItemText.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
            }
        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        _editText = (EditText) view.findViewById(R.id.EventDate);
        FloatingActionButton fab = view.findViewById(R.id.addEventButton);
        eventRv = view.findViewById(R.id.EventListRecyclerView);
        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);
        noItemText = view.findViewById(R.id.noItemTextView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create New com.example.login.Event", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent createNewEvent = new Intent(getActivity(), createEvent.class);
                createNewEvent.putExtra("eventDate", eventDate);
                startActivity(createNewEvent);
            }
        });
        eventAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                System.out.println(position);
                openDialog(position);
//                Snackbar.make(view, position, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        eventRv.setLayoutManager(linearLayoutManager);
        eventRv.setAdapter(eventAdapter);
        this._editText.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {

                new DatePickerDialog(getActivity(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        this._editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                System.out.println(s);
                DateFormat dfParse = new SimpleDateFormat("EEE, d MMM yyyy");
                DateFormat dfFormat = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    String val = dfFormat.format(dfParse.parse(String.valueOf(s)));
                    noItemText.setVisibility(View.GONE);
                    getEvents(val);
                    spinner.setVisibility(View.GONE);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        _editText.setText(date);
        return view;
    }

    private void openDialog(int position)
    {
        EventDialog eventDialog =  new EventDialog(eventArrayList.get(position));
        eventDialog.show(getFragmentManager(),"Event");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String event  = parent.getItemAtPosition(position).toString();
        Snackbar.make(view, event, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}