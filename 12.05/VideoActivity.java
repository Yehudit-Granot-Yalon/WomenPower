package com.ilanp.firstapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

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
    DBHelper DB;

    public String videoID;
    public String videoToFind;
    public String[] days;
    public String thisDayInWeek;
    public String getValueFromDB(String valueToSearch) {

        String value = "";
        Cursor res = DB.getdata();
        while (res.moveToNext()) {
            Log.d("videokey", res.getString(0));

            Log.d("videosearch", valueToSearch);
            if (res.getString(0).equals(valueToSearch))
                value = res.getString(1);
        }
        Log.d("return", value);

        return value;
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setTitle("videoActivity");
      days = new String[7];
        DB = new DBHelper(this);




        videoID=getValueFromDB(getString(R.string.VIDEO));

        Log.d("videoplay", videoID);

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
