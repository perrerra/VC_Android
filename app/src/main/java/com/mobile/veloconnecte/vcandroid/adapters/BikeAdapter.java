package com.mobile.veloconnecte.vcandroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Bike;

import java.util.List;

/**
 * Created by guillaumetostain on 17/05/2017.
 */

public class BikeAdapter extends ArrayAdapter<Bike> {

    private Context context;
    private List<Bike> bikes;
    private BikeViewHolder viewHolder;

    public BikeAdapter(Context context, List<Bike> bikes){
        super(context, 0, bikes);

        this.context = context;
        this.bikes = bikes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bike_list_item, parent, false);
        }

        BikeViewHolder viewHolder = (BikeViewHolder) convertView.getTag();

        if (viewHolder == null){
            viewHolder = new BikeViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.bike_item_text_view_name);
            viewHolder.type = (TextView) convertView.findViewById(R.id.bike_item_text_view_type);
            //viewHolder.delete_button = (ImageButton) convertView.findViewById(R.id.bike_item_button_delete);

            convertView.setTag(viewHolder);
        }

        Bike bike = getItem(position);

        if (bike != null){
            viewHolder.name.setText(bike.getName());
            viewHolder.type.setText(bike.getType());
        }

        /*viewHolder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO delete bike
            }
        });*/


        return convertView;
    }

    private class BikeViewHolder{
        public TextView name;
        public TextView type;
    }
}
