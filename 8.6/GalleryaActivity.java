package com.yehudit.powerwomen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class GalleryaActivity extends AppCompatActivity {
   // private TextView textViewChoose;
  //  private EditText editText;
    public int weeklCycle;
    private ImageView imageViewBefore;
    private ImageView imageViewAfter;
    public DBHelper DB;
    private TextView viewNumCycle;
    private TextView dateBefore;
    private TextView dateAfter;
    private NumberPicker numberPicker;
    private int chooseCycleToShow;
    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerya);
        //DBPicture = new DBHelperPicture(this);
        DB = new DBHelper(this);
        viewNumCycle=findViewById(R.id.numberCycle);
        numberPicker=findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        int maxCycle=Integer.parseInt(DB.getValue(getString(R.string.weeklyCycle)));
        Log.d("maxCycle", ""+maxCycle);
        numberPicker.setMaxValue(maxCycle);
        dateBefore=findViewById(R.id.dateBefore);
        dateAfter=findViewById(R.id.dateAfter);
        imageViewBefore=findViewById(R.id.imageViewBefore);
        imageViewAfter=findViewById(R.id.imageViewAfter);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.wpower);
        imageViewBefore.setImageBitmap(bitmap);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(oldVal==0){
                    numberPicker.setMinValue(1);
                }

                viewNumCycle.setText(String.format("photos of weekly cycle: %s",newVal));
                chooseCycleToShow=newVal;
                buttonChoose();
            }
        });
        //  editText = findViewById(R.id.editTextGallerya);
       // textViewChoose = findViewById(R.id.textGallerye);
       // imageViewBefore = findViewById(R.id.imageView1);

    }
    public void buttonChoose() {
        String findInDBBefore=getString(R.string.BEFORE);
        String findInDBAfter=getString(R.string.AFTER);
        findInDBBefore+=chooseCycleToShow;
        Log.d("findInDBBefore", findInDBBefore);
       findInDBAfter+=chooseCycleToShow;
        Log.d("findInDBBefore", findInDBAfter);
       // byte[] image =DBPicture.getImage(findInDBBefore);//################
        byte[] image =DB.getImageFromDB(findInDBBefore);
        Bitmap photoBefore= getImage(image);
        imageViewBefore.setImageBitmap(photoBefore);
        dateBefore.setText(DB.getDatePicture(findInDBBefore));
        //dateBefore.setText(DBPicture.getDate(findInDBBefore));
        //image =DBPicture.getImage(findInDBAfter);
        image =DB.getImageFromDB(findInDBAfter);
        Bitmap photoAfter= getImage (image);
        imageViewAfter.setImageBitmap(photoAfter);
         dateAfter.setText(DB.getDatePicture(findInDBAfter));

   }
}
