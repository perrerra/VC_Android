package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Bike;
import com.mobile.veloconnecte.vcandroid.utils.database.BikeManager;

public class BikeDetailActivity extends AppCompatActivity {

    EditText name;
    EditText type;
    EditText wheel_size;
    Button update_button;

    BikeManager bikeManager;

    Bike selectedBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_detail);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        this.selectedBike = (Bike) bundle.getSerializable("BIKE");

        this.name = (EditText) findViewById(R.id.bike_edit_text_name);
        this.type = (EditText) findViewById(R.id.bike_edit_text_type);
        this.wheel_size = (EditText) findViewById(R.id.bike_edit_text_wheel_size);
        this.update_button = (Button) findViewById(R.id.bike_button_update);

        this.name.setText(selectedBike.getName());
        this.type.setText(selectedBike.getType());
        this.wheel_size.setText(String.valueOf(selectedBike.getWheel_size()));

        this.update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Update bike

                selectedBike.setName(name.getText().toString());
                selectedBike.setType(type.getText().toString());
                selectedBike.setWheel_size(Integer.parseInt(wheel_size.getText().toString()));

                bikeManager = new BikeManager(BikeDetailActivity.this);
                bikeManager.updateBike(selectedBike);

                Toast toast = Toast.makeText(BikeDetailActivity.this,  selectedBike.getName() + "updated", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
