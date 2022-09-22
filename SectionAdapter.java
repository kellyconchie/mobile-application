package com.example.testingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


// This adapter class is  populating the listView

public class SectionAdapter  extends ArrayAdapter<Section> {
    private Context mContext;
    private int mResource;

    public SectionAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Section> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    // Inflating layout
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

       convertView = layoutInflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.section_image);
        imageView.setImageResource(getItem(position).getImage());

        TextView textName =  convertView.findViewById(R.id.section_name);
        textName.setText(getItem(position).getName());


       return convertView;


    }
}