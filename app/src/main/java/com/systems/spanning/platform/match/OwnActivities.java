package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Gurkstav on 2018-02-12.
 */

public class OwnActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_own_activities);


    }

    /**
     if(matchList.isEmpty()){
        Match a = new Match("a", R.drawable.nomatch);
        matchList.add(a);


        recyclerView = findViewById(R.id.searchResultsView);
        adapter = new searchMatchAdapter(this, matchList);
        RecyclerView.LayoutManager mLayoutManager = new LinerLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new ItemDecorator(2, 30, true));
        recyclerView.setAdapter(adapter);

     */

    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
