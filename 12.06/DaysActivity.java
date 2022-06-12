package com.yehudit.powerwomen;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DaysActivity extends AppCompatActivity {


    private DBHelper DB;
    private String kindVideo;
    private String timeVideo;
    private Boolean checkInsertData;
    private Switch switches[];
    private Button buttons[];

    public void listenerButtons(View v) {
        int num=0;
        switch (v.getId())
        {
            case R.id.btnday1:
               num=1;
                break;

            case R.id.btnday2:
                num=2;
                break;

            case R.id.btnday3:
                num=3;
                break;

            case R.id.btnday4:
                num=4;
                break;
            case R.id.btnday5:
                num=5;
                break;
            case R.id.btnday6:
                num=6;
                break;
            case R.id.btnday7:
                num=7;
                break;
        }
        Intent intent = new Intent(DaysActivity.this, VideoActivity.class);//-------declare how father video
        intent.putExtra("numVideo",num);
        startActivity(intent);

//        if(v.getId()==findViewById(R.id.btnday1).getId())
//            num=1;
//        if(v.getId()==findViewById(R.id.btnday2).getId())
//            goToShowVideo(2);
//        if(v.getId()==findViewById(R.id.btnday3).getId())
//            goToShowVideo(3);
//        if(v.getId()==findViewById(R.id.btnday4).getId())
//            goToShowVideo(4);
//        if(v.getId()==findViewById(R.id.btnday5).getId())
//            goToShowVideo(5);
//        if(v.getId()==findViewById(R.id.btnday6).getId())
//            goToShowVideo(6);
//        if(v.getId()==findViewById(R.id.btnday7).getId())
//            goToShowVideo(7);

    }


    private void goToShowVideo(int numVideo) {//func that fix video that go to be show
        String valVideoToFind="";
        valVideoToFind+=kindVideo;
        valVideoToFind+=timeVideo;
        valVideoToFind+=numVideo;
        Log.d("valVideoToFind", valVideoToFind);
        String videoID=DB.getValue(valVideoToFind);
        Log.d("videoplay", videoID);
        checkInsertData = DB.updateuserdata(getString(R.string.VIDEO), videoID);
        Intent intent = new Intent(DaysActivity.this, VideoActivity.class);//-------declare how father video
        intent.putExtra("numVideo",numVideo);
        startActivity(intent);

    }
//    public void buttonClicked(View v)
//    {
//        switch (v.getId())
//        {
//            case R.id.btnCheckAlarmStatusID:
//                isAlarmOn();
//                break;
//
//            case R.id.btnOneTimeAlarmID:
//                createOneTimeAlarm();
//                break;
//
//            case R.id.btnRepeatingAlarmID:
//                createRepeatingAlarm();
//                break;
//
//            case R.id.btnCancelAlarmID:
//                cancelAlarm();
//                break;
//        }
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        DB = new DBHelper(this);
        switches=new Switch[7];
        buttons=new Button[7];
        kindVideo=DB.getValue(getString(R.string.KIND));
        timeVideo=DB.getValue(getString(R.string.TIME));
        switches[0]=findViewById(R.id.switch1);
        switches[1]=findViewById(R.id.switch2);
        switches[2]=findViewById(R.id.switch3);
        switches[3]=findViewById(R.id.switch4);
        switches[4]=findViewById(R.id.switch5);
        switches[5]=findViewById(R.id.switch6);
        switches[6]=findViewById(R.id.switch7);
        String findInDB="";
        int placeSwitch=0;
        int numDay=0;
        for(;placeSwitch<7;placeSwitch++){
            numDay++;
            findInDB="DAY"+numDay+"_DO";
            if(DB.getValue(findInDB).equals(getString(R.string.YES))) {//For the status quo that already existed after leaving
                switches[placeSwitch].setChecked(true);
            }
            switches[placeSwitch].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String findInDB="";
                    int placeSwitch=0;
                    for(;placeSwitch<7;placeSwitch++) {
                        if(v.getId()==switches[placeSwitch].getId()){
                            int num=1+placeSwitch;
                            findInDB="DAY"+num+"_DO";
                            break;
                        }
                    }
                    Log.d("place",""+placeSwitch );
                   if (switches[placeSwitch].isChecked()) {
                       checkInsertData = DB.updateuserdata(findInDB, switches[placeSwitch].getTextOn().toString());
                    }
                    else {
                       checkInsertData = DB.updateuserdata(findInDB, switches[placeSwitch].getTextOff().toString());
                    }
                }
            });
        }



        buttons[0]=findViewById(R.id.btnday1);
        buttons[1]=findViewById(R.id.btnday2);
        buttons[2]=findViewById(R.id.btnday3);
        buttons[3]=findViewById(R.id.btnday4);
        buttons[4]=findViewById(R.id.btnday5);
        buttons[5]=findViewById(R.id.btnday6);
        buttons[6]=findViewById(R.id.btnday7);
        int placeButton=0;

        for(; placeButton<7; placeButton++){
            findInDB="DAY"+ (placeButton+1);
            buttons[placeButton].setText(DB.getValue( findInDB));

        }

//
//        Button b1 = findViewById(R.id.btnday1);
//        b1.setText(DB.getValue(getString(R.string.DAY1)));
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToShowVideo(1);
//
//            }
//        });
//        Button b2 = findViewById(R.id.btnday2);
//        b2.setText(DB.getValue(getString(R.string.DAY2)));
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToShowVideo(2);
//            }
//        });
//        Button b3 = findViewById(R.id.btnday3);
//        b3.setText(DB.getValue(getString(R.string.DAY3)));
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToShowVideo(3);
//            }
//        });
//        Button b4 = findViewById(R.id.btnday4);
//        b4.setText(DB.getValue(getString(R.string.DAY4)));
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToShowVideo(4);
//            }
//        });
//        Button b5 = findViewById(R.id.btnday5);
//        b5.setText(DB.getValue(getString(R.string.DAY5)));
//        b5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToShowVideo(5);
//            }
//        });
//        Button b6 = findViewById(R.id.btnday6);
//        b6.setText(DB.getValue(getString(R.string.DAY6)));
//        b6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToShowVideo(6);
//            }
//        });
//        Button b7= findViewById(R.id.btnday7);
//        b7.setText(DB.getValue(getString(R.string.DAY7)));
//        b7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToShowVideo(7);
//            }
//        });



    }


    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("mylogdays", ">>> onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("mylogdays", ">>> onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("mylogdays", ">>> onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
       // Intent intent = new Intent(DaysActivity.this, MainActivity.class);//-------declare how father video
      //  startActivity(intent);

        Log.d("mylogdays", ">>> onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("mylogdays", ">>> onDestroy()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("mylogdays", ">>> onRestart()");
    }



}
