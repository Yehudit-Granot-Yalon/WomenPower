package com.yehudit.powerwomen;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class GalleryActivity extends AppCompatActivity {

    private EditText editTextFileName;
    private TextView textViewStatus;
    private ImageView imageView;
    private DBPicture sqliteDBHandler;
    private SQLiteDatabase sqLiteDatabase;

    //https://stackoverflow.com/questions/16954109/reduce-the-size-of-a-bitmap-to-a-specified-size-in-android
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        editTextFileName = findViewById(R.id.editTextTextFileName);
        textViewStatus = findViewById(R.id.textViewStatus);
        imageView = findViewById(R.id.imageView);

        try {
            sqliteDBHandler = new DBPicture(this, "ImageDatabase", null, 1);
            sqLiteDatabase = sqliteDBHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE ImageTable(Name TEXT, Image BLOB)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buttonInsert(View view) {
        //  String stringFilePath = Environment.getExternalStorageDirectory().getPath()+"/Download/"+editTextFileName.getText().toString()+".jpeg";
        String stringFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/myCamera/" + editTextFileName.getText().toString() + ".jpeg";
        Log.d("stringFilePath", stringFilePath);

        Bitmap bitmap = BitmapFactory.decodeFile(stringFilePath);
        Bitmap smallBitmap = getResizedBitmap(bitmap, 2000);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        smallBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytesImage = byteArrayOutputStream.toByteArray();
        long lengthbmp = bytesImage.length;
        Log.d("bytesImage", "" + lengthbmp);
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", "MyImage");
        contentValues.put("Image", bytesImage);
        sqLiteDatabase.insert("ImageTable", null, contentValues);
        textViewStatus.setText("Insert Successful");
    }

    public void buttonUpdate(View view) {
        String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/" + editTextFileName.getText().toString() + ".jpeg";
        Bitmap bitmap = BitmapFactory.decodeFile(stringFilePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte[] bytesImage = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", "MyImage");
        contentValues.put("Image", bytesImage);

        sqLiteDatabase.update("ImageTable", contentValues, null, null);
        textViewStatus.setText("Update Successful");
    }

    public void buttonDelete(View view) {
        if (sqLiteDatabase.delete("ImageTable", "Name=\"MyImage\"", null) > 0) {
            textViewStatus.setText("Deletion successful");
        }
    }

    public void buttonFetch(View view) {
       // String stringQuery = "Select Image from ImageTable where Name=\"MyImage\"";
        String stringQuery = "Select * from ImageTable";
        Cursor cursor = sqLiteDatabase.rawQuery(stringQuery, null);
       int i=0;
        //while (i<2){
            i++;
        while (cursor.moveToNext()) {
            try {
               //cursor.moveToNext();
                byte[] bytesImage = cursor.getBlob(1);
                Log.d("gallery0", "buttonFetch: ");
                cursor.close();
                Log.d("gallery1", "buttonFetch: ");
                // Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0,NullPointerException);
                Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
                Log.d("gallery2", "buttonFetch: ");
                imageView.setImageBitmap(bitmapImage);
                Log.d("gallery3", "buttonFetch: ");
                textViewStatus.setText("Fetch Successful");
            } catch (Exception e) {
                textViewStatus.setText("ERROR");
            }
        }
    }
}