package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.entities.Ride;
import com.mobile.veloconnecte.vcandroid.utils.database.RideManager;

import java.util.Date;

public class RideCurrentActivity extends AppCompatActivity {

    Button button;

    RideManager rideManager;

    Ride currentRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_current);

        Toast toast = Toast.makeText(RideCurrentActivity.this,  "New ride started", Toast.LENGTH_SHORT);
        toast.show();

        this.rideManager = new RideManager(RideCurrentActivity.this);
        this.button = (Button) findViewById(R.id.ride_button_stop);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        this.currentRide = (Ride) bundle.getSerializable("RIDE");

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentRide.setEnd_date(new Date());
                currentRide.setDescription("test description");
                int count = rideManager.updateRide(currentRide);

                if (count == 1)
                {
                    currentRide =  rideManager.getRideById(currentRide.getId());
                    Intent intent = new Intent(RideCurrentActivity.this, RideListActivity.class);
                    RideCurrentActivity.this.startActivity(intent);
                }
            }
        });
    }
}
