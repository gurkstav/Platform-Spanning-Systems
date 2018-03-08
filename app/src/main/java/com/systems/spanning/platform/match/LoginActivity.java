package com.systems.spanning.platform.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements PostDataInterface{

    private EditText email;
    private EditText password;
    private TextView wrongPassword;
    private String Email;
    private String Password;



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

        Email = email.getText().toString();
        Password = password.getText().toString();

        if (Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show();
        }
         else {
            HashMap<String, String> postData = new HashMap<>();
            postData.put("email", Email);
            postData.put("password", Password);

            new PostData("http://192.168.1.2:8000/login", postData, this).execute();
            //10.0.2.2
        }
    }

    public void ForgotPasswordClick(View view){
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);

    }

    @Override
    public void fetchDataCallback(JSONObject result) {
        try{
            if(result == null){
                Toast.makeText(this, "Could not connect with server, please try again later", Toast.LENGTH_SHORT).show();
            }
            else {
                if (result.getBoolean("success")) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, result.getString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(JSONException jse){

        }
    }
}
