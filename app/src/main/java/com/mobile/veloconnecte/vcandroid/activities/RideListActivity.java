package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.adapters.RideAdapter;
import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.entities.Ride;
import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.utils.database.BikeManager;
import com.mobile.veloconnecte.vcandroid.utils.database.RideManager;
import com.mobile.veloconnecte.vcandroid.utils.database.UserManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RideListActivity extends AppCompatActivity {

    ListView rideList;
    FloatingActionButton button;

    UserManager userManager;
    BikeManager bikeManager;
    RideManager rideManager;

    User currentUser;
    List<Ride> rides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_list);

        this.rides = this.generateRides();

        final RideAdapter rideAdapter = new RideAdapter(this, this.rides);
        this.rideList = (ListView) findViewById(R.id.ride_list_view);
        this.rideList.setAdapter(rideAdapter);

        this.rideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ride ride = rideAdapter.getItem(position);

                Intent intent = new Intent(RideListActivity.this, RideDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("RIDE", ride);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        this.button = (FloatingActionButton) findViewById(R.id.ride_button_add);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RideListActivity.this, RideCreateActivity.class);
                RideListActivity.this.startActivity(intent);
            }
        });

    }

    private List<Ride> generateRides(){

        this.userManager = new UserManager(RideListActivity.this);
        this.bikeManager = new BikeManager(RideListActivity.this);
        this.rideManager = new RideManager(RideListActivity.this);

        //User userInsert = new User("Toto", "123", "email@email.com", false);
        //userManager.insertUser(userInsert);

        User user = userManager.getUserByUsername("Toto");

//        Bike bike = new Bike();
//        bike.setName("Bike2");
//        bike.setType("Vtt");
//        bike.setWheel_size(10);
//        bike.setUser(user);
//        bikeManager.insertBike(bike);

        //List<Bike> bikes = bikeManager.getBikes();

        /*for (int i=0; i < 5; i++) {
            Ride ride = new Ride();
            ride.setStart_date(new Date());
            ride.setEnd_date(new Date());
            ride.setBike(bike);
            ride.setUser(user);
            rideManager.insertRide(ride);
            //this.rides.add(ride);
        }*/

        rideManager.deleteAllRides();
        List<Ride> rides = new ArrayList<>();

        rides = rideManager.getRidesByUserId(user.getId());

        return rides;
    }

}
