package com.systems.spanning.platform.match;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
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

    /**
    public void newSearchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
     */

    Button button_pick_date_time;
    TextView pick_date_time_results;

    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    int PLACE_PICKER_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);


        button_pick_date_time = (Button) findViewById(R.id.button_pick_date_time);
        pick_date_time_results = (TextView) findViewById(R.id.pick_date_time_results);

        button_pick_date_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this, CreateActivity.this,
                        year, month, day);
                datePickerDialog.show();
            }
        });

        Button pick_location = (Button) findViewById(R.id.button_pick_location);
        pick_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent;
                try {
                    intent = builder.build(getApplicationContext());
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
                Place place = PlacePicker.getPlace(data, this);
                String address = String.format("Place: %s", place.getAddress());
                get_place.setText(address);
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

        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateActivity.this, CreateActivity.this,
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
