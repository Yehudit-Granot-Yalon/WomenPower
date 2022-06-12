package com.yehudit.powerwomen;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
//code clock
//https://github.com/codeWithCal/TimePickerAndroidStudio/blob/master/app/src/main/java/codewithcal/au/timerpickertutorial/MainActivity.java
//time notification
//https://developer.android.com/training/scheduling/alarms#type
public class Time extends AppCompatActivity
{
    DBHelper DB;
    //public DBHelperPicture DBPicture;
    private Button timeReminderButton;
    private Button chooseTimeButton;
    private ImageView img10;
    private ImageView img20;
    private int numberToString;
    int hour, minute;
    private Long startMillisForNotification;
    private Boolean checkinsertdata;
    private String selectTimeToReminder;
    private String selectTimeToVideo;

    //for notification

    private String timeToNotification;
    //private String dateToNotification;

    public void setWeeklyCycle(){
        String thisDayInWeek="";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-dd-MM-yyyy");
        String day;
        //https://stackoverflow.com/questions/33199084/how-to-get-next-seven-days-in-android
        for (int i = 0; i < 7; i++) {
            day="DAY";
            day+=String.valueOf(i+1);
            Log.d("dayfor", day);
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            checkinsertdata = DB.updateuserdata(day, sdf.format(calendar.getTime()));
        }


        //An event in the calendar can only appear after the days have been set
    }
    //define notification
    private void setAlarmWas() {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        String timeTonotify="21:33";
        String dateTonotify="31-5-2022";
        Intent intent = new Intent(getApplicationContext(), AlarmBrodcast.class);
        intent.putExtra("event", "reminder myiiiiiii");
        intent.putExtra("time", timeTonotify);
        intent.putExtra("date",dateTonotify);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = dateTonotify+" "+timeTonotify;
        // String dateandtime = "24-5-2022"+ " " + timeTonotify;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {

            // Toast.makeText(AddContactActivity.this, "setA"+dateandtime, Toast.LENGTH_SHORT).show();
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            // long a=date1.getTime();
            //  Toast.makeText(this,"aa  "+a , Toast.LENGTH_LONG).show();
            Toast.makeText(this, "setAlremmm", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            Toast.makeText(this, "catch noty", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }



    }
    private void setAlarm()  {
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(),BroadcastAlarm.class);
//        intent.putExtra("event", text);
//        intent.putExtra("time", timeTonotify);
//        intent.putExtra("date",dateTonotify);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //  PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        // PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, ALARM_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //   String dateandtime = dateTonotify+" "+timeTonotify;
        // String dateandtime = "24-5-2022"+ " " + timeTonotify;
        // DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        // Set the alarm to start at approximately 2:00 p.m.


//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//     //   startMillis
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//       // calendar.set(Calendar.MINUTE, minute);
//// With setInexactRepeating(), you have to use one of the AlarmManager interval
//// constants--in this case, AlarmManager.INTERVAL_DAY.
//        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, alarmIntent);

        long triggerTimeMS = Calendar.HOUR_OF_DAY;
        // long triggerTimeMS = System.currentTimeMillis();
        Log.d("setAlarm1 ", ""+triggerTimeMS);

        //  triggerTimeMS=  startMillis;
        Log.d("setAlarm2 ", ""+startMillisForNotification);
        long repeatIntervalMS = 2*60*1000;

        // Toast.makeText(AddContactActivity.this, "setA"+dateandtime, Toast.LENGTH_SHORT).show();
        //     Date date1 = formatter.parse(dateandtime);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, triggerTimeMS, repeatIntervalMS, alarmIntent);
        // am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
        // long a=date1.getTime();
        //  Toast.makeText(this,"aa  "+a , Toast.LENGTH_LONG).show();
        //   Toast.makeText(myReceiver.this, "setAlremmm", Toast.LENGTH_SHORT).show();

        //finish();

    }
    private void setNotification(int i) {
        String dateToNotification;

        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, i);
        dateToNotification=sdf.format(calendar.getTime());
        timeToNotification=DB.getValue(getString(R.string.TIME_REMINDER));
        Log.d("timeToNotification", "timeToNotification ");
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmBrodcast.class);
        intent.putExtra("event", "Reminder enter to the app");
        intent.putExtra("time", timeToNotification);
        intent.putExtra("date", dateToNotification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateToParse =  dateToNotification+" "+timeToNotification;
        Toast.makeText(this, "date"+dateToParse, Toast.LENGTH_SHORT).show();
        DateFormat format = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date = format.parse(dateToParse);

            alarm.set(AlarmManager.RTC_WAKEUP, date.getTime(), pendingIntent);
            Toast.makeText(this, "tryL"+dateToParse, Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            Toast.makeText(this, "catch noty", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }



    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) // function for check if the user gave permissions to calendar
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // If request is cancelled, the result arrays are empty.
        Log.d("hello", "onRequestPermissionsResult: ");
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted. Continue the action or workflow
            // in your app.
            String[] words;
            String reminder=DB.getValue(getString(R.string.TIME_REMINDER));
            words=reminder.split(":");
            int hourInt=Integer.parseInt(words[0]);
            int minuteInt=Integer.parseInt(words[1]);
            int minuteIntEnd=0;
            String time=DB.getValue(getString(R.string.TIME));//The end time of the gymnastics according to the duration of the gymnastics time
            Log.d("Time", time);
            if(time.equals("1"))
                minuteIntEnd=minuteInt+10;
            if(time.equals("2"))
                minuteIntEnd=minuteInt+20;
            String dateString;
            String year;
            String month;
            String day;
            String numDay;
            for(int i=1;i<8;i++){
                numDay=getString(R.string.DAY) +i;
                Log.d("numDay", numDay);
                dateString=DB.getValue(numDay);
                Log.d("dateStringbefore", dateString);
                words=dateString.split("-");
                Log.d("word0", words[0]);
                day=words[1];
                Log.d("word1", day);
                month=words[2];
                Log.d("word2", month);
                year=words[3];
                Log.d("word3", year);
                int dayInt= Integer.parseInt(day);
                Log.d("day", ""+dayInt);
                int monthInt= Integer.parseInt(month)-1;//month is less 1 because it order
                Log.d("day", ""+monthInt);
                int yearInt=Integer.parseInt(year);
                Log.d("day", ""+yearInt);
                ContentResolver cr = getContentResolver();
                ContentValues cv = new ContentValues();
                cv.put(CalendarContract.Events.TITLE, "exercise for:"+i+" day");
                cv.put(CalendarContract.Events.DESCRIPTION, "daily reminder of exercise");
                cv.put(CalendarContract.Events.EVENT_LOCATION, "power women application");
                Calendar start = Calendar.getInstance();
                start.set(yearInt, monthInt, dayInt, hourInt, minuteInt);
                long startMillis = start.getTimeInMillis();
                if(i==1)//
                    startMillisForNotification=startMillis;
                Log.d("startMillis", ""+startMillis);
                cv.put(CalendarContract.Events.DTSTART, startMillis);
                Calendar end = Calendar.getInstance();
                end.set(yearInt, monthInt, dayInt, hourInt, minuteIntEnd);
                Log.d("calendar1: ", "calendar");
                Long endMillis = end.getTimeInMillis();
                cv.put(CalendarContract.Events.DTEND, endMillis);
                cv.put(CalendarContract.Events.CALENDAR_ID, 1);
                cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
                Log.d("calendar2: ", String.valueOf(CalendarContract.Calendars.CONTENT_URI));
                Log.d("calendar: ", "calendar");
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, cv);
                Log.d("startMillis", ""+startMillisForNotification);
            }
        }
        else {
            Toast.makeText(this, "permission denied no event inserted to calendar ", Toast.LENGTH_SHORT).show();
            // Explain to the user that the feature is unavailable because
            // the features requires a permission that the user has denied.
            // At the same time, respect the user's decision. Don't link to
            // system settings in an effort to convince the user to change
            // their decision.
        }
    }

    public void chooseTimeListener(View view)
    {
        Toast.makeText(Time.this, "select 10 or 20 minutes", Toast.LENGTH_LONG).show();
    };
    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                timeReminderButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                selectTimeToReminder=String.format(Locale.getDefault(), "%02d:%02d",hour, minute);

            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    public void timeDefine(View v)
    {

        if(v.getId()==R.id.image10Min) {//for 10 minitue
            selectTimeToVideo = "1";
            // Random rnd = new Random();
            // int randomColor = rnd.nextInt();
            //    Log.d("int", ""+randomColor);
            // "@color/lightPink"
            //492681106
            //157746808
            //175804752 la
            //va 2011634197
            //-1108751392 va
            img10.setBackgroundColor(-1108751392);
            img20.setBackgroundColor(175804752);
            Toast.makeText(Time.this, "timeDefine", Toast.LENGTH_LONG).show();
            chooseTimeButton.setText("you choose 10 minute");

            // img10.setBackground("#F3DCE4");
            //   img10.setBackground();
        }
        if(v.getId()==R.id.image20Min) {//for 20 minitue
            chooseTimeButton.setText("you choose 20 minute");
            selectTimeToVideo = "2";
            img20.setBackgroundColor(-1108751392);
            img10.setBackgroundColor(175804752);
        }
    }
    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    public void resetDBForStartCycleWeek(){
        checkinsertdata = DB.updateuserdata(getString(R.string.APP_ON), getString(R.string.YES));
        int numCycle=Integer.parseInt(DB.getValue(getString(R.string.weeklyCycle)));
        numCycle+=1;
        checkinsertdata = DB.updateuserdata(getString(R.string.weeklyCycle), ""+numCycle);
        checkinsertdata = DB.updateuserdata(getString(R.string.TIME), selectTimeToVideo);
        checkinsertdata = DB.updateuserdata(getString(R.string.TIME_REMINDER),selectTimeToReminder);
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY), "1");
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY1_DO), "");
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY2_DO), "");
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY3_DO), "");
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY4_DO), "");
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY5_DO), "");
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY6_DO), "");
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY7_DO), "");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.gallery);
        byte[] b=getBytes(bitmap);
        checkinsertdata =DB.insertPictureData(getString(R.string.BEFORE)+numCycle,b,"no photo");
        checkinsertdata =DB.insertPictureData(getString(R.string.AFTER)+numCycle,b,"no photo");
        Log.d("reset", ""+checkinsertdata);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        setTitle("TIME");
        DB = new DBHelper(this);
        hour=-1;
        minute=-1;
        timeReminderButton = findViewById(R.id.btnReminder);
        chooseTimeButton = findViewById(R.id.btnTime);
        img10=findViewById(R.id.image10Min);
        img20=findViewById(R.id.image20Min);
        numberToString=0;
        selectTimeToReminder="-1";
        selectTimeToVideo="-1";
        Button btn3 = findViewById(R.id.btnStart);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selectTimeToReminder.equals("-1")) {
                    Toast.makeText(Time.this, "select time to reminder", Toast.LENGTH_LONG).show();
                    return;
                }
                if(selectTimeToVideo.equals("-1")) {
                    Toast.makeText(Time.this, "select long time to exercise", Toast.LENGTH_LONG).show();
                    return;
                }
                setWeeklyCycle();
                resetDBForStartCycleWeek();
                //insertEventsToCalendar
                ActivityCompat.requestPermissions(Time.this, new String[]{Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR}, 0);
                ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.READ_CALENDAR);
                ContextCompat.checkSelfPermission(v.getContext()  , Manifest.permission.WRITE_CALENDAR);
                //notify after calendar
                setAlarm();
                Intent intent = new Intent(Time.this, TodayActivity.class);
                startActivity(intent);

            }
        });


    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("mylog", ">>> onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("mylog", ">>> onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("mylog", ">>> onPause()");

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("mylog", ">>> onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("mylog", ">>> onDestroy()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("mylog", ">>> onRestart()");
    }

}