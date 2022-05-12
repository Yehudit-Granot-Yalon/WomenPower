package com.ilanp.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;
//code clock
//https://github.com/codeWithCal/TimePickerAndroidStudio/blob/master/app/src/main/java/codewithcal/au/timerpickertutorial/MainActivity.java

public class TimeActivity extends AppCompatActivity
{
    DBHelper DB;
    Button timeButton;

    int hour, minute;
    public void insertEventsToCalendar(){


    }
    public String getValueFromDB(String valueToSearch) {

        String value = "";
        Cursor res = DB.getdata();
        while (res.moveToNext()) {
            if (res.getString(0).equals(valueToSearch)) {
                Log.d("find", "getValueFromDB: ");
                value = res.getString(1);
            }
        }
        return value;
    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
                String time=hour+":"+minute;
                Boolean checkinsertdata = DB.updateuserdata(getString(R.string.TIME_REMINDER), time);

            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        setTitle("TimeActivity");
        DB = new DBHelper(this);
        hour=-1;
        minute=-1;

        timeButton = findViewById(R.id.timeButton);



        Button btn1 = findViewById(R.id.btnSecond1ID);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //for 10 minitue
                Boolean checkinsertdata = DB.updateuserdata(getString(R.string.TIME), "1");



            }
        });
        Button btn2 = findViewById(R.id.btnSecond2ID);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //for 20 minitue
                Boolean checkinsertdata = DB.updateuserdata(getString(R.string.TIME), "2");



            }
        });
        Button btn3 = findViewById(R.id.startAfterChoose);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s=getValueFromDB(getString(R.string.TIME_REMINDER));
                Log.d("valindb", s);
                if(s.equals("-1")) {
                    //if(hour==-1||minute==-1) {
                    Toast.makeText(TimeActivity.this, "select time to reminder", Toast.LENGTH_LONG).show();
                    return;
                }
                s=getValueFromDB(getString(R.string.TIME));
                if(s.equals("-1")) {
                    Toast.makeText(TimeActivity.this, "select long time to exercise", Toast.LENGTH_LONG).show();
                    return;
                }
                insertEventsToCalendar();
                Intent intent = new Intent(TimeActivity.this, DaysActivity.class);
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