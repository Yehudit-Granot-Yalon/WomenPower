package com.yehuditg.womenpower;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class GalleryActivity extends AppCompatActivity {
    private ImageView imageViewBefore;
    private ImageView imageViewAfter;
    private DBHelper DB;
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
        setTitle("GALLERY");
        DB = new DBHelper(this);
        viewNumCycle=findViewById(R.id.numberCycle);
        numberPicker=findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);//0
        int maxCycle=Integer.parseInt(DB.getValue(getString(R.string.weeklyCycle)));
        numberPicker.setMaxValue(maxCycle);
        dateBefore=findViewById(R.id.dateBefore);
        dateAfter=findViewById(R.id.dateAfter);
        imageViewBefore=findViewById(R.id.imageViewBefore);
        imageViewAfter=findViewById(R.id.imageViewAfter);
        viewNumCycle.setText(String.format(" photos of weekly cycle: %s ",1));//in the start before choice num
        chooseCycleToShow=1;
        buttonChoose();
        //Listener function for the cycle number selected by the user
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                viewNumCycle.setText(String.format(" photos of weekly cycle: %s ",newVal));
                chooseCycleToShow=newVal;
                buttonChoose();
            }
        });

    }
    public void buttonChoose() {//Displays the images before and after the user selects
        String findInDBBefore=getString(R.string.BEFORE);
        String findInDBAfter=getString(R.string.AFTER);
        findInDBBefore+=chooseCycleToShow;
        Log.d("findInDBBefore", findInDBBefore);
       findInDBAfter+=chooseCycleToShow;
        Log.d("findInDBBefore", findInDBAfter);
        byte[] image =DB.getImageFromDB(findInDBBefore);
        Bitmap photoBefore= getImage(image);
        imageViewBefore.setImageBitmap(photoBefore);
        dateBefore.setText(DB.getDatePicture(findInDBBefore));
        image =DB.getImageFromDB(findInDBAfter);
        Bitmap photoAfter= getImage (image);
        imageViewAfter.setImageBitmap(photoAfter);
         dateAfter.setText(DB.getDatePicture(findInDBAfter));

   }
}
