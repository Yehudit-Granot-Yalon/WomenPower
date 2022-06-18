package com.yehuditg.womenpower;
import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
   private DBHelper DB;
    private Boolean app_on;
    private Boolean checkInsertData;
    private HelpFunctions helpFunctions;
    int hour;
    int minute;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("SETTINGS");
        DB = new DBHelper(this);
        helpFunctions=new HelpFunctions(this);


    }
    public void listenerS3(View v) {
        helpFunctions.stopNotification ();
        Toast.makeText(this, "Alerts have been muted", Toast.LENGTH_SHORT).show();
    }

    public void listenerS2(View v) {
        int checkedItem[]=new int[1];
        if(!app_on) {
            Toast.makeText(this, "start weekly exercise cycle", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("What length of time of video would you like?");
                    final String[] listItems = new String[]{"10 minute", "20 minute"};
                    alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkedItem[0] = which;
                        }
                    });
                    alertDialog.setNegativeButton("FINISH", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(checkedItem[0]==0) {
                                 DB.updateuserdata(getString(R.string.TIME), "1");

                            }
                            if(checkedItem[0]==1) {
                                DB.updateuserdata(getString(R.string.TIME), "2");

                            }
                        }

                    });

                    AlertDialog customAlertDialog = alertDialog.create();
                    customAlertDialog.show();
        }


    }
    public void listenerS1(View v) {
        int checkedItem[]=new int[1];
        if(!app_on) {
            Toast.makeText(this, "you need to set a weekly exercise cycle", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Which kind of video would you like?");
            final String[] listItems = new String[]{"legs", "abdominal","all body"};
            alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    checkedItem[0] = which;
                }
            });
            alertDialog.setNegativeButton("FINISH", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if(checkedItem[0]==0) {
                        Toast.makeText(SettingsActivity.this, "1", Toast.LENGTH_SHORT).show();
                        DB.updateuserdata(getString(R.string.KIND), "1");

                    }
                    if(checkedItem[0]==1) {
                        Toast.makeText(SettingsActivity.this, "2", Toast.LENGTH_SHORT).show();
                        DB.updateuserdata(getString(R.string.KIND), "2");

                    }
                    if(checkedItem[0]==2
                    ) {
                        Toast.makeText(SettingsActivity.this, "3 ", Toast.LENGTH_SHORT).show();
                        DB.updateuserdata(getString(R.string.KIND), "3");

                    }
                }

            });
            AlertDialog customAlertDialog = alertDialog.create();
            customAlertDialog.show();
        }


    }
    public void listenerS4(View view)//Function for time selection
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
               String selectTimeToReminder=String.format(Locale.getDefault(), "%02d:%02d",hour, minute);
                helpFunctions.startNotification(selectTimeToReminder,0);
                Toast.makeText(SettingsActivity.this, "Notifications at: "+selectTimeToReminder, Toast.LENGTH_SHORT).show();

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
//    public void listenerS4(View v) {
//        if(!app_on) {
//            Toast.makeText(this, "you need to set a weekly exercise cycle", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Toast.makeText(this, "choose time", Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    protected void onStart()
    {
        super.onStart();
        app_on=helpFunctions.checkAppOn();//Every time you get to the settings page you see if the gymnastics cycle has been set

        Log.d("mylogSetting", ">>> onStart()");

    }


}
