package com.superfeed.android;

import android.app.Application;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by nicolas on 3/4/17.
 */

public class MyApplication extends MultiDexApplication {

    public RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        //start service for
        //PollService.isServiceAlarmOn(getActivity()
        Log.e("hello","from application");
        this.requestQueue = Volley.newRequestQueue(this);

        if(!PullingService.isServiceAlarmOn(this))
            PullingService.setServiceAlarm(this,true);

    }
}
