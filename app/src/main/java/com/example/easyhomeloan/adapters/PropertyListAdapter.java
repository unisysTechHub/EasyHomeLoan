package com.example.easyhomeloan.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyhomeloan.R;
import com.example.easyhomeloan.databinding.PropertyViewBinding;
import com.example.easyhomeloan.model.Property;

import java.util.ArrayList;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.PropertyListAdaoterViewHolder> {

    ArrayList<Property> propetyList;
    LayoutInflater inflater;

    public PropertyListAdapter(Context context, ArrayList<Property> properties)
    {
        Log.d("@DBS", "ListAdapterConstructor");
    this.propetyList=properties;
    inflater= LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public PropertyListAdaoterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PropertyViewBinding propertyViewBinding = DataBindingUtil.inflate(inflater, R.layout.property_view,parent,false);
        return new PropertyListAdaoterViewHolder(propertyViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyListAdaoterViewHolder holder, int position) {
        Log.d("@DBS", "onBindViewholder "+ position );
                holder.propertyViewBinding.setProperty(propetyList.get(position));
                holder.propertyViewBinding.executePendingBindings();
    }



    @Override
    public int getItemCount() {
        return propetyList.size();
    }
    class PropertyListAdaoterViewHolder extends RecyclerView.ViewHolder {
        PropertyViewBinding propertyViewBinding;
        public PropertyListAdaoterViewHolder(@NonNull PropertyViewBinding propertyViewBinding) {
            super(propertyViewBinding.getRoot());
            this.propertyViewBinding=propertyViewBinding;
        }
    }
}
