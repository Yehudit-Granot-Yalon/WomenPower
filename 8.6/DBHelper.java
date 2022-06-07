package com.yehudit.powerwomen;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "DB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(myKey TEXT primary key, myValue TEXT)");
        DB.execSQL("create Table PictureDetails(myKey TEXT primary key,KEY_IMAGE BLOB,DATE_IMAGE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists UserDetails");
        DB.execSQL("drop Table if exists PictureDetails");
    }

    public Boolean insertuserdata(String myKey, String myValue) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("myKey", myKey);
        contentValues.put("myValue", myValue);

        long result = DB.insert("UserDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertPictureData(String myKey, byte[] image, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("myKey", myKey);

        contentValues.put("KEY_IMAGE", image);
        contentValues.put("DATE_IMAGE", date);
        long result = DB.insert("PictureDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateuserdata(String myKey, String myValue) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("myValue", myValue);
        Cursor cursor = DB.rawQuery("Select * from UserDetails where myKey = ?", new String[]{myKey});
        if (cursor.getCount() > 0) {
            long result = DB.update("UserDetails", contentValues, "myKey=?", new String[]{myKey});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean updatePictureData(String myKey, byte[] image, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("KEY_IMAGE", image);
        contentValues.put("DATE_IMAGE", date);
        Cursor cursor = DB.rawQuery("Select * from PictureDetails where myKey = ?", new String[]{myKey});
        if (cursor.getCount() > 0) {
            long result = DB.update("PictureDetails", contentValues, "myKey=?", new String[]{myKey});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String myKey) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetails where myKey = ?", new String[]{myKey});
        if (cursor.getCount() > 0) {
            long result = DB.delete("UserDetails", "myKey=?", new String[]{myKey});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetails", null);
        return cursor;
    }

    public String getValue(String myKey) {
        String value = "";
        SQLiteDatabase DB = this.getWritableDatabase();

       // new Thread(new Runnable() {
           // @Override
           // public void run() {

               // synchronized (this) {
                    // SQLiteDatabase DB = this.getWritableDatabase();

                    Cursor res = DB.rawQuery("Select * from UserDetails where myKey = ?", new String[]{myKey});


                    // String value = "";
////
                    res.moveToFirst();
                    value = res.getString(1);
                    Log.d("valindb", value);


                      // wait(500);
//                        Intent intent = new Intent
//                                (OpenActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
               // }
         //   }
       // }).start();

//        SQLiteDatabase DB = this.getWritableDatabase();
//
//        Cursor res = DB.rawQuery("Select * from UserDetails where myKey = ?", new String[]{myKey});
//
//
//        String value = "";
//
//        res.moveToFirst();
//        value=res.getString(1);
//        Log.d("valindb", value);
//         return  value;

return value;
    }

    public byte[] getImageFromDB (String myKey )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("Select * from PictureDetails where myKey = ?", new String[]{myKey});
        byte[] value ;
        res.moveToFirst();
        value=res.getBlob(1);
        return  value;


    }
    public String getDatePicture(String myKey )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("Select * from PictureDetails where myKey = ?", new String[]{myKey});
        String value ;
        res.moveToFirst();
        value=res.getString(2);
        return  value;


    }
}