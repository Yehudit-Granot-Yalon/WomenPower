package com.yehuditg.womenpower;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
public class TodayActivity extends AppCompatActivity {
    private HelpFunctions helpFunctions;
    private int numThisDay;
    private ImageButton enterVideo;
    private Switch switchDo;
//for picture
    private ImageView btnPhoto;
    private Bitmap bitmap;
    private TextView beforeOrAfterText;
    private String ifBeforeOrAfterString;
    public void cameraListener(View v)//A listener function that transmits to the show of the camera
    {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        someActivityResultLauncher.launch(cameraIntent);
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(//A camera listener function that captures and inserts an image
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String numCycleToDB="";
                        String numCycle=helpFunctions.DB.getValue(getString(R.string.weeklyCycle));
                        numCycleToDB+=ifBeforeOrAfterString+numCycle;
                        bitmap = (Bitmap)data.getExtras().get("data");
                        byte[] b=helpFunctions.getBytes(bitmap);// convert from bitmap to byte array
                        Calendar calendar = new GregorianCalendar();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        helpFunctions.DB.updatePictureData(numCycleToDB,b,sdf.format(calendar.getTime()));//Updates the image according to where it should be before or after


                    }
                }
            });






    private String funcBeforeOrAfter(){//Function checks if today is the first or last day to give a photo option
        if(numThisDay==1)
     return getString(R.string.BEFORE) ;
        if(numThisDay==7)
            return getString(R.string.AFTER) ;
        return getString(R.string.NO);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        helpFunctions=new HelpFunctions(this);
        enterVideo = findViewById(R.id.enterVideo);
        beforeOrAfterText=findViewById(R.id.textPicture);
        btnPhoto=findViewById(R.id.imageButton);
        String s=helpFunctions.DB.getValue(getString(R.string.DAY));
        numThisDay=Integer.parseInt(s);//Check what day is in the cycle of the weekly exercise
        enterVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayActivity.this, VideoActivity.class);//-------declare how father video

                intent.putExtra("numVideo",numThisDay);//Know the class that presents the video some day today
                startActivity(intent);
            }
        });
        switchDo=findViewById(R.id.switchVideo);
        switchDo.setText("do?");
        String findInDB="DAY"+numThisDay+"_DO";
        if(helpFunctions.DB.getValue(findInDB).equals(getString(R.string.YES))) {//For the status quo that already existed after leaving
            switchDo.setChecked(true);
        }
        switchDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String findInDB="DAY"+numThisDay+"_DO";
                if (switchDo.isChecked()) {
                    helpFunctions.DB.updateuserdata(findInDB, switchDo.getTextOn().toString());
                }
                else {
                    helpFunctions.DB.updateuserdata(findInDB, switchDo.getTextOff().toString());
                }
            }
        });

        ifBeforeOrAfterString=funcBeforeOrAfter();//Know whether to turn on the camera on the first or last day
        beforeOrAfterText.setText( " click for: "+ ifBeforeOrAfterString+" exercise ");
        if(numThisDay!=1&&numThisDay!=7){//The camera will only operate on the first and last day
          beforeOrAfterText.setVisibility(View.GONE);
          btnPhoto.setVisibility(View.GONE);
             }

    }
}
