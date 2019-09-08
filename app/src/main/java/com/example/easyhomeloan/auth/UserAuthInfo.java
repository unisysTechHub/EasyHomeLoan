package com.example.easyhomeloan.auth;

public class UserAuthInfo {
    private static UserAuthInfo userAuthInfoInstance;
    private String cust_id = "CustID";
    private  UserAuthInfo() {}

    public static UserAuthInfo getInstance() {
         if ( userAuthInfoInstance == null)
         {
             userAuthInfoInstance=  new UserAuthInfo();
         }
        return userAuthInfoInstance;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }
}
