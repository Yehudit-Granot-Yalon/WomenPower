package com.yehudit.powerwomen;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private DBHelper DB;
    private Boolean checkInsertData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("SETTINGS");
        DB = new DBHelper(this);

    }
    public void listenerS3(View v) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
// Create the same Intent
        Intent alarmIntent = new Intent(this, BroadcastAlarm.class);
// Create PendingIntent with the same Intent & ALARM_ID & with different flag: FLAG_NO_CREATE
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 0,
                alarmIntent, PendingIntent.FLAG_NO_CREATE);
// If alarmPendingIntent is not null – alarm is SET ON – so we can cancel it
        if(alarmPendingIntent != null)
        {
            alarmManager.cancel(alarmPendingIntent);
            alarmPendingIntent.cancel();
        }
        Toast.makeText(this, "Alerts have been muted", Toast.LENGTH_SHORT).show();
    }

    public void listenerS2(View v) {
        int checkedItem[]=new int[1];
        //Log.d("listenerHello", "listenerHello: ");
        if(DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.NO))) {
            Toast.makeText(this, "you need to set a weekly exercise cycle", Toast.LENGTH_SHORT).show();
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
                            //Toast.makeText(this, "onClick!!"+checkedItem[0], Toast.LENGTH_SHORT).show();
                            if(checkedItem[0]==0) {
                                Toast.makeText(SettingsActivity.this, "10 min", Toast.LENGTH_SHORT).show();
                                 DB.updateuserdata(getString(R.string.TIME), "1");

                            }
                            if(checkedItem[0]==1) {
                                Toast.makeText(SettingsActivity.this, "20 min", Toast.LENGTH_SHORT).show();
                                //deletContact(contact.getPhone());
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
        //Log.d("listenerHello", "listenerHello: ");
        if(DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.NO))) {
            Toast.makeText(this, "you need to set a weekly exercise cycle", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            // deletContactBtn.setOnClickListener(new View.OnClickListener() {

            // public void onClick(View view) {
            // Toast.makeText(DetailContactActivity.this, "onClick!!", Toast.LENGTH_SHORT).show();
            // AlertDialog builder instance to build the alert dialog
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            // set the custom icon to the alert dialog
            //alertDialog.setIcon(R.drawable.image_logo);

            // title of the alert dialog
            alertDialog.setTitle("What length of time of video would you like?");

            // list of the items to be displayed to
            // the user in the form of list
            // so that user can select the item from
            final String[] listItems = new String[]{"legs", "abdominal","all body"};

            // the function setSingleChoiceItems is the function which builds
            // the alert dialog with the single item selection
            alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // update the selected item which is selected by the user
                    // so that it should be selected when user opens the dialog next time
                    // and pass the instance to setSingleChoiceItems method
                    checkedItem[0] = which;

                    // now also update the TextView which previews the selected item
                    //  tvSelectedItemPreview.setText("Selected Item is : " + listItems[which]);

                    // when selected an item the dialog should be closed with the dismiss method


                }
            });
            alertDialog.setNegativeButton("FINISH", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //Toast.makeText(this, "onClick!!"+checkedItem[0], Toast.LENGTH_SHORT).show();
                    if(checkedItem[0]==0) {
                        Toast.makeText(SettingsActivity.this, "1", Toast.LENGTH_SHORT).show();
                        //deletContact(contact.getPhone());
                        DB.updateuserdata(getString(R.string.KIND), "1");

                    }
                    if(checkedItem[0]==1) {
                        Toast.makeText(SettingsActivity.this, "2", Toast.LENGTH_SHORT).show();
                        //deletContact(contact.getPhone());
                        DB.updateuserdata(getString(R.string.KIND), "2");

                    }
                    if(checkedItem[0]==2
                    ) {
                        Toast.makeText(SettingsActivity.this, "3 ", Toast.LENGTH_SHORT).show();
                        //deletContact(contact.getPhone());
                        DB.updateuserdata(getString(R.string.KIND), "3");

                    }
                }

            });
            // create and build the AlertDialog instance
            // with the AlertDialog builder instance
            AlertDialog customAlertDialog = alertDialog.create();

            // show the alert dialog when the button is clicked
            customAlertDialog.show();
            // }
            //});
        }


    }
    public void listenerS4(View v) {
        if(DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.NO))) {
            Toast.makeText(this, "you need to set a weekly exercise cycle", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "choose time", Toast.LENGTH_SHORT).show();

    }

}
