package com.yehudit.powerwomen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;
import java.net.URL;
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
    public int countBackgroundPicture;
    public int flagBackgroundThread;
    public ImageView imageBackground;
    public Bitmap bitmapBackground;
    public static String urls[];
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
            checkinsertdata = DB.insertuserdata(getString(R.string.KIND), "");
            checkinsertdata = DB.insertuserdata(getString(R.string.weeklyCycle), "0");
            checkinsertdata = DB.insertuserdata(getString(R.string.TIME_REMINDER), "-1");
            checkinsertdata = DB.insertuserdata(getString(R.string.TIME), "-1");
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
            checkinsertdata = DB.insertuserdata("314", "Y_rwY8LCFMc");
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
    private void startThread(){
        new Thread(new Runnable() {

            @Override

            public void run() {
              //  Log.d("url","Thread");
                flagBackgroundThread=1;
               // String url1="https://www.yo-yoo.co.il/coolpics/images/uploads/03bc72.jpg";
               // String url2="https://drive.google.com/file/d/1REVZ0K8RAtfiUzElv6NPAHGE5uGZ4UCM/view?usp=sharing";
                // countBackgroundPicture=0;
                //int place;
                while (  flagBackgroundThread==1) {
                    countBackgroundPicture++;
                    if(countBackgroundPicture==24)
                        countBackgroundPicture=1;
                    try {
                        synchronized (this) {
                            wait(500);
                            Log.d("run", "run: ");
                            String url= "https://west-wind.com/wconnect/temp/t"+countBackgroundPicture+".jpeg";
                            Log.d("url", countBackgroundPicture+": "+url);
                           // String u="https://west-wind.com/wconnect/temp/webcam-1654816641050.jpg";
                            //if(count%2==0) {
                            // bitmapBackground = loadImageFromNetwork("https://upload.wikimedia.org/wikipedia/commons/7/7a/Basketball.png");
                            bitmapBackground = loadImageFromNetwork(url);
//                          place=count%10;
//                            Log.d("run: ",place+"" );
//                            bitmapBackground = loadImageFromNetwork(urls[place]);
                            // }

                            runOnUiThread(new Runnable()
                            {
                                public void run() {
                                    // Toast.makeText(DbActivity.this, "image", Toast.LENGTH_SHORT).show();
                                    imageBackground.setImageBitmap( bitmapBackground);;
                                }
                            });

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
    private Bitmap loadImageFromNetwork(String url) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
        urls=new String[10];

        resetDB();
        checkStartWeek();
        imageBackground=findViewById(R.id.imageWelcome);
       // flagBackgroundThread=1;
//startThread();

        imageBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, DbActivity.class);
                 startActivity(intent);

            }
        });







        img1 = findViewById(R.id.imageNewProg);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedItem[] = new int[1];
               // int flagStart=1;
                if (DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.YES))) {

                    AlertDialog.Builder exitDialog = new AlertDialog.Builder(MainActivity.this);
                   // exitDialog.setIcon(R.drawable.ic_exit);
                    exitDialog.setMessage("There is a weekly exercise cycle, should you start a new cycle?");
                    exitDialog.setCancelable(false);
                    exitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            //finish(); // destroy & exit this activity
                            Intent intent = new Intent(MainActivity.this, KindActivity.class);
                           startActivity(intent);
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
                  //  startThread();
//                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
//                    alertDialog.setTitle("There is a weekly exercise cycle, should you start a new cycle?");
//                    final String[] listItems = new String[]{"NO", "YES"};
//                    alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
//                        @SuppressLint("SetTextI18n")
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            checkedItem[0] = which;
//                        }
//                    });
//                    alertDialog.setNegativeButton("FINISH", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            if (checkedItem[0] == 0) {
//                               // Toast.makeText(MainActivity.this, "10 min", Toast.LENGTH_SHORT).show();
//                               // DB.updateuserdata(getString(R.string.TIME), "1");
//
//
//
//                            }
//                            if (checkedItem[0] == 1) {
//                               // Toast.makeText(MainActivity.this, "20 min", Toast.LENGTH_SHORT).show();
//                                //deletContact(contact.getPhone());
//                                //B.updateuserdata(getString(R.string.TIME), "2");
//                               // flagStart=1;
//                                Intent intent = new Intent(MainActivity.this, KindActivity.class);
//                                startActivity(intent);
//                            }
//                        }
//
//                    });
//
//                    androidx.appcompat.app.AlertDialog customAlertDialog = alertDialog.create();
//                    customAlertDialog.show();
                }
                  else {
                    Intent intent = new Intent(MainActivity.this, KindActivity.class);
                    startActivity(intent);
                }
            }
        });
        img2 = findViewById(R.id.imageToday);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          if(app_on==true||DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.YES))) {
               // if(app_on==true) {//If you already have the plan or have just set up a plan
                    Intent intent = new Intent(MainActivity.this, TodayActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Set up a weekly exercise program", Toast.LENGTH_LONG).show();
                }

            }
      });
        img3 = findViewById(R.id.imageWeek);
        if(app_on==true||DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.YES))) {

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
                if(app_on==true||DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.YES))) {
                    Intent intent = new Intent(MainActivity.this, DaysActivity.class);
                    Log.d("daily", "onClick: ");
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplicationContext(), "Set up a weekly exercise program", Toast.LENGTH_LONG).show();
                }


            }
        });
        img4= findViewById(R.id.imageGallery);

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DB.getValue(getString(R.string.weeklyCycle)).equals("0")) {
                    Toast.makeText(getApplicationContext(), "There are no weekly cycles", Toast.LENGTH_LONG).show();
                }
                  else {
                    Intent intent = new Intent(MainActivity.this, GalleryaActivity.class);
                    startActivity(intent);
                }
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
                if(app_on==true||DB.getValue(getString(R.string.APP_ON)).equals(getString(R.string.YES))) {

                    Intent intent = new Intent(MainActivity.this, CircleActivity.class);
                    startActivity(intent);


                }
                else {
                    Toast.makeText(getApplicationContext(), "Set up a weekly exercise program", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
       startThread();
        Log.d("mylogmMain", ">>> onStart()");
        Log.d("mylogmMain", ">>> onStart() flag:"+ flagBackgroundThread);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
      //  startThread();
       // flagBackgroundThread=1;
        Log.d("mylogMain", ">>> onResume()+flag:"+ flagBackgroundThread);

    }

    @Override
    protected void onPause()
    {
        Log.d("mylogMain", ">>> onPause()");
        super.onPause();
         flagBackgroundThread=0;


    }

    @Override
    protected void onStop()
    {
        Log.d("mylogMain", ">>> onStop()+flag"+ flagBackgroundThread);
        super.onStop();
        // flagBackgroundThread=0;

    }

    @Override
    protected void onDestroy()
    {
        Log.d("mylogMain", ">>> onDestroy()");
        super.onDestroy();


    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
       // startThread();
        Log.d("mylogMain", ">>> onRestart()");
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
        aboutDialog.setMessage("App: Women Power\nBy: Yehudit Granot\nDate: 18.6.2022\nAndroid 11.0 (R) API Level 30.");
        //26 API) Oreo (0.8 A
        //Android 11.0 (R) API Level 30

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