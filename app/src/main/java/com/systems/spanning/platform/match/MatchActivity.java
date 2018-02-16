package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus on 2018-02-08.
 */

public class MatchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private searchMatchAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ArrayList<Match> matchList = getIntent().getParcelableArrayListExtra("matchList");

        if(matchList.isEmpty()){
            Match a = new Match("a", R.drawable.nomatch);
            matchList.add(a);
            recyclerView = findViewById(R.id.searchResultsView);
            adapter = new searchMatchAdapter(this, matchList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new ItemDecorator(2, 30, true));
            recyclerView.setAdapter(adapter);

          //  setContentView(R.layout.activity_no_match);

        }
        else {
            recyclerView = findViewById(R.id.searchResultsView);
            adapter = new searchMatchAdapter(this, matchList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new ItemDecorator(2, 30, true));
            recyclerView.setAdapter(adapter);
        }
    }

    public void newSearchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}