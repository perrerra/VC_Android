package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Ride;

import java.util.Date;

public class RideDetailActivity extends AppCompatActivity {

    TextView duration;
    TextView distance;
    TextView average_speed;

    Ride selectedRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_detail);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        this.selectedRide = (Ride) bundle.getSerializable("RIDE");

        this.duration = (TextView) findViewById(R.id.ride_detail_duration);
        this.distance = (TextView) findViewById(R.id.ride_detail_distance);
        this.average_speed = (TextView) findViewById(R.id.ride_detail_average_speed);

        long totalSecs = (this.selectedRide.getEnd_date().getTime() - this.selectedRide.getStart_date().getTime()) / 1000;

        long hours = totalSecs / 3600;
        long minutes = (totalSecs % 3600) / 60;
        long seconds = totalSecs % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        this.duration.setText(String.valueOf(timeString));
        this.distance.setText("distance");
        this.average_speed.setText("average_speed");
    }
}
