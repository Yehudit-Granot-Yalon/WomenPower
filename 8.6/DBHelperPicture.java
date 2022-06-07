package com.yehudit.powerwomen;

//public class DBHelperPicture {
//}


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import androidx.annotation.Nullable;

public class DBHelperPicture extends SQLiteOpenHelper {

    public DBHelperPicture(Context context) {
        super(context, "pictureData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        // DB.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, dob TEXT)");
        //DB.execSQL("create Table Userdetails(myKey TEXT primary key, phone TEXT,address TEXT, email TEXT,birthdayDate TEXT,timeToCall TEXT, KEY_IMAGE BLOB )");
        DB.execSQL("create Table Userdetails(myKey TEXT primary key,KEY_IMAGE BLOB,DATE_IMAGE TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }

   // public Boolean insertuserdata(String myKey, String phone,String address,String email,String birthdayDate,String timeToCall, byte[] image)
   public Boolean insertuserdata(String myKey,  byte[] image,String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("myKey", myKey);

        contentValues.put("KEY_IMAGE",image);
        contentValues.put("DATE_IMAGE",date);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String myKey,byte[] image,String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("KEY_IMAGE", image);
        contentValues.put("DATE_IMAGE", date);
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

    public byte[] getImage (String myKey )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("Select * from Userdetails where myKey = ?", new String[]{myKey});
        byte[] value ;
        res.moveToFirst();
        value=res.getBlob(1);
        return  value;


    }
    public String getDate (String myKey )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor res = DB.rawQuery("Select * from Userdetails where myKey = ?", new String[]{myKey});
        String value ;
        res.moveToFirst();
        value=res.getString(2);
        return  value;


    }
}