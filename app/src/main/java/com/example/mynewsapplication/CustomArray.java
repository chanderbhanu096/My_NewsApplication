package com.example.mynewsapplication;

public class CustomArray {
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mSource_url;

    public CustomArray(String title, String section, String date, String sourceUrl) {
        this.mTitle = title;
        this.mSection = section;
        this.mDate = date;
        this.mSource_url = sourceUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmSource_url() {
        return mSource_url;
    }

}
