package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Marcus on 2018-02-09.
 */

public class HomeActivity extends AppCompatActivity {
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
        Intent intent = new Intent(this, OwnActivities.class);
        startActivity(intent);
    }

}
