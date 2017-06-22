package com.mobile.veloconnecte.vcandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.Ride;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by guillaumetostain on 21/06/2017.
 */

public class RideAdapter extends ArrayAdapter<Ride> {

    private Context context;
    private List<Ride> rides;
    private RideViewHolder viewHolder;

    public RideAdapter(Context context, List<Ride> rides){
        super(context, 0, rides);

        this.context = context;
        this.rides = rides;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ride_list_item, parent, false);
        }

        RideViewHolder viewHolder = (RideViewHolder) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new RideViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.ride_item_text_view_date);
            viewHolder.bike = (TextView) convertView.findViewById(R.id.ride_item_text_view_bike);

            convertView.setTag(viewHolder);
        }

        Ride ride = getItem(position);

        if (ride != null) {
            String dateString = DateFormat.getDateInstance().format(ride.getStart_date());
            viewHolder.date.setText(dateString);
            viewHolder.bike.setText(ride.getBike().getName());
        }

        return convertView;
    }

    private class RideViewHolder{
        TextView date;
        TextView bike;
    }
}
