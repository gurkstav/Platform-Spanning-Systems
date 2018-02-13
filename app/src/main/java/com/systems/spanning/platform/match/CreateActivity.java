package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Gurkstav on 2018-02-12.
 */

public class CreateActivity extends AppCompatActivity{

    /**
    public void newSearchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);
    }



    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
