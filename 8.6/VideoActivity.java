package com.yehudit.powerwomen;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class VideoActivity extends AppCompatActivity  {
    private YouTubePlayerView youTubePlayerView;
    private DBHelper DB;
    private String videoID;
    private String videoToFind;
    private String thisDayInWeek;
    private Switch switchDo;
    private int numDay;
    private TextView textView;
    private boolean checkInsertData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setTitle("videoActivity");
        DB = new DBHelper(this);
        numDay=getIntent().getIntExtra("numVideo",0);
        textView=findViewById(R.id.txvVideoID);
        switchDo=findViewById(R.id.switchVideo);
        switchDo.setText("do"+numDay+" ?");//??????
        videoID=DB.getValue(getString(R.string.VIDEO));
        thisDayInWeek=DB.getValue(("DAY"+numDay));
        textView.setText(thisDayInWeek);
        String findInDB="DAY"+numDay+"_DO";
        if(DB.getValue(findInDB).equals(getString(R.string.YES))) {//For the status quo that already existed after leaving
            Log.d("inforonfind", findInDB);
            switchDo.setChecked(true);
        }
        switchDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("videoActivity", "onClick: ");
                String findInDB="DAY"+numDay+"_DO";
                if (switchDo.isChecked()) {
                    checkInsertData = DB.updateuserdata(findInDB, switchDo.getTextOn().toString());
                    Log.d("videoActivity", ""+checkInsertData);
                }
                else {
                    checkInsertData = DB.updateuserdata(findInDB, switchDo.getTextOff().toString());
                }
            }
        });

        youTubePlayerView=findViewById(R.id.activity_main_youtubePlayerWiew);
                getLifecycle().addObserver(youTubePlayerView);
                youTubePlayerView.enterFullScreen();

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
               // String videoId = v;
                youTubePlayer.loadVideo(videoID, 0);
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
