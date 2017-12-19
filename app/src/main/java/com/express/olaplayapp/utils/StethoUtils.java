package com.express.olaplayapp.utils;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.okhttp.Interceptor;

import java.util.List;

/**
 * Created by root on 1/12/17.
 */

public class StethoUtils {
    public static void install(Context context) {
        Stetho.initialize(Stetho.newInitializerBuilder(context)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                .build());
    }

    public static void addNetworkInterceptors(List<Interceptor> interceptors) {
       // interceptors.add(new StethoInterceptor());
    }
}
