package com.example.nasa;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ApodActivity1 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Apod>> {


    private ApodAdapter mAdapter;
    private final static String nasaUrl ="https://api.nasa.gov/planetary/apod?api_key=Q2LSnoceQDU98gXRFEqQ30nLOxmafVK3LGuzsefK";
        //Loader id
      private static final int EARTHQUAKE_lOADER_ID =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod1);


//        StringBuilder query = new StringBuilder();
//        query.append(nasaUrl);


        ListView listview = findViewById(R.id.list);
        mAdapter = new ApodAdapter(this,new ArrayList<>());
        listview.setAdapter(mAdapter);
//
//        EditText startDateEt = findViewById(R.id.startDate);
//        EditText endDateEt = findViewById(R.id.endDate);
//
//
//
//        Button button = findViewById(R.id.submitButton);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String startDate = "&start_date="+startDateEt.getText().toString() ;
//                String endDate = "&end_date="+endDateEt.getText().toString();
//                query.append(startDate);
//                query.append(endDate);
//
//                query.toString();
//
//            }
//        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Find the current earthquake that was clicked on
                Apod currentApod = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri nasaUri = Uri.parse(currentApod.getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, nasaUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);


            }
        });

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_lOADER_ID,null,this);
    }

    @Override
    public Loader<List<Apod>> onCreateLoader(int id, Bundle bundle) {
        return new ApodLoader(this, nasaUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Apod>> loader, List<Apod> apodList) {

        mAdapter.clear();
        if(apodList != null && !apodList.isEmpty()){
            mAdapter.addAll(apodList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Apod>> loader) {

        mAdapter.clear();
    }


}

