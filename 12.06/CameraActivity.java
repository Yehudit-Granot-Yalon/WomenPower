package com.yehudit.powerwomen;//https://programmerworld.co/android/how-to-create-custom-camera-app-to-take-pictures-in-android-phone/

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CameraActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE =1 ;
    public static final int CAMERA_ACTION_CODE=1;
    DBHelper DB;
    DBHelperPicture DBPicture;
    Button btnPhoto;
    Bitmap bitmap;
    ImageView imageView;

    //String dateToday;
    boolean beforeWeek;
   boolean afterWeek;
    final int CAMERA_REQUEST = 1;
    //Check if this is the last or first day of the weekly cycle
    public boolean checkTodayDate(){
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-dd-MM-yyyy");
        String dateToday=sdf.format(calendar.getTime());
        String dayStart=DB.getValue(getString(R.string.DAY1));
        String dayEnd=DB.getValue(getString(R.string.DAY7));
        //dateToday=DB.getValue(getString(R.string.DAY7
       // ));
        if(dateToday.equals(dayStart)) {
            beforeWeek = true;
            return true;
        }
        if(dateToday.equals(dayEnd)) {
            afterWeek = true;
            return true;
        }
        return false;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK) {
            String numCycleToDB="";
            String numCycle=DB.getValue(getString(R.string.weeklyCycle));
            if(beforeWeek) {
                numCycleToDB = getString(R.string.BEFORE);
                numCycleToDB+=numCycle;
                Log.d("numCycle", numCycleToDB);
            }
            if(afterWeek) {
                numCycleToDB = getString(R.string.AFTER);
                numCycleToDB+=numCycle;
                Log.d("numCycle", numCycleToDB);
            }
            bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            byte[] b=getBytes(bitmap);
           // DBPicture.updateuserdata(numCycleToDB,b);

            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DBPicture.updateuserdata(numCycleToDB,b,sdf.format(calendar.getTime()));
        }
    }
    public void cameraListener(View v)
    {

        if(v.getId()==R.id.btnCameraBefore) {//for 10 minitue
            beforeWeek = true;
            afterWeek = false;
        }
        if(v.getId()==R.id.btnCameraAfter) {//for 20 minitue
            afterWeek = true;
            beforeWeek=false;
        }
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_ACTION_CODE);

    }
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DB = new DBHelper(this);
      //  if (checkTodayDate()) {
            setContentView(R.layout.activity_camera);
            DBPicture = new DBHelperPicture(this);
            imageView = findViewById(R.id.imageViewCamera);
//
            ActivityCompat.requestPermissions(this, new String[]{CAMERA, WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
//        }
//        else {
//            Toast.makeText(this,"cant take picture",Toast.LENGTH_SHORT).show();
//        }

    }


}