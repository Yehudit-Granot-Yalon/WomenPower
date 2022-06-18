package com.yehuditg.womenpower;

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
public class TimeActivity extends AppCompatActivity
{
    private DBHelper DB;
    private HelpFunctions helpFunctions;
    private Button timeReminderButton;
    private Button chooseTimeButton;
    private ImageView img10;
    private ImageView img20;
    private int numberToString;
    int hour, minute;
    private Boolean checkinsertdata;
    private String selectTimeToReminder;
    private String selectTimeToVideo;
    private void setCalendar(){//A function that puts events in a calendar
         int minuteToAdd=0;
        String time=selectTimeToVideo;//The end time of the exercise according to the duration of the gymnastics time
        Log.d("Time", time);
        if(time.equals("1"))//Add to the diary the duration of the exercise in minutes
            minuteToAdd=10;
        if(time.equals("2"))
            minuteToAdd=20;
       String dateString;
        int parameters[];
       String numDay;
        for(int i=1;i<8;i++){
            numDay=getString(R.string.DAY) +i;
            dateString=DB.getValue(numDay);
            parameters=helpFunctions.getTimeAndDate( dateString,selectTimeToReminder);
            ContentResolver cr = getContentResolver();
            ContentValues cv = new ContentValues();
            cv.put(CalendarContract.Events.TITLE, "exercise for: "+i+" day");
            cv.put(CalendarContract.Events.DESCRIPTION, "daily reminder of exercise");
            cv.put(CalendarContract.Events.EVENT_LOCATION, "power women application");
            Calendar start = Calendar.getInstance();
            start.set(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]);
            long startMillis = start.getTimeInMillis();
            Log.d("startMillis", ""+startMillis);
            cv.put(CalendarContract.Events.DTSTART, startMillis);
            Calendar end = Calendar.getInstance();
            end.set(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]+minuteToAdd);
            Log.d("calendar1: ", "calendar");
            Long endMillis = end.getTimeInMillis();
            cv.put(CalendarContract.Events.DTEND, endMillis);
            cv.put(CalendarContract.Events.CALENDAR_ID, 3);//#######3
            cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, cv);
        }
    }
    public void popTimePicker(View view)//Function for time selection
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    public void timeDefine(View v)//Listener function for time selection button
    {

        if(v.getId()==R.id.image10Min) {//for 10 minitue
            selectTimeToVideo = "1";
            img10.setBackgroundColor(-1108751392);
            img20.setBackgroundColor(175804752);
        }
        if(v.getId()==R.id.image20Min) {//for 20 minitue
            selectTimeToVideo = "2";
            img20.setBackgroundColor(-1108751392);
            img10.setBackgroundColor(175804752);
        }
    }

    public void resetDBForStartCycleWeek(){//Data database initialization for the new transcription week
        checkinsertdata = DB.updateuserdata(getString(R.string.APP_ON), getString(R.string.YES));
        int numCycle=Integer.parseInt(DB.getValue(getString(R.string.weeklyCycle)));
        numCycle+=1;
        checkinsertdata = DB.updateuserdata(getString(R.string.weeklyCycle), ""+numCycle);
        String kindOfVideo=getIntent().getStringExtra("typeVideo");
        checkinsertdata = DB.updateuserdata(getString(R.string.KIND), kindOfVideo);
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
        byte[] b=helpFunctions.getBytes(bitmap);
        checkinsertdata =DB.insertPictureData(getString(R.string.BEFORE)+numCycle,b,"no photo");
        checkinsertdata =DB.insertPictureData(getString(R.string.AFTER)+numCycle,b,"no photo");
    }
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
            helpFunctions.DB.updateuserdata(day, sdf.format(calendar.getTime()));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_time);
        setContentView(R.layout.activity_time);
        setTitle("TIME");
        helpFunctions=new HelpFunctions(this);
        DB = new DBHelper(this);
        hour=-1;
        minute=-1;
        timeReminderButton = findViewById(R.id.btnReminder);
        //chooseTimeButton = findViewById(R.id.btnTime);
         img10=findViewById(R.id.image10Min);
        img20=findViewById(R.id.image20Min);
        numberToString=0;
        selectTimeToReminder="-1";
        selectTimeToVideo="-1";
        Button btn3 = findViewById(R.id.btnStart);
        //After  finished setting the times initializes and  alerts - starts
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(selectTimeToReminder.equals("-1")) {
                    Toast.makeText(TimeActivity.this, "select time to reminder", Toast.LENGTH_LONG).show();
                    return;
                }
                if(selectTimeToVideo.equals("-1")) {
                    Toast.makeText(TimeActivity.this, "select long time to exercise", Toast.LENGTH_LONG).show();
                    return;
                }
                setWeeklyCycle();
                resetDBForStartCycleWeek();
                helpFunctions.startNotification(selectTimeToReminder,30);//Arranges the reminders and gets the date and time and time that needs to be changed in minutes for half an hour before performing the exercise
                setCalendar();
                //Intent intent = new Intent(TimeActivity.this, TodayActivity.class);
                Intent intent = new Intent(TimeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();//No need to return to this activity
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