package com.example.mynewsapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<CustomArray> {
    ArrayList<CustomArray> customArrays;

    public CustomArrayAdapter(@NonNull Context context, int resource, ArrayList<CustomArray> customArrays) {
        super( context, resource, customArrays );
    }


    public void setCustomArrays(ArrayList<CustomArray> customArrays) {
        this.customArrays = customArrays;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from( getContext() ).inflate( R.layout.listitem, parent, false );
        }
        final CustomArray currentItem = getItem( position );

        TextView textView1 = (TextView) listItemView.findViewById( R.id.title );
        textView1.setText( currentItem.getmTitle() );

        TextView textView3 = (TextView) listItemView.findViewById( R.id.details );
        textView3.setText( currentItem.getmSection() );

        TextView textView4 = (TextView) listItemView.findViewById( R.id.date );
        textView4.setText( currentItem.getmDate() );

        TextView textView5 = (TextView) listItemView.findViewById( R.id.get_url );
        textView5.setText( currentItem.getmSource_url() );
        textView5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse( currentItem.getmSource_url() )
                );
                getContext().startActivity( i );
            }
        } );

        return listItemView;
    }
}
