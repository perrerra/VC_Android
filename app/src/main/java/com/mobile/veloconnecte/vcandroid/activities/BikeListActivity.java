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

import java.util.ArrayList;
import java.util.List;

public class BikeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_list);

        final BikeAdapter bikeAdapter = new BikeAdapter(this, this.generateBikes());
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

    private List<Bike> generateBikes(){
        List<Bike> bikes = new ArrayList<>();
        for (int i=0; i < 5; i++) {
            Bike bike = new Bike();
            bike.setName("Bike" + i);
            bike.setType("Vtt");
            bikes.add(bike);
        }
        return bikes;

    }
}
