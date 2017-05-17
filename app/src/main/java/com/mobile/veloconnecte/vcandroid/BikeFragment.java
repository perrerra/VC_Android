package com.mobile.veloconnecte.vcandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaumetostain on 15/05/2017.
 */

public class BikeFragment extends Fragment {

    /*// Define a ListView object
    ListView mBikesList;

    // An adapter that binds the result Cursor to the ListView
    private SimpleCursorAdapter mCursorAdapter;

    List<Bike> bikes = new ArrayList<Bike>();

    private final static String[] FROM_COLUMNS = {"name"};

    public BikeFragment(){ }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bike bike1 = new Bike();
        bike1.setName("bike1");

        Bike bike2 = new Bike();
        bike1.setName("bike1");

        this.bikes.add(bike1);
        this.bikes.add(bike2);

        // Gets the ListView from the View list of the parent activity
        mBikesList =
                (ListView) getActivity().findViewById(R.id.bike_list);

        // Gets a CursorAdapter
        mCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.bike_list_item,
                bikes,
                FROM_COLUMNS, R.id.text_view_bike_list_item,
                0);

        // Sets the adapter for the ListView
        mBikesList.setAdapter(mCursorAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the fragment layout
        return inflater.inflate(R.layout.bike_list_view,
                container, false);
    }

    public class Bike{
        protected String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }*/
}
