package com.yehuditg.womenpower;

import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
public class VideoActivity extends AppCompatActivity  {
    private YouTubePlayerView youTubePlayerView;
    private DBHelper DB;
    private String videoID;
    private String thisDayInWeek;
    private int numDay;
    private TextView textView;
    private String kindVideo;
    private String timeVideo;
    //To block calls that come in while exercising
    public BlockCallReceiver myReceiver;
    private IntentFilter filter;
    public String getIdVideo() {//func that fix video that go to be show
        String valVideoToFind="";
        valVideoToFind+=kindVideo;
        valVideoToFind+=timeVideo;
        valVideoToFind+=numDay;
        Log.d("valVideoToFind", valVideoToFind);
        String videoID=DB.getValue(valVideoToFind);
        Log.d("videoplay", videoID);
        return videoID;
    }

    private void setupBroadcastReceiver()//Running a broadcaster listens to an incoming call
    {
        Log.d("setupBroadcastReceiver", "setupBroadcastReceiver: ");
        myReceiver = new BlockCallReceiver();
        filter= new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);//Set up a filter that will listen if there is an incoming call
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setTitle("videoActivity");
        DB = new DBHelper(this);
        textView=findViewById(R.id.txvVideoID);
        kindVideo = DB.getValue(getString(R.string.KIND));
         timeVideo = DB.getValue(getString(R.string.TIME));
        numDay=getIntent().getIntExtra("numVideo",0);//Replacing the video number from a class that came from the previous activity
        videoID=getIdVideo();
        thisDayInWeek=DB.getValue(("DAY"+numDay));
        textView.setText(thisDayInWeek);
        youTubePlayerView=findViewById(R.id.activity_main_youtubePlayerWiew);
        getLifecycle().addObserver(youTubePlayerView);//A listener waiting to play the video with the video ID
        youTubePlayerView.enterFullScreen();
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoID, 0);//Loads the link and plays the video
            }
        });





    }

    @Override
    protected void onStart()
    {
        super.onStart();
        setupBroadcastReceiver();//For call rejection - listens for incoming calls
        registerReceiver(myReceiver, filter);//Activates the broadcast at the entrance to the activity
        Log.d("mylogVideoActivity", ">>> onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("mylogVideoActivity", ">>> onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("mylogVideoActivity", ">>> onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("mylogVideoActivity", "onStop()");
        //Turns off the broadcast when exiting the activity
        unregisterReceiver(myReceiver);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("mylogVideoActivity", ">>> onDestroy()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("mylogVideoActivity", ">>> onRestart()");
    }


}
