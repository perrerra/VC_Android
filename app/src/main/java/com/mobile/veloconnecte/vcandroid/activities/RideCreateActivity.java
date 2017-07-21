package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.entities.Ride;
import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.utils.database.BikeManager;
import com.mobile.veloconnecte.vcandroid.utils.database.RideManager;
import com.mobile.veloconnecte.vcandroid.utils.database.UserManager;
import com.mobile.veloconnecte.vcandroid.utils.volley.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

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
                ride.setBike(selectedBike);
                ride.setUser(selectedBike.getUser());

                try {
                    postRide(ride);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                long rideId = rideManager.insertRide(ride);

                ride = rideManager.getRideById(rideId);

                //TODO connect to board wifi

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

    private void postRide(Ride ride) throws JSONException {
        String url = "http://10.3.5.37:3000/rides";

        Gson gson = new Gson();
        String jsonRide = gson.toJson(ride);

        JSONObject jsonObject = new JSONObject(jsonRide);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Access the RequestQueue through your singleton class.
        MyVolley.getInstance(RideCreateActivity.this).addToRequestQueue(jsObjRequest);
    }

}