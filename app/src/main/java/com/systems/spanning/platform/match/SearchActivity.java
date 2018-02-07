package com.systems.spanning.platform.match;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Marcus on 2018-02-07.
 */

public class SearchActivity extends AppCompatActivity {
    Spinner spinner;
    private Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        spinner = findViewById(R.id.select_activity);
        spinner.setOnItemSelectedListener(new OnItemSelected());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_login);
            }
        });
    }
}
