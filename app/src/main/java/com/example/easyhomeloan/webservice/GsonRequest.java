package com.example.easyhomeloan.webservice;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
    public static String TAG ="@WEBSERVICE";
     Gson gson = new Gson();
     Class<?> clazz;
     Map<String,String> headers;
     JSONObject requestBody;
     Response.Listener<T> listener;

    public GsonRequest(Class<?> clazz,Resource resource ,Response.Listener<T> listener ,@Nullable Response.ErrorListener errorListener) {
        super(resource.method, resource.url, errorListener);
        this.clazz=clazz;
        this.requestBody=resource.requestBody;
        this.headers=resource.headers;
        this.listener=listener;
        Log.d(TAG,"gson request constructor" + resource.url);

    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return requestBody.toString().getBytes();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        Log.d(TAG,"networkresponse");
        try {

            Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
            if (cacheEntry == null) {
                cacheEntry = new Cache.Entry();
            }
            final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
            final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
            long now = System.currentTimeMillis();
            final long softExpire = now + cacheHitButRefreshed;
            final long ttl = now + cacheExpired;
            cacheEntry.data = response.data;
            cacheEntry.softTtl = softExpire;
            cacheEntry.ttl = ttl;
            String headerValue;
            headerValue = response.headers.get("Date");
            if (headerValue != null) {
                cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
            }
            headerValue = response.headers.get("Last-Modified");
            if (headerValue != null) {
                cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
            }
            cacheEntry.responseHeaders = response.headers;
            String json = new String(
                    response.data);


            Object jsonObject =new JSONTokener(json).nextValue();

            if(jsonObject instanceof JSONArray)
            {
                Log.d(TAG,"JSON Array");
                  JSONObject tempJsonObject  =new JSONObject().put("propertyList",jsonObject);
                  json=tempJsonObject.toString();
                 //json=(new JSONObject(((JSONArray) jsonObject).toString())).toString();
                Log.d(TAG,"JSON Array");
                Log.d(TAG,json);
            }

            Log.d(TAG,json);
            return  Response.success(
                    (T)gson.fromJson(json, clazz),
                    cacheEntry);
        }
//        catch (UnsupportedEncodingException e) {
//            return Response.error(new ParseError(e));
//        }
        catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }



    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }



}
