package com.example.easyhomeloan.webservice;

import org.json.JSONObject;

import java.util.Map;

public class Resource {
    public static String TAG ="@WEBSERVICE";
    int method;
    Map<String,String> headers;
    JSONObject requestBody;
    String url;

    public interface Method
    {
        int GET =0;
        int POST =1;
    }

     public static class Builder {

            int method;
            Map<String,String> headers;
            JSONObject requestBody;
            String url;
       public   Builder setRequestMethod(int requestMethod)
         {

                     this.method=requestMethod;


             return this;
         }
         public Builder setHeaders(Map<String,String> headers)
         {
             this.headers=headers;

             return this;
         }
         public Builder setResposeClass(Class<?> resposeClass)
         {

             return this;
         }

         public Builder setRequestBody(JSONObject requestBody )
         {  this.requestBody=requestBody;
             return this;
         }

         public Builder setUrl(String url)
         {
             this.url=url;
             return this;
         }
         public Resource build()
         {
             Resource resource = new Resource();
             if(this.headers!=null)
             resource.headers= this.headers;

             resource.method=this.method;

             if(this.requestBody !=null)
             resource.requestBody=this.requestBody;

             resource.url=this.url;

             return resource;
         }
     }


}
