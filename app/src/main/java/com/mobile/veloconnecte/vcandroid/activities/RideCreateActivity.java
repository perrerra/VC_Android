package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.entities.Ride;
import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.utils.database.BikeManager;
import com.mobile.veloconnecte.vcandroid.utils.database.RideManager;
import com.mobile.veloconnecte.vcandroid.utils.database.UserManager;

import java.util.Date;
import java.util.List;

public class RideCreateActivity extends AppCompatActivity {

    public static final String SSID = "VC_AP_Mode";
    public static final String PASSWORD = "veloconnecte";

    //TextView ssid;
    Spinner bikeSpinner;
    Button button;

    UserManager userManager;
    BikeManager bikeManager;
    RideManager rideManager;

    User currentUser;
    List<Bike> bikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_create);

        //this.ssid = (TextView) findViewById(R.id.ride_text_view_ssid);
        this.bikeSpinner = (Spinner) findViewById(R.id.ride_spinner_bikes);
        this.button = (Button) findViewById(R.id.ride_button_add);

        this.userManager = new UserManager(RideCreateActivity.this);
        this.currentUser = userManager.getUserByUsername("Toto");

        this.bikeManager = new BikeManager(RideCreateActivity.this);
        this.bikes = bikeManager.getBikesByUserId(this.currentUser.getId());

        ArrayAdapter<Bike> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, this.bikes);
        this.bikeSpinner.setAdapter(adapter);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bike selectedBike = (Bike) RideCreateActivity.this.bikeSpinner.getSelectedItem();

                rideManager = new RideManager(RideCreateActivity.this);

                Ride ride = new Ride();
                ride.setBike(selectedBike);
                ride.setStart_date(new Date());
                ride.setEnd_date(new Date());
                ride.setBike(selectedBike);
                ride.setUser(selectedBike.getUser());

                long rideId = rideManager.insertRide(ride);

                ride = rideManager.getRideById(rideId);

                //TODO connect to board wifi & insert new ride

                /*WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = String.format("\"%s\"", SSID);
                wifiConfig.preSharedKey = String.format("\"%s\"", PASSWORD);

                WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
                int netId = wifiManager.addNetwork(wifiConfig);


                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();*/

                Intent intent = new Intent(RideCreateActivity.this, RideCurrentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("RIDE", ride);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

}