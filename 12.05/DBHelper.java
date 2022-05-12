package com.ilanp.firstapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        // DB.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, dob TEXT)");
        DB.execSQL("create Table Userdetails(myKey TEXT primary key, myValue TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String myKey, String myValue)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("myKey", myKey);
        contentValues.put("myValue", myValue);

        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String myKey, String myValue)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("myValue", myValue);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where myKey = ?", new String[]{myKey});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "myKey=?", new String[]{myKey});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String myKey )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where myKey = ?", new String[]{myKey});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "myKey=?", new String[]{myKey});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}