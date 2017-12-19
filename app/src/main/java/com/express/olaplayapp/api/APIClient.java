package com.express.olaplayapp.api;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by root on 30/11/17.
 */

public class APIClient {

    public static Retrofit mInstance = null;
    public static final String BASE_URL = "http://starlord.hackerearth.com/";

    private APIClient() {

    }

    public static Retrofit getInstance() {
        if(mInstance == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.networkInterceptors().add(new StethoInterceptor());

            mInstance = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return mInstance;
    }
}
