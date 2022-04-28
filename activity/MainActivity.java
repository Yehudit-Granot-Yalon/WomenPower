package com.ilanp.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Lesson2 + Lesson3 - First Android App!
 * @autor Ilan Peretz | 17.2.2022
 */
public class MainActivity<string> extends AppCompatActivity
{
public String[] days;
public String NAME;
public DBHelper myDB;


    public String getValueFromDB(String valueToSearch){
        String value="";
        Cursor res = myDB.getdata();
      //  if(res.getCount()==0){
            //Toast.makeText(DbActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
          //  return;
      //  }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Name :"+res.getString(0)+"\n");
           if(res.getString(0).equals(valueToSearch))
               value=res.getString(1);
            buffer.append("Contact :"+res.getString(1)+"\n");
            buffer.append("Date of Birth :"+res.getString(2)+"\n\n");
        }

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(buffer.toString());
        builder.show();
        return value;
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DBHelper(this);
        Log.d("mylog", ">>> onCreate()");

        ImageView img = findViewById(R.id.imgID);
        img.setBackgroundColor(Color.MAGENTA);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View v)
            {
                Random rnd = new Random();
                int randomColor = rnd.nextInt();
                img.setBackgroundColor(randomColor);
            }
        });
        Button btn5 = findViewById(R.id.btn5ID);
        btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                EditText edt = findViewById(R.id.edtID);
                NAME= edt.getText().toString();
                if (NAME == null || (NAME != null && NAME.isEmpty()))
                Toast.makeText(MainActivity.this, "Name must be NOT Empty!", Toast.LENGTH_LONG).show();
                  else
                {
                    TextView txv = findViewById(R.id.txvNameID);
                 txv.setText("hello "+NAME);
                }
                Boolean checkinsertdata = myDB.insertuserdata("NAME", NAME, "");
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                String findNameInDB=getValueFromDB("NAME");
                TextView itxv = findViewById(R.id.txvTitleID);
                Toast.makeText(MainActivity.this,findNameInDB, Toast.LENGTH_LONG).show();
                itxv.setText(findNameInDB);
            }

        });
        Button btn1 = findViewById(R.id.btn1ID);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                //EditText edt = findViewById(R.id.edtID);
                //String name = edt.getText().toString();
                //if (name == null || (name != null && name.isEmpty()))
                    //Toast.makeText(MainActivity.this, "Name must be NOT Empty!", Toast.LENGTH_LONG).show();
              //  else
                //{
                   // TextView txv = findViewById(R.id.txvTitleID);
                   // txv.setText(name);
                //}
            }
        });

        Button btn2 = findViewById(R.id.btn2ID);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        Button btn4 = findViewById(R.id.btn4ID);
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, DbActivity.class);
                Log.d("HI", "onClick: ");
                startActivity(intent);
            }
        });

        Button btn3 = findViewById(R.id.btn3ID);

        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.v("PRINT", "hello");
                Calendar today = Calendar.getInstance();

                //Uri uriCalendar = Uri.parse("content://com.android.calendar/time/" + String.valueOf(today.getTimeInMillis()));
               // Intent intentCalendar = new Intent(Intent.ACTION_DATE_CHANGED,uriCalendar);
                //startActivity(intentCalendar);
                 days = new String[7];
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setFirstDayOfWeek(Calendar.SUNDAY);
                calendar1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                for (int i = 0; i < 7; i++) {
                    days[i] = format.format(calendar1.getTime());
                    calendar1.add(Calendar.DAY_OF_MONTH, 1);
                    Log.v("date", days[i]);

                   // btn3.setTextColor(Color.parseColor("#000000"));

                }
                btn3.setText(days[6]);
             //   requestPermissions(new String[] {Manifest.permission.CAMERA}, 0);
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