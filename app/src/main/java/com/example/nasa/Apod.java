package com.example.nasa;

public class Apod {

private String mDate;
private String mTitle;
private String mExplanation;
private String mUrl;

    public Apod(String mDate, String mTitle, String mExplanation, String mUrl) {
        this.mDate = mDate;
        this.mTitle = mTitle;
        this.mExplanation = mExplanation;
        this.mUrl = mUrl;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmExplanation() {
        return mExplanation;
    }

    public String getmUrl() {
        return mUrl;
    }
}
