package com.yehudit.powerwomen;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BroadcastAlarm extends BroadcastReceiver {
    private int notificationID;
    private NotificationManager notificationManager;
    private DBHelper DB;
    private String todayDate;
//    public void stopNotificationFunc(){
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//// Create the same Intent
//        Intent alarmIntent = new Intent(SettingsActivity, BroadcastAlarm.class);
//// Create PendingIntent with the same Intent & ALARM_ID & with different flag: FLAG_NO_CREATE
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 0,
//                alarmIntent, PendingIntent.FLAG_NO_CREATE);
//// If alarmPendingIntent is not null – alarm is SET ON – so we can cancel it
//        if(alarmPendingIntent != null)
//        {
//            alarmManager.cancel(alarmPendingIntent);
//            alarmPendingIntent.cancel();
//        }
//        Toast.makeText(this, "Alerts have been muted", Toast.LENGTH_SHORT).show();}

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("broadcastAlarm", "onReceive: ");
        Toast.makeText(context, "select time to reminder", Toast.LENGTH_LONG).show();
        notificationID = 1;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // 2. Create Notification-Channel. ONLY for Android 8.0 (OREO API level 26) and higher.
        NotificationChannel notificationChannel = new NotificationChannel(
                "HIGH_CHANNEL_ID", // Constant for Channel ID
                "HIGH_CHANNEL_NAME", // Constant for Channel NAME
                NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);
        Notification notification;
        //if(ifCycleWeekFinish()) {
            notification = new NotificationCompat.Builder(context, "HIGH_CHANNEL_ID")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("This is notify title! (" + notificationID+")")
                    .setContentText("You should join women power to start a new weekly exercise cycle")
                    .build();

       // }
      //  else {
            // Toast.makeText(MainActivity.this, "setAlremmm", Toast.LENGTH_SHORT).show();
//            notification = new NotificationCompat.Builder(context, "HIGH_CHANNEL_ID")
//                    .setSmallIcon(R.drawable.ic_notification)
//                    .setContentTitle("This is notify title! (" + notificationID + ")")
//                    .setContentText("reminder of:"+todayDate)
//                    .build();
      //  }

        // שליחת הנוטיפיקציה שתוקפץ למשתמש מסטוטס בר
        notificationManager.notify(notificationID, notification);
        notificationID++;
    }
    private String getDateToday(){
        String date="";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-dd-MM-yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 0);
        date=sdf.format(calendar.getTime());
        todayDate=date;
        return date;
    }
    private boolean ifCycleWeekFinish(){
        Log.d("ifCycleWeekFinish: ", "ifCycleWeekFinish: ");
        if(DB.getValue("APP_ON").equals("YES")){
            String date = getDateToday();
            Log.d(" checkinsertdata", date);
            int i;
            String dateInDB = "";
            for (i = 1; i < 8; i++) {
                dateInDB = DB.getValue("DAY" + i);
                Log.d(" dateInDB", dateInDB);
                if (dateInDB.equals(date)) {
                    Log.d(" ifdateInDB", dateInDB);
                    boolean checkinsertdata = DB.updateuserdata("DAY", "" + i);
                    Log.d(" checkinsertdatanum", "" + i);
                    return false;
                }
            }
        }
        return true;
    }
    private void setupNotification()
    {
//        notificationID = 1;
//         notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        //notificationManager =  (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        // 2. Create Notification-Channel. ONLY for Android 8.0 (OREO API level 26) and higher.
//        NotificationChannel notificationChannel = new NotificationChannel(
//                "HIGH_CHANNEL_ID", // Constant for Channel ID
//                "HIGH_CHANNEL_NAME", // Constant for Channel NAME
//                NotificationManager.IMPORTANCE_HIGH);
//        notificationManager.createNotificationChannel(notificationChannel);
//
//
//
//
//
//               // Toast.makeText(MainActivity.this, "setAlremmm", Toast.LENGTH_SHORT).show();
//                Notification notification = new NotificationCompat.Builder(this, "HIGH_CHANNEL_ID")
//                        .setSmallIcon(R.drawable.ic_notification)
//                        .setContentTitle("This is notify title! (" + notificationID+")")
//                        .setContentText("This is your notification text.")
//                        .build();
//
//                // שליחת הנוטיפיקציה שתוקפץ למשתמש מסטוטס בר
//                notificationManager.notify(notificationID, notification);
//                notificationID++;


    }

}
