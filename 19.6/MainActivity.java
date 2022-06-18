package com.yehuditg.womenpower;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;



public class MainActivity extends AppCompatActivity {
    private HelpFunctions helpFunctions;
    private boolean app_on;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img6;
    public int countBackgroundPicture;
    public int flagBackgroundThread;
    public ImageView imageBackground;
    public Bitmap bitmapBackground;
    public static String urls[];
    public BlockCallReceiver myReceiver;

    //The method requests phone permissions if it does not exist
    private void askPermissions() {
        String[] PERMISSIONS;
        PERMISSIONS = new String[] {
                Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ANSWER_PHONE_CALLS
        };
        if (!hasPermissions(MainActivity.this,PERMISSIONS)) {
            ActivityCompat.requestPermissions(MainActivity.this,PERMISSIONS,1);
        }
    }
    //The method checks if permissions exist
    private boolean hasPermissions(Context context, String... PERMISSIONS) {

        if (context != null && PERMISSIONS != null) {

            for (String permission: PERMISSIONS){

                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }
//    private void askPermissions(){//A function that requests all the permissions needed at runtime
//        ActivityCompat.requestPermissions(MainActivity.this,
//                  new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.SYSTEM_ALERT_WINDOW},
//                    1);
//
//    }
    private void setUrls(){//Links to wallpapers running in the process

       // https://drive.google.com/uc?id=1EorBzXW5SintRjXcRLIwVeAOb4_4BkKr
      // urls[0]="https://drive.google.com/uc?id=11HEw9YGZ1YKk84nVSFx6ndhk1A4FD4lg";mydrive
       // urls[0]="https://drive.google.com/uc?id=1EorBzXW5SintRjXcRLIwVeAOb4_4BkKr";//cani
        //urls[0]="https://images.pexels.com/photos/2088205/pexels-photo-2088205.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940";
        //urls[1]="https://drive.google.com/uc?id=1p7pFaKCZCAu7_gsg2grpwEqrIz5q-I9g";
//        urls[1]="https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg";
//        urls[2]="https://drive.google.com/uc?id=1VdQB8iigs77ODy6SDQJGIhEXGT5ZWZKA";
//        urls[3]="https://drive.google.com/uc?id=1KnucYxAhuQzeyWMRBPdyZiSO5GjrM_V8";
//        urls[4]="https://drive.google.com/uc?id=1SYc2nU1B3PsuxxiSqMwtwB4p0vRIdWss";
        urls[0]="https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg";
        urls[1]="https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg";

        urls[2]="https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg";
        urls[3]="https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg";
        urls[4]="https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg";



    }
    private void resetDB(){//A function that initializes the database only the first time it is used in the application
             helpFunctions.DB.insertuserdata(getString(R.string.APP_ON), getString(R.string.NO));
             helpFunctions.DB.insertuserdata(getString(R.string.DAY), "");
             helpFunctions.DB.insertuserdata(getString(R.string.KIND), "");
             helpFunctions.DB.insertuserdata(getString(R.string.TIME), "-1");
             helpFunctions.DB.insertuserdata(getString(R.string.weeklyCycle), "0");
             helpFunctions.DB.insertuserdata(getString(R.string.TIME_REMINDER), "-1");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY1), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY2), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY3), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY4), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY5), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY6), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY7), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY1_DO), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY2_DO), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY3_DO), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY4_DO), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY5_DO), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY6_DO), "");
             helpFunctions.DB.insertuserdata(getString(R.string.DAY7_DO), "");
             helpFunctions.DB.insertuserdata("111", "aN_2L2vxKy0");
             helpFunctions.DB.insertuserdata("112", "nG69wuXHwxg");
             helpFunctions.DB.insertuserdata("113", "Jg61m0DwURs");
             helpFunctions.DB.insertuserdata("114", "zbt1g9WX6bA");
             helpFunctions.DB.insertuserdata("115", "FJA3R7n_594");
             helpFunctions.DB.insertuserdata("116", "uVwNVEQS_uo");
             helpFunctions.DB.insertuserdata("117", "R1EKAgFRe2E");
             helpFunctions.DB.insertuserdata("121", "7HIr1g39PzA");
             helpFunctions.DB.insertuserdata("122", "9hQTvrP6EsM");
             helpFunctions.DB.insertuserdata("123", "HeolReSa5ic");
             helpFunctions.DB.insertuserdata("124", "zZ8tWnE8kzQ");
             helpFunctions.DB.insertuserdata("125", "G_26U5DIaRg");
             helpFunctions.DB.insertuserdata("126", "X7BWNO_H8KY");
             helpFunctions.DB.insertuserdata("127", "Smim7-qG8Ls");
             helpFunctions.DB.insertuserdata("211", "1f8yoFFdkcY");
             helpFunctions.DB.insertuserdata("212", "AnYl6Nk9GOA");
             helpFunctions.DB.insertuserdata("213", "PlqWX_cv7bc");
             helpFunctions.DB.insertuserdata("214", "1919eTCoESo");
             helpFunctions.DB.insertuserdata("215", "QfMlHzgzBwQ");
             helpFunctions.DB.insertuserdata("216", "xZaSVMB2yy0");
             helpFunctions.DB.insertuserdata("217", "54x6yjnzLms");
             helpFunctions.DB.insertuserdata("221", "3oeimlA6s68");
             helpFunctions.DB.insertuserdata("222", "EGhERRQ3GP0");
             helpFunctions.DB.insertuserdata("223", "53B40Aw0k3g");
             helpFunctions.DB.insertuserdata("224", "EWjsnO-9BPU");
             helpFunctions.DB.insertuserdata("225", "H765nVNbUsA");
             helpFunctions.DB.insertuserdata("226", "0q7QQci6UhI");
             helpFunctions.DB.insertuserdata("227", "kl5AhFQtfvg");
             helpFunctions.DB.insertuserdata("311", "6VFLKdfxA24");
             helpFunctions.DB.insertuserdata("312", "FGFfqCjtmS8");
             helpFunctions.DB.insertuserdata("313", "j5SHMJ6mUoA");
             helpFunctions.DB.insertuserdata("314", "Y_rwY8LCFMc");
             helpFunctions.DB.insertuserdata("315", "agvdkRc_img");
             helpFunctions.DB.insertuserdata("316", "bqVBMv_nqJ4");
             helpFunctions.DB.insertuserdata("317", "-K2vxOQG0MQ");
             helpFunctions.DB.insertuserdata("321", "UItWltVZZmE");
             helpFunctions.DB.insertuserdata("322", "UBMk30rjy0o");
             helpFunctions.DB.insertuserdata("323", "IT94xC35u6k");
             helpFunctions.DB.insertuserdata("324", "Y2eOW7XYWxc");
             helpFunctions.DB.insertuserdata("325", "uyFjMupI5B0&t=259s");
             helpFunctions.DB.insertuserdata("326", "hI-JSz2Iv-k");
             helpFunctions.DB.insertuserdata("327", "uZbig5yMlN8");
        }



   private void checkStartWeek(){ // check if the week begin
        Log.d("checkStartWeek", "checkStartWeek: ");
        if(helpFunctions.checkAppOn()) {//If there is information that there is a weekly cycle - you should check if the date has passed
            if (helpFunctions.ifCycleWeekFinish() == true) {
                 helpFunctions.DB.updateuserdata(getString(R.string.APP_ON), getString(R.string.NO));//update DB the week end
                app_on=false;
            }
            else { app_on=true;}
        }
        else{
            app_on=false;
        }
    }
    private boolean checkKayInDB(String valueToSearch){//To check if the DB is already initialized if a cycle of exercise has already been defined
        Cursor res = helpFunctions.DB.getdata();
        while (res.moveToNext()) {
            if (res.getString(0).equals(valueToSearch)) {
                return true;
            }
        }
        return false;
    }


    private void startThread(){//A process for running background images
        new Thread(new Runnable() {
            @Override
            public void run() {
                flagBackgroundThread=1;
                while (  flagBackgroundThread==1) {
                    countBackgroundPicture++;
                    if(countBackgroundPicture==5)
                        countBackgroundPicture=0;
                    try {
                        synchronized (this) {
                            wait(5000);
                            Log.d("run", "run: ");
                           // https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg

                            //String url= urls[countBackgroundPicture];
                           //String url="https://upload.wikimedia.org/wikipedia/commons/e/ef/Aramis_carlin.jpg";
                           String url="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/2007_Nagaoka_Festival_004_Edit.jpg/1280px-2007_Nagaoka_Festival_004_Edit.jpg";
                            bitmapBackground = loadImageFromNetwork(url);
                            runOnUiThread(new Runnable()
                            {
                                public void run() {
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
    private Bitmap loadImageFromNetwork(String url) {//Downloads the image from the Internet
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {//
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {


            }
        }
    }
    public void  drawActivity() {//A function that adds the buttons to the screen as needed if the program is set up
        Log.d("drawActivity", "drawActivity: ");
        if(app_on){//Show buttons that a program has been set
            //img3 = findViewById(R.id.imageWeek);
           // img2 = findViewById(R.id.imageToday);

        }
        if(!app_on) {//Do not display buttons yet because no program has been set
            Log.d("drawActivity", "! app on");
        //if(app_on==false) {
            //btn7.setTextColor(getColor(R.color.pink));
            //btnDays.setText("nooo");
            img2.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
//getColor(R.color.pink)
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helpFunctions= new HelpFunctions(this);
        helpFunctions.DB = new DBHelper(this);
        urls=new String[5];
        setUrls();
        if(checkKayInDB(getString(R.string.APP_ON))==false) {//application not started yet
            resetDB();
        }
        //AskPermissions();
        askPermissions();
        imageBackground=findViewById(R.id.imageWelcome);
        imageBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, DbActivity.class);
                 startActivity(intent);

            }
        });
        img1 = findViewById(R.id.imageNewProg);
        img1.setOnClickListener(new View.OnClickListener() {//Listens to click on enter a new weekly program
            @Override
            public void onClick(View v) {
                int checkedItem[] = new int[1];
                if (app_on) {
                    AlertDialog.Builder exitDialog = new AlertDialog.Builder(MainActivity.this);
                    exitDialog.setMessage("There is a weekly exercise cycle, should you start a new cycle?");
                    exitDialog.setCancelable(false);
                    exitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
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
                }
                  else {

                    Intent intent = new Intent(MainActivity.this, KindActivity.class);
                    startActivity(intent);
                }
            }
        });
        img2 = findViewById(R.id.imageToday);
        img2.setOnClickListener(new View.OnClickListener() {//Listens to click on enter the daily movie
            @Override
            public void onClick(View v) {
                if(app_on) {//If you already have the plan or have just set up a plan
                    Intent intent = new Intent(MainActivity.this, TodayActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Set up a weekly exercise program", Toast.LENGTH_LONG).show();
                }

            }
      });
        img3 = findViewById(R.id.imageWeek);
        img3.setOnClickListener(new View.OnClickListener() {//Listener clicks on entry to all weekly movies
            @Override
            public void onClick(View v) {
                if(app_on) {
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

        img4.setOnClickListener(new View.OnClickListener() {//Listener clicks on the gallery
            @Override
            public void onClick(View v) {
                if(helpFunctions.DB.getValue(getString(R.string.weeklyCycle)).equals("0")) {//There are no pictures to show yet
                    Toast.makeText(getApplicationContext(), "NO PICTURES IN GALLERY", Toast.LENGTH_LONG).show();
                }
                  else {
                    Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                    startActivity(intent);
                }
            }
        });



        img6= findViewById(R.id.imageInfo);
        img6.setOnClickListener(new View.OnClickListener() {//Listens to click on the information on the progress of the weekly cycle
            @Override
            public void onClick(View v) {
                if(app_on) {
                    Intent intent = new Intent(MainActivity.this, CircleActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "NO EXERCISE PROGRAM", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        checkStartWeek();//Checks if a new program has already been configured
        startThread();//Begins the process of loading the images in the background
       drawActivity();//A function that adds the buttons to the screen as needed if the program is set up
        Log.d("mylogmMain", ">>> onStart()");
        Log.d("mylogmMain", ">>> onStart() flag:"+ flagBackgroundThread);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("mylogMain", ">>> onResume()");

    }

    @Override
    protected void onPause()
    {
        Log.d("mylogMain", ">>> onPause()");
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        Log.d("mylogMain", ">>> onStop()+flag"+ flagBackgroundThread);
        super.onStop();
        flagBackgroundThread=0;//Stops the process of loading the images in the background

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
        Log.d("mylogMain", ">>> onRestart()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)//3-point menu
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