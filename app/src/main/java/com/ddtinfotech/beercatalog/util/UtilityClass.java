package com.ddtinfotech.beercatalog.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilityClass {
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    //    return a random number between 2 to 100
    public int getRandomNum() {
        int max = 100;
        int min = 2;
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }

}