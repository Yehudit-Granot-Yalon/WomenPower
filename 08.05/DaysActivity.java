package com.ilanp.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DaysActivity extends AppCompatActivity {
    public String[] days;
    public String thisDayInWeek;
    DBHelper DB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        DB = new DBHelper(this);
        days = new String[7];
       /* Calendar calendar = new GregorianCalendar();
        int dayOfWeek = Calendar.DAY_OF_WEEK;
        String weekday = new DateFormatSymbols().getShortWeekdays()[dayOfWeek];*/

        //https://stackoverflow.com/questions/33199084/how-to-get-next-seven-days-in-android
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
        SimpleDateFormat sdfday = new SimpleDateFormat("EEEE");
        for (int i = 0; i < 7; i++) {
           Calendar calendar = new GregorianCalendar();
           calendar.add(Calendar.DATE, i);
            if(i==0){
             thisDayInWeek=sdfday.format(calendar.getTime());
            }
           days[i]=sdf.format(calendar.getTime());
        }
        Log.d("day", thisDayInWeek);
        Boolean checkinsertdata = DB.updateuserdata(getString(R.string.DAY), thisDayInWeek);





        Button b1 = findViewById(R.id.btnday1);
        //b1.setText(days[0]);
        b1.setText(thisDayInWeek);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("daily", "onClick: ");

            }
        });
        Button b2 = findViewById(R.id.btnday2);
        b2.setText(days[1]);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("daily", "onClick: ");
            }
        });
        Button b3 = findViewById(R.id.btnday3);
        b3.setText(days[2]);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("daily", "onClick: ");
            }
        });
        Button b4 = findViewById(R.id.btnday4);
        b4.setText(days[3]);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("daily", "onClick: ");
            }
        });
        Button b5 = findViewById(R.id.btnday5);
        b5.setText(days[4]);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("daily", "onClick: ");
            }
        });
        Button b6 = findViewById(R.id.btnday6);
        b6.setText(days[5]);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("daily", "onClick: ");
            }
        });
        Button b7= findViewById(R.id.btnday7);
        b7.setText(days[6]);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("daily", "onClick: ");
            }
        });



    }
}
