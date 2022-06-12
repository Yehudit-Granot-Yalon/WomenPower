package com.yehudit.powerwomen;;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TodayActivity extends AppCompatActivity {
    private DBHelper DB;

    private Boolean checkinsertdata;
    private int numThisDay;
    private TextView textToday;
    private  ImageView  enterVideo;
//for picture
    public static final int CAMERA_ACTION_CODE=1;
    private ImageView btnPhoto;
    private Bitmap bitmap;
    private TextView beforeOrAfterText;

    private String ifBeforeOrAfterString;
    final int CAMERA_REQUEST = 1;
    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    public void cameraListener(View v)
    {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_ACTION_CODE);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK) {
            String numCycleToDB="";
            String numCycle=DB.getValue(getString(R.string.weeklyCycle));
            numCycleToDB+=ifBeforeOrAfterString+numCycle;
            Log.d("numCycleToDB", numCycleToDB);
            bitmap = (Bitmap)data.getExtras().get("data");
            byte[] b=getBytes(bitmap);
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           // DBPicture.updateuserdata(numCycleToDB,b,sdf.format(calendar.getTime()));
        DB.updatePictureData(numCycleToDB,b,sdf.format(calendar.getTime()));
        }
    }
    public void goToShowVideo(int numVideo) {//func that fix video that go to be show
        String valVideoToFind="";
//        valVideoToFind+=kindVideo;
//        valVideoToFind+=timeVideo;
        valVideoToFind+=numVideo;
        Log.d("valVideoToFind", valVideoToFind);
        String videoID=DB.getValue(valVideoToFind);
        Log.d("videoplay", videoID);
        checkinsertdata = DB.updateuserdata(getString(R.string.VIDEO), videoID);
        Intent intent = new Intent(TodayActivity.this, VideoActivity.class);//-------declare how father video
        intent.putExtra("numVideo",numVideo);
        startActivity(intent);


    }
    private String funcBeforeOrAfter(){
        if(numThisDay==1)
     return getString(R.string.BEFORE) ;
        if(numThisDay==7)
            return getString(R.string.AFTER) ;
        return getString(R.string.NO);
    }
    public void getDataInThread() {
        new Thread(new Runnable() {
            public void run() {
                String s=DB.getValue(getString(R.string.DAY));
               // Bitmap bitmap = loadImageFromNetwork("http://site.com/image.png");
                runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("rum", numThisDay+"");
                        numThisDay=Integer.parseInt(s);
                        textToday.setText(DB.getValue(getString(R.string.DAY)+numThisDay));
                    }
                });
            }
        }).start();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        DB = new DBHelper(this);
        enterVideo = findViewById(R.id.enterVideo);
        beforeOrAfterText=findViewById(R.id.textPicture);
        btnPhoto=findViewById(R.id.imageButton);
        //getDataInThread();
        String s=DB.getValue(getString(R.string.DAY));
        Log.d("valindbtoday", "onCreate: ");
       numThisDay=Integer.parseInt(s);

       enterVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("numThisDay", ""+numThisDay);
                //goToShowVideo( numThisDay);
                Intent intent = new Intent(TodayActivity.this, VideoActivity.class);//-------declare how father video
                intent.putExtra("numVideo",numThisDay);
                startActivity(intent);
            }
        });


        ifBeforeOrAfterString=funcBeforeOrAfter();
        beforeOrAfterText.setText( " click for: "+ ifBeforeOrAfterString+" exercise ");
        if(numThisDay!=1&&numThisDay!=7){
          beforeOrAfterText.setVisibility(View.GONE);
          btnPhoto.setVisibility(View.GONE);
             }

    }
}
