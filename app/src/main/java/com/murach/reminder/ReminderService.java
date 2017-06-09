package com.murach.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by dtracy on 6/8/2017.
 */
public class ReminderService extends Service {
    private Timer timer;

    private NotificationManager notificationManager;
    private  final int NOTIFICATION_ID =1;

    @Override
    public void onCreate(){
        notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("Reminder","started(start commander)");
                startTimer();
        //SERVICE STICKY SO THAT IT HAPPENS NO MATTER WHICH APP IS BEING USED
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent){
        Log.d("Reminder", "service bound");
        return null;
    }

    @Override
    public void onDestroy(){
        Log.d("reminder","stopped(destroyer)");
        stopTimer();
    }

    public void startTimer(){
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Log.d("reminder","Look into the distance. It’s good for your eyes");
                Intent notificationIntent = new Intent(getApplicationContext(),ReminderActivity.class);

                // create the pending intent
                int flags = PendingIntent.FLAG_UPDATE_CURRENT;
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, flags);

                // create the variables for the notification
                int icon = R.drawable.ic_launcher;
                CharSequence tickerText = getText(R.string.app_name);
                CharSequence contentTitle = getText(R.string.app_name);
                CharSequence contentText = getText(R.string.app_description);

                // create the notification and set its data
                Notification notification =
                        new Notification.Builder(getApplicationContext())
                                .setSmallIcon(icon)
                                .setTicker(tickerText)
                                .setContentTitle(contentTitle)
                                .setContentText(contentText)
                                .setContentIntent(pendingIntent)
                                .build();
                // display the notification
                notificationManager.notify(NOTIFICATION_ID, notification);
            }
        };
        //create & start timer
        timer= new Timer(true);
        int delay=5000;
        //frequency of reminder is 6 seconds
        int interval =1000*6;
        timer.schedule(task, delay, interval);
    }
    //stopper
    private void stopTimer(){
        if(timer!= null){
                timer.cancel();
                Log.d("reminder","cancelled");
        }
    }

}
