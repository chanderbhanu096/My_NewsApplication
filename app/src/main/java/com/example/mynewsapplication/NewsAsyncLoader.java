package com.example.mynewsapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsAsyncLoader extends AsyncTaskLoader<ArrayList<CustomArray>> {
    ArrayList<CustomArray> customArrays;

    public NewsAsyncLoader(@NonNull Context context, ArrayList<CustomArray> customArrays) {
        super( context );
        this.customArrays = customArrays;
    }

    @Override
    public void deliverResult(@Nullable ArrayList<CustomArray> customArrays) {
        super.deliverResult( customArrays );
    }

    @Nullable
    @Override
    public ArrayList<CustomArray> loadInBackground() {
        URL url = null;
        try {
            url = Utils.getUrl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            setDataToList( Utils.sendRequestToServer( url ) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return customArrays;
    }

    private void setDataToList(String data) throws JSONException {
        JSONObject response = new JSONObject( data );
        JSONObject articles = response.getJSONObject( "response" );
        JSONArray results = articles.getJSONArray( "results" );
        for (int i = 0; i < results.length(); i++) {
            JSONObject object = results.getJSONObject( i );
            String Title = object.getString( "webTitle" );
            String Section = object.getString( "sectionName" );
            String abc = object.getString( "webPublicationDate" );
            String[] parts = abc.split( "T" );
            String Date = parts[0];
            String url = object.getString( "webUrl" );

            CustomArray abcd = new CustomArray( Title, Section, Date, url );
            customArrays.add( abcd );
        }
    }
}
