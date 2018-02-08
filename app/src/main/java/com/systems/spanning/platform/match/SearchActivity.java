package com.systems.spanning.platform.match;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus on 2018-02-07.
 */

public class SearchActivity extends AppCompatActivity {
    Spinner spinner;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        spinner = findViewById(R.id.select_activity);
        spinner.setOnItemSelectedListener(new OnItemSelected());

    }
    public void SearchClick(View view){
        Intent intent = new Intent(this, MatchActivity.class);
        startActivity(intent);
    }

}
