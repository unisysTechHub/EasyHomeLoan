package com.example.easyhomeloan.di;

import android.app.Application;
import android.content.Context;

import com.example.easyhomeloan.auth.UserAuthInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppBaseModule {
    Application context;
    public AppBaseModule(){}
    AppBaseModule(Application application)
    { this.context=application;
    }

    @Provides
    @Singleton
    public UserAuthInfo userAuthInfoProvider()
    {
        return UserAuthInfo.getInstance();
    }

    @Provides
    public Application applicationprovider()
    {
        return context;
    }






}
