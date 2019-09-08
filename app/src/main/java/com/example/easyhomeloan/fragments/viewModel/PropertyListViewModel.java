package com.example.easyhomeloan.fragments.viewModel;

import android.app.Application;
import android.app.Person;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyhomeloan.datarepository.EasyLoanDataRepository;
import com.example.easyhomeloan.model.CustomerLogin;
import com.example.easyhomeloan.model.Property;
import com.example.easyhomeloan.model.PropertyListModel;
import com.example.easyhomeloan.network.response.CustomerDetails;

import java.util.ArrayList;

public class PropertyListViewModel extends AndroidViewModel {
    EasyLoanDataRepository easyLoanDataRepository;
    MutableLiveData<PropertyListModel>  propertyList=new MutableLiveData<>();
    MutableLiveData<CustomerDetails> customerDetailsObserver = new MutableLiveData<>();
    public PropertyListViewModel(@NonNull Application application) {
        super(application);
    }

   public void userLogin()
    {
        CustomerLogin customerLogin= new CustomerLogin();
        customerLogin.setUserId("111");
        customerLogin.setPassword("DBS");

        easyLoanDataRepository.userLogIn(customerLogin,customerDetailsObserver);

    }

    public MutableLiveData<PropertyListModel> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(MutableLiveData<PropertyListModel> propertyList) {
        this.propertyList = propertyList;
    }

    public void propertiesListService()
    {
        easyLoanDataRepository.getPropertyList(null,propertyList);
    }



    public void setEasyLoanDataRepository(EasyLoanDataRepository easyLoanDataRepository) {
        this.easyLoanDataRepository = easyLoanDataRepository;
    }

    public MutableLiveData<CustomerDetails> getCustomerDetailsObserver() {
        return customerDetailsObserver;
    }

    // TODO: Implement the ViewModel
}
