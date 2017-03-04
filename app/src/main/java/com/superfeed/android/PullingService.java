package com.superfeed.android;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class PullingService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.superfeed.android.action.FOO";
    private static final String ACTION_BAZ = "com.superfeed.android.action.BAZ";

    public PullingService() {
        super("PullingService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("hello","can you hear me");
    }

    public static final int POLL_INTERVAL = 7*1000;


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getStringExtra(ACTION_FOO);
            if ("123".equals(action)) {
               //call 3amo baydoun
                Log.e("hello","can you hear me i am inside intent");
                RequestQueue queue = ((MyApplication)getApplication()).requestQueue;
                String url ="http://superpotato.herokuapp.com/api/get?key=push";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("hello","response"+response);
                                // Display the first 500 characters of the response string.
                                if(response.equals("true")) {
                                    //create notification
                                    createNotification();
                                }

                            }
                        }, null);
// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        }
    }

    private void createNotification(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.sym_def_app_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, LoginActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(LoginActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());

    }



    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent i = new Intent(context, PullingService.class);
        i.putExtra(ACTION_FOO,"123");
        PendingIntent pi = PendingIntent.getService(
                context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) {
            Log.e("hello","set Alarm manager");
            alarmManager.setRepeating(AlarmManager.RTC,
                    System.currentTimeMillis(), POLL_INTERVAL, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent i = new Intent(context, PullingService.class);
        PendingIntent pi = PendingIntent.getService(
                context, 0, i, PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }
}
