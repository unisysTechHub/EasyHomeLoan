package com.example.easyhomeloan.network.response;

import com.google.gson.annotations.SerializedName;

public class CustomerDetails {

    @SerializedName("customerName")
    String Name;

    @SerializedName("customerSex")
    String gender;

    @SerializedName("customerAdd1")
    String address1;

    @SerializedName("customerAdd2")
    String address2;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}

