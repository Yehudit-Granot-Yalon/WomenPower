package com.yehuditg.womenpower;

import static android.content.Context.ALARM_SERVICE;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HelpFunctions {
    private Context myContext;
    public DBHelper DB;
    public HelpFunctions(Context context){
        this.myContext = context;
        DB = new DBHelper(context);
    }
    private String getDateToday(){//Returns the date of that day in a suitable format
        String date="";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-dd-MM-yyyy");
        Calendar calendar = new GregorianCalendar();
        date=sdf.format(calendar.getTime());
        return date;
       // return "Thursday-24-06-2022";

    }
    public boolean checkAppOn(){//A function that checks if a week of exercise has begun
        if(DB.getValue("APP_ON").equals("YES"))
            return true;
        else
            return false;
    }
    public boolean ifCycleWeekFinish(){//A function that checks if a week of exercise has already ended
        if(checkAppOn()) {
            String date = getDateToday();
            int i;
            String dateInDB = "";
            for (i = 1; i < 8; i++) {
                dateInDB = DB.getValue("DAY"+ i);
                Log.d(" dateInDB", dateInDB);
                if (dateInDB.equals(date)) {
                    DB.updateuserdata("DAY", "" + i);//Updates the day number in the weekly cycle
                    return false;
                }
            }
        }
        return true;
    }
    public void startNotification(String time,int addMinute){//Arranges the reminders and gets the date and time and time that needs to be changed in minutes for half an hour before performing the exercise
        stopNotification ();
        AlarmManager alarmManager = (AlarmManager) myContext.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(myContext, BroadcastAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(myContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long repeatIntervalMS =60*24*60*1000;//Alerts every 24 hours
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        int parameters[]= getTimeAndDate(getDateToday(),time);
        c2.set(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]-addMinute,0);//Notification half an hour before exercise
        if(compareTime(c1,c2)==-1){//The alert time for today has passed
            c2.set(parameters[0], parameters[1], parameters[2]+1, parameters[3], parameters[4]-30,0);//The alert will start tomorrow
            Log.d("parameters", "tomorrow alert");
        }
        Log.d("parameters", "today alert");
        long timeLong=   c2.getTimeInMillis();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeLong , repeatIntervalMS, pendingIntent);

    }
    public void stopNotification () {
        AlarmManager alarmManager = (AlarmManager) myContext.getSystemService(ALARM_SERVICE);
// Create the same Intent
        Intent alarmIntent= new Intent(myContext, BroadcastAlarm.class);
        // Create PendingIntent with the same Intent & ALARM_ID & with different flag: FLAG_NO_CREATE
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(myContext, 0,
                alarmIntent, PendingIntent.FLAG_NO_CREATE);
// If alarmPendingIntent is not null – alarm is SET ON – so we can cancel it
        if(alarmPendingIntent != null)
        {
            Log.d("topNotification", "PendingIntent is not null");
            alarmManager.cancel(alarmPendingIntent);
            alarmPendingIntent.cancel();
        }
    }

    public int[] getTimeAndDate(String date,String time){//A function that sorts values to put them in a specific time instance
     int parameters[] = new int[5];
        String[] timeWords;
        timeWords=time.split(":");
        parameters[3]=Integer.parseInt(timeWords[0]);
        parameters[4]=Integer.parseInt(timeWords[1]);
        String [] dateWords;
        dateWords=date.split("-");
        parameters[2]=Integer.parseInt(dateWords[1]);
        parameters[1]=Integer.parseInt(dateWords[2])-1;//month is less 1 because it order 0-11
        parameters[0]=Integer.parseInt(dateWords[3]);
        return parameters;
    }
    public int compareTime(Calendar c1,Calendar c2) {//Compares between 2 times
        c1.getTimeInMillis();
        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();
        if (time1 < time2) {//this time not to notify
            Log.d("compareDate: ", "time 1 small ");
            return 1;
        }
        if (time2 < time1) {
            Log.d("compareDate: ", "time 1 big ");
            return -1;
       }
            if (time2 == time1) {
                Log.d("compareDate: ", "time 1 =time 2 ");
                return 0;
            }
            return 0;
        }
    public static byte[] getBytes(Bitmap bitmap) {//Moves from Bitmap to Bates
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }




    }


