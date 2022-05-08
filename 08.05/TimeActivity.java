package com.ilanp.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TimeActivity extends AppCompatActivity
{
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {DBHelper DB;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        setTitle("TimeActivity");
        DB = new DBHelper(this);
        Button btn1 = findViewById(R.id.btnSecond1ID);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ;
                Boolean checkinsertdata = DB.updateuserdata(getString(R.string.TIME), "10");

                Intent intent = new Intent(TimeActivity.this, VideoActivity.class);
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

}