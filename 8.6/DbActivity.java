package com.yehudit.powerwomen;


import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import android.database.Cursor;
        import android.os.Bundle;
import android.util.Log;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
public class DbActivity extends AppCompatActivity {
    EditText myKey, myValue;
    Button insert, update, delete, view;
    DBHelper DB;
    public String getValueFromDB(String valueToSearch){
        Cursor res = DB.getdata();
        String value = "";
        while (res.moveToNext()) {
            Log.d("alueToSearch", res.getString(0));
            if (res.getString(0).equals(valueToSearch)) {
                value = res.getString(1);
                return value;
            }
        }
        return value;
    }
    public boolean checkKayInDB(String valueToSearch){
        Cursor res = DB.getdata();
        while (res.moveToNext()) {

            if (res.getString(0).equals(valueToSearch)) {
                Log.d("indb", "yes");
                return true;
            }
        }
        Log.d("indb", "no");
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        myKey = findViewById(R.id.myKey);
        myValue = findViewById(R.id.myValue);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myKeyTXT = myKey.getText().toString();
                String myValueTXT = myValue.getText().toString();
                Boolean checkinsertdata;

              if(checkKayInDB(myKeyTXT)==false) {
                  checkinsertdata = DB.insertuserdata(myKeyTXT, myValueTXT);
              }
               /* if(checkinsertdata==true) {
                    Toast.makeText(DbActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("dbactivity", "oinsert: ");
                }
                else
                    Toast.makeText(DbActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();*/
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myKeyTXT = myKey.getText().toString();
                String myValueTXT = myValue.getText().toString();


                Boolean checkupdatedata = DB.updateuserdata(myKeyTXT, myValueTXT);
                if(checkupdatedata==true)
                    Toast.makeText(DbActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DbActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myKeyTXT = myKey.getText().toString();
                Boolean checkudeletedata = DB.deletedata(myKeyTXT);
                if(checkudeletedata==true)
                    Toast.makeText(DbActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DbActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(DbActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("myKey :"+res.getString(0)+"\n");
                    buffer.append("myValue :"+res.getString(1)+"\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(DbActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}
