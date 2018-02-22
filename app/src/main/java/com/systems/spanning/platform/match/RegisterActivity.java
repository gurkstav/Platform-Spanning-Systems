package com.systems.spanning.platform.match;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements PostDataInterface{
    HashMap<String, String> postData = new HashMap<>();
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText ssn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.textInputEditTextName);
        email = findViewById(R.id.textInputEditTextEmail);
        password = findViewById(R.id.textInputEditTextPasswordRepeat);
        ssn = findViewById(R.id.textInputEditTextSSN);

    }

    public void RegisterClick(View view){
      postData.put("full_name", name.getText().toString());
      postData.put("ssn", name.getText().toString());
      postData.put("email", name.getText().toString());
      postData.put("pwd", name.getText().toString());
      postData.put("ssn", ssn.getText().toString());

      new PostData("http://192.168.1.2:1000/users", postData, this).execute();
    }

    @Override
    public void fetchDataCallback(String result) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
