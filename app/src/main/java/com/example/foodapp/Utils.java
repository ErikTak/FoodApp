package com.example.foodapp;

// Some Utility functions

import android.net.Uri;

import androidx.annotation.NonNull;

public class Utils {

    @NonNull
    public static Uri buildUri(String base, String paramName, String paramValue, String requestParamName, String requestParamValue, String numberOfItemsName, String numberOfItemsValue){
        Uri uri = Uri.parse(base);
        // create a URI Builder and add the parameter
        Uri.Builder uriBuilder = uri.buildUpon();
        uriBuilder.appendQueryParameter(paramName, paramValue).appendQueryParameter(requestParamName, requestParamValue).appendQueryParameter(numberOfItemsName, numberOfItemsValue);
        return uriBuilder.build();
    }

}
