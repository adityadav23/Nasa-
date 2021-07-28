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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ApodActivity1 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Apod>> {


    // declaring empty state textview
    private TextView mEmptyState;
    private ApodAdapter mAdapter;
    //emptyState textview for listview

    private final static String nasaUrl ="https://api.nasa.gov/planetary/apod?api_key=Q2LSnoceQDU98gXRFEqQ30nLOxmafVK3LGuzsefK";
    //Loader id
    private static final int EARTHQUAKE_lOADER_ID =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod1);


        ListView listview = findViewById(R.id.list);
        // Setting emptyView to listview
        mEmptyState = findViewById(R.id.emptyState);
        listview.setEmptyView(mEmptyState);
        mAdapter = new ApodAdapter(this,new ArrayList<>());
        listview.setAdapter(mAdapter);



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

        //Loading indicator set to invisible
        View loadingIndicator = findViewById(R.id.loadingIndicator);
        loadingIndicator.setVisibility(View.GONE);
        //setting emptyState textview, so if no data is found it will be loaded
        mEmptyState.setText(R.string.noDataFound);
        mAdapter.clear();
        // adding apodArraylist data to adapter
        if(apodList != null && !apodList.isEmpty()){
            mAdapter.addAll(apodList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Apod>> loader) {

        mAdapter.clear();
    }


}

