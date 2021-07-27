package com.example.nasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ApodAdapter extends ArrayAdapter<Apod> {

    private  Context context;
    private ArrayList<Apod> apodArray;


    public ApodAdapter( Context context,  ArrayList<Apod> objects) {
        super(context, R.layout.apod_list_item, objects);
        this.context= context;
        this.apodArray = objects;
    }


    @Override
    public View getView(int position,  View rowView,  ViewGroup parent) {
        if(rowView== null){
            rowView= LayoutInflater.from(getContext()).inflate(R.layout.apod_list_item, parent , false);
        }

        Apod currentApod= getItem(position);

        String title = currentApod.getmTitle();
        String explanation = currentApod.getmExplanation();
        String date = currentApod.getmDate();
        String url = currentApod.getmUrl();



        TextView titleTextView = rowView.findViewById(R.id.titleTextView);
        titleTextView.setText(title);

        TextView explanationTextView = rowView.findViewById(R.id.explanationTextView);
        explanationTextView.setText(explanation);

        TextView dateTextView = rowView.findViewById(R.id.dateTextView);
        dateTextView.setText(date);



        return rowView;
    }
}
