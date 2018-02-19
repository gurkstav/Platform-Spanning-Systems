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
        new GetData("http://www.android.com/",this).execute();
    }

    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void fetchDataCallback(String result) {
            Match a = new Match("first one", R.drawable.regbtn);
            ArrayList<Match> matchList = new ArrayList<>();
            matchList.add(a);

            Intent intent = new Intent(this, MatchActivity.class);
            intent.putParcelableArrayListExtra("matchList", matchList);
            startActivity(intent);
    }
}
