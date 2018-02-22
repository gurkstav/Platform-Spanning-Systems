package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements GetDataInterface {
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

    }

    public void newGoToSearchActivity(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void newGoToCreateActivity(View view){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void newGoToShowOwnActivity(View view){
        new GetData("http://192.168.1.2:1000/activities",this).execute();
    }

    @Override
    public void fetchDataCallback(JSONArray result) {
        ArrayList<Match> matchList = new ArrayList<>();

        for (int i = 0; i < result.length(); i++) {
            try{
                JSONObject activity = result.getJSONObject(i);
                String type = activity.getString("type");
                String date = activity.getString("date");
                String location = activity.getString("location");
                String max_part = activity.getString("max_participants");
                String min_part = activity.getString("min_participants");
                Match a = new Match(type, date, location, min_part, max_part,null, 0);
                matchList.add(a);
            }
            catch(JSONException e){
            }
        }

        Intent intent = new Intent(this, OwnActivities.class);
        intent.putParcelableArrayListExtra("matchList", matchList);
        startActivity(intent);
    }
}
