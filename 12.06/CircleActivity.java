package com.yehudit.powerwomen;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//https://www.youtube.com/watch?v=7BVg8_WR7h4
public class CircleActivity extends AppCompatActivity {
    private ProgressBar circular_pro;
   // private Button clickme_btn;
    private TextView status;
    private DBHelper DB;
    private int progressStatus=0;
    String doDays="";
    String notDoDays="";
    private TextView showDayDO;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        setTitle("Weekly progress");

        DB = new DBHelper(this);
        circular_pro=(ProgressBar)findViewById(R.id.progressbar_circular);
        //clickme_btn=(Button)findViewById(R.id.progress_btn) ;
        status=(TextView) findViewById(R.id.text_status);
        showDayDO=(TextView) findViewById(R.id.day_do_status);
       // clickme_btn.setOnClickListener(new View.OnClickListener() {

            //@Override
           // public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        //   while (progressStatus < 100) {
                        int numDay=1;
                        String findInDB = "";
                        String dayInDB="";
                         doDays="";
                         notDoDays="";
                        while (numDay < 8) {
                            findInDB = "DAY" + numDay + "_DO";
                            dayInDB="DAY" + numDay;
                            if (DB.getValue(findInDB).equals(getString(R.string.YES))) {
                                progressStatus += 14;
                            if(progressStatus==98)
                                progressStatus=100;
                            doDays+="do in: "+DB.getValue(dayInDB)+"\n";
                            showDayDO.setText(doDays);
                            }
                            else{
                                //notDoDays+="not do in: "+DB.getValue(dayInDB)+"\n";
                                doDays+="no do: "+DB.getValue(dayInDB)+"\n";
                                showDayDO.setText(doDays);
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    circular_pro.setProgress(progressStatus);
                                    status.setText(progressStatus + "%");
                                }
                            });
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                                // e.print
                                Log.d("CATCH", "eror");
                            }
                       numDay++;
                        }
                    }
                }).start();

            //}

        }

        }



