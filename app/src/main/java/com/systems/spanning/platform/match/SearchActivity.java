package com.systems.spanning.platform.match;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements PostDataArrayInterface,
        DatePickerDialog.OnDateSetListener{

    Spinner spinner;
    Button button_pick_date;
    TextView pick_date_results;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;

    int PLACE_PICKER_REQUEST = 1;
    String location;
    String address;
    String date;
    int numMin = 0;
    int numMax = 0;
    NumberPicker numberpicker_min2;
    NumberPicker numberpicker_max2;
    TextView typeOfType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        spinner = findViewById(R.id.select_activity);
        typeOfType = findViewById(R.id.textInputActivityType);


        final TextView textview_min2 = findViewById(R.id.textview_min2);
        numberpicker_min2 = findViewById(R.id.numberpicker_min2);

        final TextView textview_max2 = findViewById(R.id.textview_max2);
        numberpicker_max2 = findViewById(R.id.numberpicker_max2);

        textview_min2.setTextColor(Color.parseColor("#000000"));
        textview_max2.setTextColor(Color.parseColor("#000000"));

        numberpicker_min2.setMinValue(2);
        numberpicker_min2.setMaxValue(20);
        numberpicker_min2.setWrapSelectorWheel(true);

        numberpicker_max2.setMinValue(0);
        numberpicker_max2.setMaxValue(20);
        numberpicker_max2.setWrapSelectorWheel(true);

        numberpicker_min2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                textview_min2.setText("Select minimum \namount of \nparticipants: " + newVal);
                numMin = newVal;
            }
        });

        numberpicker_max2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal2, int newVal2){
                textview_max2.setText("Select maximum \namount of \nparticipants: " + newVal2);
                numMax = newVal2;
            }
        });

        button_pick_date = findViewById(R.id.button_pick_date_time);
        pick_date_results = findViewById(R.id.pick_date_time_results);

        button_pick_date.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, (DatePickerDialog.OnDateSetListener) SearchActivity.this,
                        year, month, day);
                datePickerDialog.show();
                }
             });

        Button button_pick_location = findViewById(R.id.pick_location_button);

        button_pick_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlacePickerActivity(view);
            }
        });
    }

    private void startPlacePickerActivity(View view){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = builder.build((Activity) view.getContext());
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            displaySelectedPlaceForPlacePicker(data);
        }
    }

    private void displaySelectedPlaceForPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, this);

        location = placeSelected.getName().toString();
        address = placeSelected.getAddress().toString();

        TextView selectedLocation = (TextView) findViewById(R.id.pick_location_results);
        selectedLocation.setText(address);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        date = dayFinal + "-" + monthFinal + "-" + yearFinal;
    }


    public void SearchClick(View view){
        HashMap<String, String> postData = new HashMap<>();
        String TypeOfType = typeOfType.getText().toString();

        if(location != null){
          //  postData.put("location", location);
        }
        if(date != null){
            // postData.put("date",date);
        }
        if(numMin != numberpicker_min2.getMinValue()){
            // postData.put("min_participants",numMin);
        }
        if(numMax > 2){
            // postData.put("max_participants",numMax);
        }
        if(!TypeOfType.isEmpty()){
            // postData.put("max_participants",numMax);
        }

        String type = spinner.getSelectedItem().toString();
        postData.put("type", type);

        new PostDataArray("http://10.0.2.2:8000/search", postData , this).execute();
        //10.0.2.2
        //192.168.1.2
    }

    public void homeClick(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void fetchDataCallback(JSONArray result) {
        if (result == null) {
            Toast.makeText(this, "Could not connect with server, please try again later", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<Match> matchList = fetchCardParams.fetchParams(result);

            Intent intent = new Intent(this, MatchActivity.class);
            intent.putParcelableArrayListExtra("matchList", matchList);
            startActivity(intent);
        }
    }
}
