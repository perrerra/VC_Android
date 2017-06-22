package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.adapters.BikeAdapter;
import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.utils.database.BikeManager;
import com.mobile.veloconnecte.vcandroid.utils.database.RideManager;
import com.mobile.veloconnecte.vcandroid.utils.database.UserManager;

import java.util.ArrayList;
import java.util.List;

public class BikeListActivity extends AppCompatActivity {

    UserManager userManager;
    BikeManager bikeManager;
    RideManager rideManager;

    User currentUser;
    List<Bike> bikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_list);

        this.bikes = this.getUserBikes();

        final BikeAdapter bikeAdapter = new BikeAdapter(this, this.bikes);
        ListView bikeList = (ListView) findViewById(R.id.bike_list_view);
        bikeList.setAdapter(bikeAdapter);

        bikeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bike bike = bikeAdapter.getItem(position);

                Intent intent = new Intent(BikeListActivity.this, BikeDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<Bike> getUserBikes(){
        this.userManager = new UserManager(BikeListActivity.this);
        this.currentUser = userManager.getUserByUsername("Toto");

        this.bikeManager = new BikeManager(BikeListActivity.this);

        List<Bike> bikes = new ArrayList<>();
        bikes = bikeManager.getBikesByUserId(this.currentUser.getId());
        return bikes;
    }
}
