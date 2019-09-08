package com.example.easyhomeloan.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Person;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyhomeloan.R;
import com.example.easyhomeloan.adapters.LocationListSpinnerAdapter;
import com.example.easyhomeloan.adapters.PropertyListAdapter;
import com.example.easyhomeloan.databinding.PropertyListFragmentBinding;
import com.example.easyhomeloan.datarepository.EasyLoanDataRepository;
import com.example.easyhomeloan.di.EasyLoanApp;
import com.example.easyhomeloan.fragments.viewModel.PropertyListViewModel;
import com.example.easyhomeloan.model.Property;
import com.example.easyhomeloan.network.response.CustomerDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class PropertyList extends Fragment {

    public static String TAG="@DBS";
    ArrayList<Property> propertyList= new ArrayList<>();
    @Inject
    EasyLoanDataRepository easyLoanDataRepository;
    PropertyListAdapter propertyListAdapter;
    LocationListSpinnerAdapter locationListSpinnerAdapter;
    ArrayList<String> locoations= new ArrayList<>();
    PropertyListFragmentBinding propertyListFragmentBinding;


    private PropertyListViewModel mViewModel;

    public static PropertyList newInstance() {
        return new PropertyList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

         propertyListFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.property_list_fragment,container,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        propertyListFragmentBinding.propertyList.setLayoutManager(linearLayoutManager);
        propertyListAdapter=new PropertyListAdapter(this.getContext(),propertyList);
        propertyListFragmentBinding.propertyList.setAdapter(propertyListAdapter);

        locationListSpinnerAdapter = new LocationListSpinnerAdapter(this.getContext(),locoations);
        propertyListFragmentBinding.locationList.setAdapter(locationListSpinnerAdapter);

        return propertyListFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EasyLoanApp.getObjectGraph(this.getContext().getApplicationContext()).inject(this);
        mViewModel = ViewModelProviders.of(this).get(PropertyListViewModel.class);
        mViewModel.setEasyLoanDataRepository(easyLoanDataRepository);
        mViewModel.getCustomerDetailsObserver().observe(this, new Observer<CustomerDetails>() {
            @Override
            public void onChanged(CustomerDetails customerDetails) {
                Log.d(TAG,customerDetails.getName());
            }
        });

        mViewModel.getPropertyList().observe(this, propertyListModel -> {

            propertyList.clear();
            propertyList.addAll(propertyListModel.getPropertyList());
            propertyListAdapter.notifyDataSetChanged();


            Log.d(TAG,"property List observer "+propertyList.size());


//            locoations.clear();
//            locoations.add("Select Location");
//            locoations.addAll(buildLocationStream()) ;

        });
        //mViewModel.userLogin();
        // TODO: Use the ViewModel
        mViewModel.propertiesListService();
    }

    ArrayList<String> buildLocationStream()
    {
        Set<String> distinctLocations = new HashSet<>();

        ArrayList<Property> propertiesCopy = new ArrayList<>(propertyList);
        propertiesCopy.removeIf(p -> !distinctLocations.add(p.getPropertyAdd1()));

        distinctLocations.stream().forEach( location -> Log.d(TAG,location));

        return new ArrayList<>(distinctLocations);
    }
}
