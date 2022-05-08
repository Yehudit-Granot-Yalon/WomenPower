package com.ilanp.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Lesson2 + Lesson3 - First Android App!
 * @autor Ilan Peretz | 17.2.2022
 */
public class MainActivity<string> extends AppCompatActivity {
    public String[] days;
    public DBHelper myDB;
    final int callbackId = 42;
    DBHelper DB;
    public void resetDB(){
        DB = new DBHelper(this);

        Boolean checkinsertdata;
        if(checkKayInDB(getString(R.string.APP_ON))==false) {
            checkinsertdata = DB.insertuserdata(getString(R.string.APP_ON), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.KIND), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.TIME), "");


        }

    }
   // public void
    public void checkStartWeek(){ // check if the week begin

        String chekStart= getValueFromDB(getString(R.string.APP_ON));

        Log.d("checkStartWeek1: ", chekStart);
        if(chekStart.equals(getString(R.string.YES))) {
            Intent intent = new Intent(MainActivity.this, DaysActivity.class);
            startActivity(intent);
        }

    }
    private void checkPermission(int callbackId, String... permissionsId) {
        Log.d("PER", "checkPermission: ");
        boolean permissions = true;
        for (String p : permissionsId) {
            permissions = permissions && ContextCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_GRANTED;
        }

        if (!permissions) {
            Log.d("PER", "ifnocheckPermission: ");
            ActivityCompat.requestPermissions(this, permissionsId, callbackId);
        }
    }

    public boolean checkKayInDB(String valueToSearch){
        Cursor res = DB.getdata();
        while (res.moveToNext()) {

            if (res.getString(0).equals(valueToSearch)) {
                Log.d("indb", "yes");
                return true;
            }
        }
        Log.d("indb", "no");
        return false;
    }
    public String getValueFromDB(String valueToSearch) {

        String value = "";
        Cursor res = DB.getdata();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("mykey :" + res.getString(0) + "\n");
            Log.d("alueToSearch", res.getString(0));
            if (res.getString(0).equals(valueToSearch))
                value = res.getString(1);
            buffer.append("myvalue :" + res.getString(1) + "\n");
            Log.d("value", value);
            //return value;

        }

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
        return value;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetDB();
        checkStartWeek();
        myDB = new DBHelper(this);

        ImageView img = findViewById(R.id.imgID);
        img.setBackgroundColor(Color.MAGENTA);
        img.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Random rnd = new Random();
                int randomColor = rnd.nextInt();
                img.setBackgroundColor(randomColor);
            }
        });
        Button btn5 = findViewById(R.id.btn5ID);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /* EditText edt = findViewById(R.id.edtID);
                NAME = edt.getText().toString();
                if (NAME == null || (NAME != null && NAME.isEmpty()))
                    Toast.makeText(MainActivity.this, "Name must be NOT Empty!", Toast.LENGTH_LONG).show();
                else {
                    TextView txv = findViewById(R.id.txvNameID);
                    txv.setText("hello " + NAME);
                }
                String string = getString(R.string.APP_ON);
                Boolean checkinsertdata = myDB.insertuserdata("HI","no");
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                String findNameInDB = getValueFromDB(string);
                TextView itxv = findViewById(R.id.txvTitleID);
                Toast.makeText(MainActivity.this, findNameInDB, Toast.LENGTH_LONG).show();
                itxv.setText(findNameInDB);*/
                Boolean checkinsertdata = myDB.insertuserdata("HI", "no");
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();


            }

        });

        Button btn0 = findViewById(R.id.btn0ID);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                Log.d("camera", "onClick: ");
                startActivity(intent);

            }
        });
        Button btn1 = findViewById(R.id.btn1ID);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KindActivity.class);
                startActivity(intent);

            }
        });


        Button btn4 = findViewById(R.id.btn4ID);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbActivity.class);
                Log.d("HI", "onClick: ");
                startActivity(intent);
            }
        });


        Button btn6 = findViewById(R.id.btn6ID);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkPermission(callbackId, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR);

                Log.d("HI", "onClick:6 ");
                ContentResolver cr = getContentResolver();
                ContentValues cv = new ContentValues();
                cv.put(CalendarContract.Events.TITLE, "birthday");
                cv.put(CalendarContract.Events.DESCRIPTION, "birthday of me");
                cv.put(CalendarContract.Events.EVENT_LOCATION, "world");
                Calendar start = Calendar.getInstance();
                start.set(2022, 5, 1, 23, 0);
                Long startMillis = start.getTimeInMillis();
                cv.put(CalendarContract.Events.DTSTART, startMillis);
                Calendar end = Calendar.getInstance();
                end.set(2022, 5, 2, 23, 0);
                Long endMillis = end.getTimeInMillis();
                cv.put(CalendarContract.Events.DTEND, endMillis);
                cv.put(CalendarContract.Events.CALENDAR_ID, 1);
                cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
                Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, cv);
                Log.d("EVENT", "onClick:6 ");
                // Toast.makeText(this,"event succefull",Toast.LENGTH_SHORT).show();


            }
        });

        Button btn7 = findViewById(R.id.btn7ID);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DaysActivity.class);
                Log.d("daily", "onClick: ");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        MenuItem menuItem1 = menu.add("About");
        MenuItem menuItem2 = menu.add("Exit");

        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                Toast.makeText(getApplicationContext(), "EXIT App", Toast.LENGTH_LONG).show();
                showAboutDialog();
                return true;
            }
        });

        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                Toast.makeText(getApplicationContext(), "EXIT App", Toast.LENGTH_LONG).show();
                showExitDialog();
                return true;
            }
        });

        return true;
    }

    private void showAboutDialog()
    {
        AlertDialog.Builder aboutDialog = new AlertDialog.Builder(this);
        aboutDialog.setIcon(R.mipmap.ic_launcher);
        aboutDialog.setTitle("About App");
        aboutDialog.setMessage("This is my First Android App!\nBy: Ilan Peretz.");
        aboutDialog.setCancelable(true);

        aboutDialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        aboutDialog.show();
    }

    public void showExitDialog()
    {
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setIcon(R.drawable.ic_exit);
        exitDialog.setTitle("Exit App");
        exitDialog.setMessage("Do you really want to exit?");
        exitDialog.setCancelable(false);

        exitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish(); // destroy & exit this activity
            }
        });

        exitDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        exitDialog.show();
    }
}