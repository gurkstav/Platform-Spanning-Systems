package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements GetDataInterface{


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
        new GetData("http://192.168.1.2:1000/activities", this).execute();

    }

    public void ForgotPasswordClick(View view){
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

    @Override
    public void fetchDataCallback(JSONArray result) {
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f,0.0f);
        alphaAnim.setStartOffset(5000);                        // start in 5 seconds
        alphaAnim.setDuration(400);
        alphaAnim.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {
                findViewById(R.id.wrongPasswordText).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                findViewById(R.id.wrongPasswordText).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        findViewById(R.id.wrongPasswordText).setAnimation(alphaAnim);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
