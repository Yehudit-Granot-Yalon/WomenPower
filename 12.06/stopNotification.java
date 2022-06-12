//package com.yehudit.powerwomen;
//
//
//import static androidx.core.content.ContextCompat.getSystemService;
//
//import android.annotation.SuppressLint;
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.widget.Toast;
//
//public class stopNotification {
//    public stopNotification(Context context){
//        Log.d("stopNotification: ", "stopNotification: ");
//
//
//
//    }
//    public void stopNotificationFunc(){
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//// Create the same Intent
//        Intent alarmIntent = new Intent(stopNotification.this, BroadcastAlarm.class);
//// Create PendingIntent with the same Intent & ALARM_ID & with different flag: FLAG_NO_CREATE
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 0,
//                alarmIntent, PendingIntent.FLAG_NO_CREATE);
//// If alarmPendingIntent is not null – alarm is SET ON – so we can cancel it
//        if(alarmPendingIntent != null)
//        {
//            alarmManager.cancel(alarmPendingIntent);
//            alarmPendingIntent.cancel();
//        }
//       // Toast.makeText(this, "Alerts have been muted", Toast.LENGTH_SHORT).show();
//
//    }
//
//}
