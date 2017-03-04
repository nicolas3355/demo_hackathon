package com.superfeed.android;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jkerry on 3/4/17.
 */

public class LocalPref {
    private static final String SHARED_PREFERENCES_NAME = "potato";

    public static void setUserLoggedIn(Context context, boolean is) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean("user_logged_in", is).apply();
    }

    public static boolean getUserLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean("user_logged_in", false);
    }
}
