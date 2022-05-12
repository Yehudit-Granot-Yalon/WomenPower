package com.ilanp.firstapp;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DaysActivity extends AppCompatActivity {
    public String[] days;
    public String thisDayInWeek;
    public DBHelper DB;
    public String kindVideo;
    public String timeVideo;
    public Boolean checkinsertdata;

    public String getValueFromDB(String valueToSearch) {

        String value = "";
        Cursor res = DB.getdata();
        while (res.moveToNext()) {
            if (res.getString(0).equals(valueToSearch))
                value = res.getString(1);
        }
        return value;
    }
    public void goToShowVideo(String date) {//func that fix video that go to be show
        String[] words = date.split(" ");
        String selectedDay=words[0];
        String valVideoToFind="";
        valVideoToFind+=kindVideo;
        valVideoToFind+=timeVideo;
        valVideoToFind+=selectedDay;
        Log.d("valVideoToFind", valVideoToFind);
        String videoID=getValueFromDB(valVideoToFind);
        Log.d("videoplay", videoID);
        checkinsertdata = DB.updateuserdata(getString(R.string.VIDEO), videoID);
        Intent intent = new Intent(DaysActivity.this, VideoActivity.class);//-------declare how father video
        startActivity(intent);

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        DB = new DBHelper(this);
        days = new String[7];
        kindVideo=getValueFromDB(getString(R.string.KIND));
        timeVideo=getValueFromDB(getString(R.string.TIME));
        //https://stackoverflow.com/questions/33199084/how-to-get-next-seven-days-in-android
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
        SimpleDateFormat sdfday = new SimpleDateFormat("EEEE");
        String day;
        for (int i = 0; i < 7; i++) {
            day="DAY";
            day+=String.valueOf(i+1);
            Log.d("dayfor", day);
           Calendar calendar = new GregorianCalendar();
           calendar.add(Calendar.DATE, i);
            if(i==0){
             thisDayInWeek=sdfday.format(calendar.getTime());
            }
           days[i]=sdf.format(calendar.getTime());

            checkinsertdata = DB.updateuserdata(day, sdf.format(calendar.getTime()));
        }
        Log.d("day", thisDayInWeek);
        checkinsertdata = DB.updateuserdata(getString(R.string.DAY), thisDayInWeek);





        Button b1 = findViewById(R.id.btnday1);
        b1.setText(getValueFromDB(getString(R.string.DAY1)));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowVideo(getValueFromDB(getString(R.string.DAY1)));
            }
        });
        Button b2 = findViewById(R.id.btnday2);
        b2.setText(getValueFromDB(getString(R.string.DAY2)));
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowVideo(getValueFromDB(getString(R.string.DAY2)));
            }
        });
        Button b3 = findViewById(R.id.btnday3);
        b3.setText(getValueFromDB(getString(R.string.DAY3)));
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowVideo(getValueFromDB(getString(R.string.DAY3)));
            }
        });
        Button b4 = findViewById(R.id.btnday4);
        b4.setText(getValueFromDB(getString(R.string.DAY4)));
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowVideo(getValueFromDB(getString(R.string.DAY4)));
            }
        });
        Button b5 = findViewById(R.id.btnday5);
        b5.setText(getValueFromDB(getString(R.string.DAY5)));
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowVideo(getValueFromDB(getString(R.string.DAY5)));
            }
        });
        Button b6 = findViewById(R.id.btnday6);
        b6.setText(getValueFromDB(getString(R.string.DAY6)));
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowVideo(getValueFromDB(getString(R.string.DAY6)));
            }
        });
        Button b7= findViewById(R.id.btnday7);
        b7.setText(getValueFromDB(getString(R.string.DAY7)));
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowVideo(getValueFromDB(getString(R.string.DAY7)));
            }
        });



    }
}
