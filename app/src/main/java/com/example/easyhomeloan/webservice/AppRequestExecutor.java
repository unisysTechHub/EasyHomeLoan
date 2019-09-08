package com.example.easyhomeloan.webservice;
import android.app.Application;
import android.content.Context;
import android.util.Log;


import androidx.lifecycle.MutableLiveData;


import javax.inject.Inject;

public class AppRequestExecutor implements RequestExecutor {
    Context context;
    public static String TAG ="@WEBSERVICE";

    @Inject
    public AppRequestExecutor(Application application)
    {
        this.context=application;
    }

    @Override
    public <T> void executeRequest(Class<T> responseClass, Resource resource, MutableLiveData mResult ) {
        Log.d(TAG,"ExecuteRequest");

        GsonRequest<T> gsonRequest = new GsonRequest<>(responseClass, resource,
                response -> {
                    mResult.setValue(response);
                    Log.d(TAG,"On Response" + response);


                }, error -> {

            Log.d(TAG,"Volley Error");
        }

        );


        AppNetworkRequester.getInstance(context).getRequestQueue().add(gsonRequest);
    }


}

