package com.example.easyhomeloan.webservice;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppNetworkRequester {
    RequestQueue requestQueue;
    ImageLoader imageLoader;
    private static AppNetworkRequester appNetworkRequester;
    private Context mContext;

    private AppNetworkRequester(Context context)
    {
        this.mContext=context;
        this.requestQueue=getRequestQueue();
        imageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

    }

        public static AppNetworkRequester  getInstance(Context context)
        {
            if(appNetworkRequester == null)
                appNetworkRequester= new AppNetworkRequester(context);
            return appNetworkRequester;

        }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }



    public RequestQueue getRequestQueue() {
        if(requestQueue == null)
        requestQueue= Volley.newRequestQueue(mContext);
        return requestQueue;
    }
}
