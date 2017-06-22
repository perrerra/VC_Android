package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Bike;

public class BikeDetailActivity extends AppCompatActivity {

    EditText name;
    EditText type;
    EditText wheel_size;
    Button update_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_detail);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Bike bike = (Bike) bundle.getSerializable("BIKE");

        this.name = (EditText) findViewById(R.id.bike_edit_text_name);
        this.type = (EditText) findViewById(R.id.bike_edit_text_type);
        this.wheel_size = (EditText) findViewById(R.id.bike_edit_text_wheel_size);
        this.update_button = (Button) findViewById(R.id.bike_button_update);

        this.name.setText(bike.getName());
        this.type.setText(bike.getType());
        this.wheel_size.setText(""+bike.getWheel_size());

        this.update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Update bike
            }
        });

    }
}
