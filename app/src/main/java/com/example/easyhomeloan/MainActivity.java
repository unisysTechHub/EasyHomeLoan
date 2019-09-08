package com.example.easyhomeloan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.easyhomeloan.auth.UserAuthInfo;
import com.example.easyhomeloan.di.EasyLoanApp;
import com.example.easyhomeloan.fragments.PropertyList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    public static  String TAG="@DBS";
    @Inject
    UserAuthInfo userAuthInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           EasyLoanApp.getObjectGraph(this.getApplicationContext()).inject(this);
       Log.d(TAG, userAuthInfo.getCust_id());

       getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, PropertyList.newInstance()).commit();
    }
}
