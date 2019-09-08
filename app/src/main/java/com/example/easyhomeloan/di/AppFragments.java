package com.example.easyhomeloan.di;

import com.example.easyhomeloan.MainActivity;
import com.example.easyhomeloan.auth.UserAuthInfo;
import com.example.easyhomeloan.fragments.PropertyList;

public interface AppFragments {
    void inject(MainActivity mainActivity);
    void inject(EasyLoanApp easyLoanApp);
    void inject(PropertyList propertyList);
    UserAuthInfo userAuthInfoProvider();

}
