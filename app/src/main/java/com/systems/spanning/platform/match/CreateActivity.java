package com.systems.spanning.platform.match;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
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

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;

/**
 * Created by Gurkstav on 2018-02-12.
 */

public class CreateActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Button button_pick_date_time;
    TextView pick_date_time_results;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    int PLACE_PICKER_REQUEST = 1;
    Button button_pick_location;
    TextView pick_location_results;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);

        final TextView textview_min = findViewById(R.id.textview_min);
        NumberPicker numberpicker_min = findViewById(R.id.numberpicker_min);

        final TextView textview_max = findViewById(R.id.textview_max);
        NumberPicker numberpicker_max = findViewById(R.id.numberpicker_max);

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
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                textview_min.setText("Select minimum \namount of \nparticipants: " + newVal);
            }
        });

        numberpicker_max.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal2, int newVal2){
                textview_max.setText("Select maximum \namount of \nparticipants: " + newVal2);
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

        button_pick_location = findViewById(R.id.pick_location_button);
        pick_location_results = findViewById(R.id.pick_location_results);

        button_pick_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent;
                try {
                    intent = builder.build((Activity) getApplicationContext());
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String address = String.format("Place: %s", place.getAddress());
                pick_location_results.setText(address);
            }
        }
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

}
