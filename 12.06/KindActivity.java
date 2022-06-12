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
    public void listenerKind(View v){
        String kind="1";
        switch (v.getId())
        {
            case R.id.kind1ID:
                kind="1";
                break;

            case R.id.kind2ID:
                kind="2";
                break;

            case R.id.kind3ID:
                kind="3";
                break;
        }


        //checkInsertData= DB.updateuserdata(getString(R.string.KIND), kind);
        Intent intent = new Intent(KindActivity.this, TimeActivity.class);
        intent.putExtra("typeVideo",kind);
              startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);
        setTitle("TYPE OF EXERCISE");

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
