package com.example.mynewsapplication;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    public static final String API_KEY = "fb71a230-3cde-49b0-b959-b68fbb55e9b9";
    public static final String API_URL = "https://content.guardianapis.com/search?q=headline?";

    public static URL getUrl() throws MalformedURLException {
        Uri uri = Uri.parse( API_URL );
        Uri.Builder uriBuilder = uri.buildUpon();
        uriBuilder.appendQueryParameter( "api-key", API_KEY );
        URL url = new URL( uriBuilder.toString() );
        return url;
    }

    public static String sendRequestToServer(URL url) {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        if (url != null) {

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod( "GET" );
                urlConnection.setReadTimeout( 10000 );
                urlConnection.setConnectTimeout( 10000 );
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = convertInputIntoString( inputStream );


            } catch (Exception e) {
                Log.e( "tag1", e.getMessage() );

            }
        }
        Log.e( "tag", jsonResponse );
        return jsonResponse;
    }

    private static String convertInputIntoString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append( line );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return stringBuilder.toString();
    }
}
