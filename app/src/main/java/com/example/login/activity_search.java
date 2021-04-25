package com.example.login;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import android.view.View;
        import android.widget.*;

        import java.util.ArrayList;

public class activity_search extends AppCompatActivity {

    SearchView mySearchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySearchView = (SearchView)findViewById(R.id.searchView);
        myList = (ListView)findViewById(R.id.myList);

        list = new ArrayList<String>();

        list.add("3v3 BasketBall Game");
        list.add("Soccer Tourney");
        list.add("Football Game");
        list.add("1v1 Tennis Match");
        list.add("Doubles Table Tennis Match");
        list.add("Basketball Pickup Game");
        list.add("100m Race Event");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s){
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s){
                adapter.getFilter().filter(s);
                myList.setVisibility(View.VISIBLE);

                return false;
            }
        });
    }
}