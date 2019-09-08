package com.example.easyhomeloan.di;

import android.app.Application;
import android.content.Context;

public class EasyLoanApp extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context=  getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public AppComponent getObjectGraph()
    {
       AppComponent appComponent =DaggerAppComponent.builder().appBaseModule(new AppBaseModule(this)).build();

        return appComponent;
    }


    public static AppComponent getObjectGraph(Context context)
    {
        return     ( (EasyLoanApp) context).getObjectGraph();

    }



}
