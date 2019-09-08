package com.example.easyhomeloan.webservice;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.easyhomeloan.fragments.PropertyList;
import com.example.easyhomeloan.model.CustomerDetails;
import com.example.easyhomeloan.model.CustomerLogin;
import com.example.easyhomeloan.model.PropertyListModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

public class WebService
{
    public static String TAG = "@DBS";
    AppRequestExecutor appRequestExecutor;

    @Inject
    WebService (AppRequestExecutor appRequestExecutor)
    {
        this.appRequestExecutor=appRequestExecutor;

    }

     public <T> void userLogin(CustomerLogin customerLogin, MutableLiveData<T> customerDetails )
     {

         Log.d(TAG,"webservice");
         String url = "http://10.10.1.147:8080/authenticateUser";
         HashMap<String,String> headers= new HashMap<>();
         headers.put("Content-Type", "application/json");
         JSONObject customerLoginJSOn=null;
         String customerLoginString= new Gson().toJson(customerLogin);
         try {
             customerLoginJSOn= new JSONObject(customerLoginString);
         } catch (JSONException e) {
             e.printStackTrace();
         }

         Resource resource= new Resource.Builder()
                 .setRequestMethod(Resource.Method.POST).setUrl(url)
                 .setHeaders(headers)
                 .setRequestBody(customerLoginJSOn).build();

         appRequestExecutor.executeRequest(CustomerDetails.class,resource,customerDetails);

     }
      public <T> void getPropertyList(CustomerDetails customerDetails, MutableLiveData<T> propertyListModel)
      {
          Log.d(TAG,"webservice");
          String url = "http://10.10.1.147:8080/properties";
          HashMap<String,String> headers= new HashMap<>();
          headers.put("Content-Type", "application/json");
          JSONObject customerLoginJSOn=null;
          //String customerLoginString= new Gson().toJson(customerLogin);
//          try {
//              customerLoginJSOn= new JSONObject(customerLoginString);
//          } catch (JSONException e) {
//              e.printStackTrace();
//          }

          Resource resource= new Resource.Builder()
                  .setRequestMethod(Resource.Method.GET).setUrl(url)
                  .setHeaders(headers).build();

          appRequestExecutor.executeRequest(PropertyListModel.class,resource,propertyListModel);



      }


}
