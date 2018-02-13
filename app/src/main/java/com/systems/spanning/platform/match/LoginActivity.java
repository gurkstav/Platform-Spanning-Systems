package com.systems.spanning.platform.match;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            }

    public void RegisterClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void LoginClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

    public void ForgotPasswordClick(View view){
        Intent intent = new Intent(this, ForgotActivity.class);
        startActivity(intent);

    }


}
