package com.example.login;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Context;
        import android.os.Bundle;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.*;

        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;

        import org.jetbrains.annotations.NotNull;

        import java.util.ArrayList;

public class activity_search extends AppCompatActivity {
    private EditText mySearch;
    private ImageButton myButton;

    private RecyclerView myResults;

    private DatabaseReference myEventDB;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        myEventDB = FirebaseDatabase.getInstance().getReference("events");

        mySearch = (EditText) findViewById(R.id.search_field);
        myButton = (ImageButton) findViewById(R.id.search_btn);

        myResults = (RecyclerView) findViewById(R.id.result_list);
        myResults.setHasFixedSize(true);
        myResults.setLayoutManager(new LinearLayoutManager(this));

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchText = mySearch.getText().toString();
                firebaseEventSearch(searchText);
            }
        });
    }

    private void firebaseEventSearch(String searchText) {

        Toast.makeText(activity_search.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = myEventDB.orderByChild("eventTitle").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Events> options = new FirebaseRecyclerOptions.Builder<Events>().setQuery(myEventDB, Events.class).build();
        FirebaseRecyclerAdapter<Events, EventsViewHolder> firerecycleadapter = new FirebaseRecyclerAdapter<Events, EventsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull EventsViewHolder holder, int position, @NonNull @NotNull Events model) {
                holder.setDetails(getApplicationContext(), model.getEventTitle(), model.getSport(), model.getAddress());
            }

            @NonNull
            @NotNull
            @Override
            public EventsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

                return new EventsViewHolder(view);
            }
        };
        firerecycleadapter.startListening();
        myResults.setAdapter(firerecycleadapter);
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder{

        View myView;
        public EventsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            myView = itemView;
        }

        public void setDetails(Context ctx, String eventName, String sportName, String addressName){
            TextView event_name = (TextView) myView.findViewById(R.id.eventTitle_text);
            TextView sport_name = (TextView) myView.findViewById(R.id.sport_text);
            TextView address_name = (TextView) myView.findViewById(R.id.address_text);

            event_name.setText(eventName);
            sport_name.setText(sportName);
            address_name.setText(addressName);

        }
    }

//    SearchView mySearchView;
//    ListView myList;
//
//    ArrayList<String> list;
//    ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mySearchView = (SearchView)findViewById(R.id.searchView);
//        myList = (ListView)findViewById(R.id.myList);
//
//        list = new ArrayList<String>();
//
//        list.add("3v3 BasketBall Game");
//        list.add("Soccer Tourney");
//        list.add("Football Game");
//        list.add("1v1 Tennis Match");
//        list.add("Doubles Table Tennis Match");
//        list.add("Basketball Pickup Game");
//        list.add("100m Race com.example.login.Event");
//
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        myList.setAdapter(adapter);
//
//        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//            @Override
//            public boolean onQueryTextSubmit(String s){
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s){
//                adapter.getFilter().filter(s);
//                myList.setVisibility(View.VISIBLE);
//
//                return false;
//            }
//        });
//    }
}