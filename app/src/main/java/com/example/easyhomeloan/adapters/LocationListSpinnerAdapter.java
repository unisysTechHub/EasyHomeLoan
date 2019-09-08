package com.example.easyhomeloan.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationListSpinnerAdapter implements SpinnerAdapter {
    Context  context;
    ArrayList<String> locationList;
    public LocationListSpinnerAdapter(Context context, ArrayList<String> locationList)
    {
        this.context=context;
        this.locationList=locationList;


    }
    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        view = new TextView(context);
        ((TextView) view).setText(locationList.get(i));

        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int i) {
        return locationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
        TextView imageName=view.findViewById(android.R.id.text1);
        imageName.setText(locationList.get(i));

        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return android.R.id.text1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
