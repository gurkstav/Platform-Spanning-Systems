package com.systems.spanning.platform.match;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OwnActivities extends AppCompatActivity{

    private RecyclerView recyclerView;
    private searchMatchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_own_activities);

        setRecyclerView();
    }


    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void setRecyclerView(){
        ArrayList<Match> matchList = getIntent().getParcelableArrayListExtra("matchListOwn");
        if(matchList == null){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            recyclerView = findViewById(R.id.OwnActivitiesView);
            adapter = new searchMatchAdapter(this, matchList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            // recyclerView.addItemDecoration(new ItemDecorator(2, 30, true));
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Match match = adapter.getItem(position);
                    if (match.getThumbnail() != 0) {
                        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        android.support.v4.app.Fragment prev = getSupportFragmentManager().findFragmentByTag("cardDialog");
                        if (prev != null) {
                            ft.remove(prev);
                        }
                        ft.addToBackStack(null);

                        CardFragment cardFragment = CardFragment.newInstance(match);
                        cardFragment.show(ft, "cardDialog");

                    }
                }

                @Override
                public void onItemLongClick(View view, int position) {
                    // ...
                }
            }));
        }
    }
}
