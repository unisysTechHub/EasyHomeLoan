package com.example.easyhomeloan.di;

import com.example.easyhomeloan.webservice.AppRequestExecutor;
import com.example.easyhomeloan.webservice.WebService;

public interface AppDependencyClasses {

void inject(AppRequestExecutor appRequestExecutor);
void inject(WebService webService);

}
