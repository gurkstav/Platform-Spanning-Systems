package com.systems.spanning.platform.match;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Marcus on 2018-02-07.
 */

public class SearchActivity extends AppCompatActivity implements GetDataInterface {
    Spinner spinner;
    private Button searchButton;
    String data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



    }
    public void SearchClick(View view){
        new GetData("http://192.168.1.2:1000/activities",this).execute();
    }

    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void fetchDataCallback(JSONArray result) {
        ArrayList<Match> matchList = new ArrayList<>();

        for (int i = 0; i < 1/*result.length()*/; i++) {
            try{
            JSONObject activity = result.getJSONObject(i);
            String type = activity.getString("type");
            String date = activity.getString("date");
            String location = activity.getString("location");
            int max_part = activity.getInt("max_participants");
            int min_part = activity.getInt("min_participants");
            Match a = new Match(type, date, location, min_part, max_part,"hej@test.se", 0);
            matchList.add(a);
            }
            catch(JSONException e){
            }


        }



            Intent intent = new Intent(this, MatchActivity.class);
            intent.putParcelableArrayListExtra("matchList", matchList);
            startActivity(intent);
    }
}
