package com.example.nasa;

import android.app.DownloadManager;
import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ApodLoader extends AsyncTaskLoader<List<Apod>> {

    private static final String LOG_TAG = ApodLoader.class.getSimpleName();
    private String mUrl;

    public ApodLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    public List<Apod> loadInBackground() {

        if(mUrl==null){
            return null;
        }
        List<Apod> apodList = QueryUtils.fetchData(mUrl);
        return apodList;
    }


    @Override
    protected void onStartLoading() {
       forceLoad();
    }
}
