package com.yehudit.powerwomen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private boolean app_on;
    private DBHelper DB;
    private Boolean checkinsertdata;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private String getDateToday(){
     String date="";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-dd-MM-yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 0);
        date=sdf.format(calendar.getTime());

     return date;
    }
    private boolean ifCycleWeekFinish(){
        Log.d("ifCycleWeekFinish: ", "ifCycleWeekFinish: ");
        if(DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.YES))) {
            String date = getDateToday();
            Log.d(" checkinsertdata", date);
            int i;
            String dateInDB = "";
            for (i = 1; i < 8; i++) {
                dateInDB = DB.getValue(getString(R.string.DAY) + i);
                Log.d(" dateInDB", dateInDB);
                if (dateInDB.equals(date)) {
                    Log.d(" ifdateInDB", dateInDB);
                    checkinsertdata = DB.updateuserdata(getString(R.string.DAY), "" + i);
                    Log.d(" checkinsertdatanum", "" + i);
                    return false;
                }
            }
        }
        return true;
    }
    private void resetDB(){
        if(checkKayInDB(getString(R.string.APP_ON))==false) {//application not started yet
            Log.d("reset db", "resetDB: ");
            checkinsertdata = DB.insertuserdata(getString(R.string.APP_ON), getString(R.string.NO));
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY), "");
           // checkinsertdata = DB.insertuserdata(getString(R.string.DAY_), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY_START), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY_END), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.KIND), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.weeklyCycle), "0");
            checkinsertdata = DB.insertuserdata(getString(R.string.TIME_REMINDER), "-1");
            checkinsertdata = DB.insertuserdata(getString(R.string.TIME), "-1");
            checkinsertdata = DB.insertuserdata(getString(R.string.VIDEO), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY1), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY2), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY3), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY4), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY5), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY6), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY7), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY1_DO), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY2_DO), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY3_DO), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY4_DO), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY5_DO), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY6_DO), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.DAY7_DO), "");
            checkinsertdata = DB.insertuserdata("111", "aN_2L2vxKy0");
            checkinsertdata = DB.insertuserdata("112", "nG69wuXHwxg");
            checkinsertdata = DB.insertuserdata("113", "Jg61m0DwURs");
            checkinsertdata = DB.insertuserdata("114", "zbt1g9WX6bA");
            checkinsertdata = DB.insertuserdata("115", "FJA3R7n_594");
            checkinsertdata = DB.insertuserdata("116", "uVwNVEQS_uo");
            checkinsertdata = DB.insertuserdata("117", "R1EKAgFRe2E");
            checkinsertdata = DB.insertuserdata("121", "7HIr1g39PzA");
            checkinsertdata = DB.insertuserdata("122", "9hQTvrP6EsM");
            checkinsertdata = DB.insertuserdata("123", "HeolReSa5ic");
            checkinsertdata = DB.insertuserdata("124", "zZ8tWnE8kzQ");
            checkinsertdata = DB.insertuserdata("125", "G_26U5DIaRg");
            checkinsertdata = DB.insertuserdata("126", "X7BWNO_H8KY");
            checkinsertdata = DB.insertuserdata("127", "Smim7-qG8Ls");
            checkinsertdata = DB.insertuserdata("211", "1f8yoFFdkcY");
            checkinsertdata = DB.insertuserdata("212", "AnYl6Nk9GOA");
            checkinsertdata = DB.insertuserdata("213", "PlqWX_cv7bc");
            checkinsertdata = DB.insertuserdata("214", "1919eTCoESo");
            checkinsertdata = DB.insertuserdata("215", "QfMlHzgzBwQ");
            checkinsertdata = DB.insertuserdata("216", "xZaSVMB2yy0");
            checkinsertdata = DB.insertuserdata("217", "54x6yjnzLms");
            checkinsertdata = DB.insertuserdata("221", "3oeimlA6s68");
            checkinsertdata = DB.insertuserdata("222", "EGhERRQ3GP0");
            checkinsertdata = DB.insertuserdata("223", "53B40Aw0k3g");
            checkinsertdata = DB.insertuserdata("224", "EWjsnO-9BPU");
            checkinsertdata = DB.insertuserdata("225", "H765nVNbUsA");
            checkinsertdata = DB.insertuserdata("226", "0q7QQci6UhI");
            checkinsertdata = DB.insertuserdata("227", "kl5AhFQtfvg");
            checkinsertdata = DB.insertuserdata("311", "6VFLKdfxA24");
            checkinsertdata = DB.insertuserdata("312", "FGFfqCjtmS8");
            checkinsertdata = DB.insertuserdata("313", "j5SHMJ6mUoA");
            checkinsertdata = DB.insertuserdata("314", "vaq7YpCwH7M");
            checkinsertdata = DB.insertuserdata("315", "agvdkRc_img");
            checkinsertdata = DB.insertuserdata("316", "bqVBMv_nqJ4");
            checkinsertdata = DB.insertuserdata("317", "-K2vxOQG0MQ");
            checkinsertdata = DB.insertuserdata("321", "UItWltVZZmE");
            checkinsertdata = DB.insertuserdata("322", "UBMk30rjy0o");
            checkinsertdata = DB.insertuserdata("323", "IT94xC35u6k");
            checkinsertdata = DB.insertuserdata("324", "Y2eOW7XYWxc");
            checkinsertdata = DB.insertuserdata("325", "uyFjMupI5B0&t=259s");
            checkinsertdata = DB.insertuserdata("326", "hI-JSz2Iv-k");
            checkinsertdata = DB.insertuserdata("327", "uZbig5yMlN8");
        }

    }

   private void checkStartWeek(){ // check if the week begin
        Log.d("checkStartWeek", "checkStartWeek: ");
        if(DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.YES))) {

            if (ifCycleWeekFinish() == true) {
                checkinsertdata = DB.updateuserdata(getString(R.string.APP_ON), getString(R.string.NO));
                app_on=false;

            }
            else { app_on=true;}
        }
        else{
            app_on=false;
        }
    }
    private boolean checkKayInDB(String valueToSearch){
        Cursor res = DB.getdata();
        while (res.moveToNext()) {
            if (res.getString(0).equals(valueToSearch)) {
                return true;
            }
        }
        return false;
    }

    private void setAlarm(String text, String date, String time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        String timeTonotify="21:33";
        String dateTonotify="31-5-2022";
        Intent intent = new Intent(getApplicationContext(), AlarmBrodcast.class);
        intent.putExtra("event", "reminder my");
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
            Toast.makeText(MainActivity.this, "setAlremmm", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            Toast.makeText(MainActivity.this, "catch noty", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }



    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate1: ", "onCreate: ");
        setContentView(R.layout.activity_main);
        Log.d("onCreate2: ", "onCreate: ");
        DB = new DBHelper(this);
       String r="";

        resetDB();

        checkStartWeek();
ImageView dbim=findViewById(R.id.imageWelcome);
        dbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, DbActivity.class);
                 startActivity(intent);

            }
        });
       // Intent intent = new Intent(MainActivity.this, DaysActivity.class);
        //Log.d("daily", "onClick: ");
       // startActivity(intent);

//        ImageView img = findViewById(R.id.imgID);
//        img.setBackgroundColor(Color.MAGENTA);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View v) {
//                Random rnd = new Random();
//                int randomColor = rnd.nextInt();
//                img.setBackgroundColor(randomColor);
//            }
//        });







//        Button btn8 = findViewById(R.id.btn8ID);
//        btn8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent intent = new Intent(MainActivity.this, GalleryaActivity.class);
//                Log.d("gallerya", "onClick: ");
//                startActivity(intent);
//
//            }
//        });
//
//        Button btn0 = findViewById(R.id.btn0ID);
//        btn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
//                Log.d("camera", "onClick: ");
//                startActivity(intent);
//
//            }
//        });
        img1 = findViewById(R.id.imageNewProg);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KindActivity.class);
                startActivity(intent);

            }
        });
        img2 = findViewById(R.id.imageToday);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TodayActivity.class);
                startActivity(intent);

            }
      });
        img3 = findViewById(R.id.imageWeek);
        if(app_on==true) {

            //btn7.setTextColor(getColor(R.color.pink));
            //btnDays.setText("yesssssssss");

//getColor(R.color.pink)
        }
        if(app_on==false) {
            //btn7.setTextColor(getColor(R.color.pink));
            //btnDays.setText("nooo");
            //btnDays.setVisibility(View.GONE);
//getColor(R.color.pink)
        }
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(app_on==false){
                    Toast.makeText(getApplicationContext(), "choose plan to this week", Toast.LENGTH_LONG).show();
                }

                if(app_on==true) {
                    Intent intent = new Intent(MainActivity.this, DaysActivity.class);
                    Log.d("daily", "onClick: ");
                    startActivity(intent);
                }
            }
        });
        img4= findViewById(R.id.imageGallery);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GalleryaActivity.class);
                startActivity(intent);
            }
        });

        img5= findViewById(R.id.imageCamera);
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        img6= findViewById(R.id.imageInfo);
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CircleActivity.class);
                startActivity(intent);
            }
        });
//        Button btn2 = findViewById(R.id.btn2ID);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CircleActivity.class);
//                Log.d("circle", "onClick: ");
//                startActivity(intent);
//
//            }
//        });
//        Button btn4 = findViewById(R.id.btn4ID);
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DbActivity.class);
//                Log.d("HI", "onClick: ");
//                startActivity(intent);
//            }
//        });

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
        Log.d("mylogMain", ">>> onStop()");
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
        MenuItem menuItem2 = menu.add("Settings");
        MenuItem menuItem3 = menu.add("Exit");

        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                showAboutDialog();
                return true;
            }
        });
        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
               startActivity(intent);
                return true;
            }
        });


        menuItem3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {

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