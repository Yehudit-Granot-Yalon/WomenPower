package com.yehudit.powerwomen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KindActivity extends AppCompatActivity {
    private DBHelper DB;
//    private ImageView img1;
//    private ImageView img2;
//    private ImageView img3;
    private Boolean checkInsertData;
    public void listenerHello(View v) {
        Log.d("listenerHello", "listenerHello: ");
        //Toast.makeText(this, "hello lidtener", Toast.LENGTH_SHORT).show();
    }

    public void listenerKind(View v){
        String kind="1";
        if(v.getId()==findViewById(R.id.kind1ID).getId()){
           kind="1";
        }
        if(v.getId()==findViewById(R.id.kind2ID).getId()){
            kind="2";
        }
        if(v.getId()==findViewById(R.id.kind3ID).getId()){
            kind="3";

        }
        checkInsertData= DB.updateuserdata(getString(R.string.KIND), kind);
        Intent intent = new Intent(KindActivity.this, TimeActivity.class);
              startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);
        setTitle("Type Of Exercise");
        DB = new DBHelper(this);
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
