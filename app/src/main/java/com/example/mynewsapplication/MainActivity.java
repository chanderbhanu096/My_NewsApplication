package com.example.mynewsapplication;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<CustomArray>> {
    MainActivityBinder
    ArrayList<CustomArray> customArrays;
    CustomArrayAdapter customArrayAdapter;
    ListView listView;

    LinearLayout linearLayout;
    TextView titleText, exceptionText, exceptionText2, urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        exceptionText2 = findViewById( R.id.exception2 );
        exceptionText = findViewById( R.id.exception1 );
        setContentView( R.layout.activity_main );
        customArrays = new ArrayList<>();

        getSupportLoaderManager().initLoader( 1, null, this );
        listView = (ListView) findViewById( R.id.list );

        if (isConnectedToInternet()) {

            customArrayAdapter = new CustomArrayAdapter( this, 0, customArrays );

            listView.setAdapter( customArrayAdapter );
        } else {

            exceptionText2.setVisibility( View.GONE );
        }
    }

    private Boolean isConnectedToInternet() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService( getApplicationContext().CONNECTIVITY_SERVICE );
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @NonNull
    @Override
    public Loader<ArrayList<CustomArray>> onCreateLoader(int id, @Nullable Bundle args) {
        NewsAsyncLoader newsAsyncLoader = new NewsAsyncLoader( this, customArrays );
        newsAsyncLoader.forceLoad();
        return newsAsyncLoader;

    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<CustomArray>> loader, ArrayList<CustomArray> data) {

        if (data != null) {
            customArrayAdapter.addAll( data );

        } else {


        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<CustomArray>> loader) {
        listView.setAdapter( null );
    }
}
