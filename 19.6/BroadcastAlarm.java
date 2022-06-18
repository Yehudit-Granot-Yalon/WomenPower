package com.yehuditg.womenpower;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
public class BroadcastAlarm extends BroadcastReceiver {//A broadcaster who sends the notifications
    private HelpFunctions helpFunctions;
    private int notificationID;
    private NotificationManager notificationManager;
    private String textNotification;
    @Override
    public void onReceive(Context context, Intent intent) {
        helpFunctions=new HelpFunctions(context);
        notificationID = 1;
        String timeToExercise=helpFunctions.DB.getValue("TIME_REMINDER");
        textNotification="reminder! exercise in: "+timeToExercise;//The text of the post depends on whether it is within the week of daily exercise
        boolean cycleWeekFinish= helpFunctions.ifCycleWeekFinish();
        if(cycleWeekFinish==true){
            textNotification="start a new weekly exercise cycle";//The text of the post depends on whether it is not within the week of daily exercise
        }
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //  Create Notification-Channel. ONLY for Android 8.0 (OREO API level 26) and higher.
        NotificationChannel notificationChannel = new NotificationChannel(
                "HIGH_CHANNEL_ID", // Constant for Channel ID
                "HIGH_CHANNEL_NAME", // Constant for Channel NAME
                NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);
        Notification notification;
            notification = new NotificationCompat.Builder(context, "HIGH_CHANNEL_ID")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("women power!")
                    .setContentText(textNotification)
                    .build();
            notificationManager.notify(notificationID, notification);//Send the notification that will pop up to the user from Status Bar
            notificationID++;
    }




}
