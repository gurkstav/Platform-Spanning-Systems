package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Marcus on 2018-02-09.
 */

public class ForgotPasswordActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void LoginClick(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
