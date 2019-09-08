package com.example.easyhomeloan.datarepository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyhomeloan.model.CustomerDetails;
import com.example.easyhomeloan.model.CustomerLogin;
import com.example.easyhomeloan.model.PropertyListModel;
import com.example.easyhomeloan.webservice.Resource;
import com.example.easyhomeloan.webservice.WebService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

public class EasyLoanDataRepository {

    public static  String TAG="@DBS";
    WebService webService;

    @Inject
    EasyLoanDataRepository(WebService webService)
    {
            this.webService=webService;
    }

  public  <T>  void userLogIn(CustomerLogin customerLogin, MutableLiveData<T> customerDetails )
    {

            webService.userLogin(customerLogin, customerDetails );
    }


    public <T> void getPropertyList(CustomerDetails customerDetails, MutableLiveData<T> propertyList)
    {

            webService.getPropertyList(customerDetails,propertyList);

    }
}
