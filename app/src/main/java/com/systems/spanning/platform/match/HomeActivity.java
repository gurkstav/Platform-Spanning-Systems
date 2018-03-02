package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
        new GetData("http://192.168.1.2:8000/activities",this).execute();
    }

    @Override
    public void fetchDataCallback(JSONArray result) {
        if (result == null) {
            Toast.makeText(this, "Could not connect with server, please try again later", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<Match> matchList = fetchCardParams.fetchParams(result);

            Intent intent = new Intent(this, OwnActivities.class);
            intent.putParcelableArrayListExtra("matchListOwn", matchList);
            startActivity(intent);
        }
    }
}
