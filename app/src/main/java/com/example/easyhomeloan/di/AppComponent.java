package com.example.easyhomeloan.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

@Component(modules = AppBaseModule.class)
@Singleton
public interface AppComponent extends AppFragments, AppDependencyClasses  {

}
