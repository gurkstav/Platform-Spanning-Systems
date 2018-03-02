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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        new GetData("http://192.168.1.2:8000/activities",this).execute();
    }

    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void fetchDataCallback(JSONArray result) {
        if (result == null) {
            Toast.makeText(this, "Could not connect with server, please try again later", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<Match> matchList = fetchCardParams.fetchParams(result);

            Intent intent = new Intent(this, MatchActivity.class);
            intent.putParcelableArrayListExtra("matchList", matchList);
            startActivity(intent);
        }
    }
}
