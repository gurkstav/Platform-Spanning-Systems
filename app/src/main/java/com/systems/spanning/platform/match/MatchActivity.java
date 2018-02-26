package com.systems.spanning.platform.match;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MatchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private searchMatchAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        setRecyclerView();

    }

    public void newSearchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void setRecyclerView(){
        ArrayList<Match> matchList = getIntent().getParcelableArrayListExtra("matchList");

        if(matchList == null){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        else if(matchList.isEmpty()){
            Match a = new Match(null, null, null, null, null, null, R.drawable.nomatch);
            matchList.add(a);
            recyclerView = findViewById(R.id.searchResultsView);
            adapter = new searchMatchAdapter(this, matchList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new ItemDecorator(2, 30, true));
            recyclerView.setAdapter(adapter);


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
}