package com.systems.spanning.platform.match;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements PostDataInterface{
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText passwordRepeat;
    private EditText ssn;
    private String Name;
    private String SSN;
    private String Email;
    private String Password;
    private String PasswordRepeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.textInputEditTextName);
        email = findViewById(R.id.textInputEditTextEmail);
        password = findViewById(R.id.textInputEditTextPassword);
        passwordRepeat = findViewById(R.id.textInputEditTextPasswordRepeat);
        ssn = findViewById(R.id.textInputEditTextSSN);


    }

    public void RegisterClick(View view){
        Name = name.getText().toString();
        SSN = ssn.getText().toString();
        Email = email.getText().toString();
        Password = password.getText().toString();
        PasswordRepeat = passwordRepeat.getText().toString();

        if(Name.isEmpty() || SSN.isEmpty() || Email.isEmpty() || Password.isEmpty()){
            Toast.makeText(this, "Please fill in all information", Toast.LENGTH_SHORT).show();
        }
        if(!Password.equals(PasswordRepeat)){
            Log.println(Log.INFO,"E", Password);

            Log.println(Log.INFO,"E", PasswordRepeat);
            Toast.makeText(this, "Passwords must match", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String, String> postData = new HashMap<>();
            postData.put("full_name", Name);
            postData.put("ssn", SSN);
            postData.put("email", Email);
            postData.put("pwd", Password);

            new PostData("http://192.168.1.2:1000/users", postData, this).execute();
        }
    }

    @Override
    public void fetchDataCallback(String result) {
        Toast.makeText(this, "You are now registered!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
