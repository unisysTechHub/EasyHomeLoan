package com.example.easyhomeloan.webservice;

import androidx.lifecycle.MutableLiveData;

public interface RequestExecutor {

   <T> void executeRequest(Class<T> responseClass, Resource resource, MutableLiveData result);
}
