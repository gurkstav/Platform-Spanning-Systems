package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements GetDataInterface{

    private EditText email;
    private EditText password;
    private TextView wrongPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.enterEmail);
        password = findViewById(R.id.enterPassword);
        wrongPassword = findViewById(R.id.wrongPasswordText);

    }

    public void RegisterClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void LoginClick(View view){
        wrongPassword.setVisibility(View.INVISIBLE);
        new GetData("http://192.168.1.2:1000/activities", this).execute();
    }

    public void ForgotPasswordClick(View view){
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

    @Override
    public void fetchDataCallback(JSONArray result) {
        // wrongPassword.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
