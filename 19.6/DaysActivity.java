package com.yehuditg.womenpower;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
public class DaysActivity extends AppCompatActivity {
    private DBHelper DB;
    private Switch switches[];
    private Button buttons[];
    private Boolean checkInsertData;

    public void listenerSwitches(View v) {//A function that listens to all the switch and updates the database accordingly
        int num = 0;
        switch (v.getId()) {
            case R.id.switch1:
                num = 1;
                break;

            case R.id.switch2:
                num = 2;
                break;

            case R.id.switch3:
                num = 3;
                break;

            case R.id.switch4:
                num = 4;
                break;
            case R.id.switch5:
                num = 5;
                break;
            case R.id.switch6:
                num = 6;
                break;
            case R.id.switch7:
                num = 7;
                break;
        }

        String findInDB="";
       int placeSwitch=num-1;

        findInDB="DAY"+num+"_DO";
        Log.d("place",""+num );
        if (switches[placeSwitch].isChecked()) {
            checkInsertData = DB.updateuserdata(findInDB, switches[placeSwitch].getTextOn().toString());
        }
        else {
            checkInsertData = DB.updateuserdata(findInDB, switches[placeSwitch].getTextOff().toString());
        }
    }

    public void listenerButtons(View v) {//A function that listens to buttons and inserts the appropriate video
        int num=0;
        switch (v.getId())
        {
            case R.id.btnday1:
               num=1;
                break;

            case R.id.btnday2:
                num=2;
                break;

            case R.id.btnday3:
                num=3;
                break;

            case R.id.btnday4:
                num=4;
                break;
            case R.id.btnday5:
                num=5;
                break;
            case R.id.btnday6:
                num=6;
                break;
            case R.id.btnday7:
                num=7;
                break;
        }
        Intent intent = new Intent(DaysActivity.this, VideoActivity.class);
        intent.putExtra("numVideo",num);//Transmits the movie number to the video department
        startActivity(intent);
    }


private void ifTurnSwitches() {//A method that checks which switches should already be on in the days when the exercise was done
    String findInDB = "";
    int placeSwitch = 0;
    int numDay = 0;
    for (; placeSwitch < 7; placeSwitch++) {
        numDay++;
        findInDB = "DAY" + numDay + "_DO";
        if (DB.getValue(findInDB).equals(getString(R.string.YES))) {//For the status quo that already existed after leaving
            switches[placeSwitch].setChecked(true);
        }
    }
}


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        DB = new DBHelper(this);
        switches=new Switch[7];
        buttons=new Button[7];

        switches[0]=findViewById(R.id.switch1);
        switches[1]=findViewById(R.id.switch2);
        switches[2]=findViewById(R.id.switch3);
        switches[3]=findViewById(R.id.switch4);
        switches[4]=findViewById(R.id.switch5);
        switches[5]=findViewById(R.id.switch6);
        switches[6]=findViewById(R.id.switch7);


            buttons[0] = findViewById(R.id.btnday1);
            buttons[1] = findViewById(R.id.btnday2);
            buttons[2] = findViewById(R.id.btnday3);
            buttons[3] = findViewById(R.id.btnday4);
            buttons[4] = findViewById(R.id.btnday5);
            buttons[5] = findViewById(R.id.btnday6);
            buttons[6] = findViewById(R.id.btnday7);
            int placeButton = 0;
            for (; placeButton < 7; placeButton++) {
               String findInDB = "DAY" + (placeButton + 1);
                buttons[placeButton].setText(DB.getValue(findInDB));

            }
}





    @Override
    protected void onStart()
    {
        super.onStart();
        ifTurnSwitches();//Turns on the days when there is exercise
        Log.d("mylogdays", ">>> onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("mylogdays", ">>> onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("mylogdays", ">>> onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
       // Intent intent = new Intent(DaysActivity.this, MainActivity.class);//-------declare how father video
      //  startActivity(intent);

        Log.d("mylogdays", ">>> onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("mylogdays", ">>> onDestroy()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("mylogdays", ">>> onRestart()");
    }



}
