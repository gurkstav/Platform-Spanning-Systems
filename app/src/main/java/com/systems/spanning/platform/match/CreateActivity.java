package com.systems.spanning.platform.match;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.NumberPicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Gurkstav on 2018-02-12.
 */

public class CreateActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Button button_pick_date_time;
    TextView pick_date_time_results;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    private final int PLACE_PICKER_REQUEST = 1;
    TextView pick_location_results;

    private String Pick_date_and_time_results;
    private String Pick_location_results;

    private String title;
    private String description;
    private String type;
    private String date;
    private String time;
    private String location;
    private String min_participants;
    private String max_participants;
    private String email;

    Spinner spinner;
    TextView Type;
    NumberPicker numberpicker_min;
    NumberPicker numberpicker_max;
    TextView Description;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        spinner = findViewById(R.id.select_activity);
        Type = findViewById(R.id.textInputActivityType);
        Description = findViewById(R.id.textInputDescription);

        final TextView textview_min = findViewById(R.id.textview_min);
        numberpicker_min = findViewById(R.id.numberpicker_min);

        final TextView textview_max = findViewById(R.id.textview_max);
        numberpicker_max = findViewById(R.id.numberpicker_max);

        textview_min.setTextColor(Color.parseColor("#000000"));
        textview_max.setTextColor(Color.parseColor("#000000"));

        numberpicker_min.setMinValue(0);
        numberpicker_min.setMaxValue(100);
        numberpicker_min.setWrapSelectorWheel(true);

        numberpicker_max.setMinValue(0);
        numberpicker_max.setMaxValue(100);
        numberpicker_max.setWrapSelectorWheel(true);

        numberpicker_min.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int minVal){
                textview_min.setText("Select \nminimum \namount of \nparticipants: " + minVal);
                String.valueOf(minVal);
            }
        });

        numberpicker_max.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal2, int maxVal){
                textview_max.setText("Select \nmaximum \namount of \nparticipants: " + maxVal);
                String.valueOf(maxVal);
            }
        });

        button_pick_date_time = findViewById(R.id.button_pick_date_time);
        pick_date_time_results = findViewById(R.id.pick_date_time_results);

        button_pick_date_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this, (DatePickerDialog.OnDateSetListener) CreateActivity.this,
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

        //String name = placeSelected.getName().toString();
        location = placeSelected.getAddress().toString();

        TextView selectedLocation = (TextView) findViewById(R.id.pick_location_results);
        selectedLocation.setText(location);
    }


    public void homeClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateActivity.this, (TimePickerDialog.OnTimeSetListener) CreateActivity.this,
                hour, minute, android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourFinal = i;
        minuteFinal = i1;

        pick_date_time_results.setText("Date: " + dayFinal + "-" + monthFinal + "-" + yearFinal + "\n" +
                "Time: " + hourFinal + ":" + minuteFinal);
    }

    public void createActivity(View view){

        title = Type.getText().toString();
        description = Description.getText().toString();
        type = spinner.getSelectedItem().toString();

        date = (dayFinal + "-" + monthFinal + "-" + yearFinal);
        time = (hourFinal + ":" + minuteFinal);

        //email = something.getText().toString();
        String email = "hejsan";
        location = "hemma";

        HashMap<String, String> postData = new HashMap<>();
        postData.put("title", title);
        postData.put("description", description);
        postData.put("type", type);
        postData.put("date", date);
        postData.put("time", time);
        postData.put("location", location);
        postData.put("min_participants", min_participants);
        postData.put("max_participants", max_participants);
        postData.put("email", email);

        new PostData("http://10.0.2.2:8000/create", postData, (PostDataInterface) this).execute();

    }



}
