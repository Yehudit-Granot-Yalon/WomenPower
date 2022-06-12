package com.yehudit.powerwomen;
//https://stackoverflow.com/questions/45853076/incoming-call-blocking-when-app-is-open

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Method;

public class BlockCallReceiver extends BroadcastReceiver {
    String number;
    DBHelper DB;
    private String phoneNumber;
    Context context;
    String block;


    @SuppressLint("MissingPermission")
    public void onReceive(Context context, Intent intent) {
        // ITelephony i= new ITelephony()
        //number="8";
        context=context;

        DB = new DBHelper(context);


        // Toast.makeText(context,"block?  "+block,Toast.LENGTH_SHORT).show();
//        ActivityCompat.requestPermissions((Activity) context,
//                new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG},
//                0);
//        ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
//          ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG);
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(PhoneStateReciver.this.getBaseContext(),
//                    new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.SYSTEM_ALERT_WINDOW},
//                    1);
//        }
        Toast.makeText(context," startttt ",Toast.LENGTH_SHORT).show();
        //ITelephony telephonyService;
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        try {
            if(state!=null&&state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context,"EXTRA_STATE_RINGING",Toast.LENGTH_SHORT).show();
                number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                //   Toast.makeText(context,"numbreeeerr   "+phoneNumber,Toast.LENGTH_SHORT).show();
                //  Toast.makeText(context,"numbreeeerr 2  "+number,Toast.LENGTH_SHORT).show();
                TelecomManager tm;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    Toast.makeText(context,"Build.VERSION.SDK_INT  ",Toast.LENGTH_SHORT).show();

                    tm = (TelecomManager)context.getSystemService(Context.TELECOM_SERVICE);
                    if (tm == null) {

                        throw new NullPointerException("tm == null");
                    }
                    //if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                    //   return;
                    //    }
                    Toast.makeText(context," tm not null ",Toast.LENGTH_SHORT).show();
                    if (context.checkSelfPermission (Manifest.permission.ANSWER_PHONE_CALLS) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(context,"befor ",Toast.LENGTH_SHORT).show();
                        tm.endCall();
                        Toast.makeText(context,"after ",Toast.LENGTH_SHORT).show();
                    }

                    else
                        Toast.makeText(context," not parmisiion ",Toast.LENGTH_SHORT).show();

                };
                //block=DB.getVal( phoneNumber);
                //  Toast.makeText(context,"blockkk   "+block,Toast.LENGTH_SHORT).show();
                //android.telephony.TelephonyManager
                /////////

//             //   TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//             //   @SuppressLint("SoonBlockedPrivateApi") Method m = tm.getClass().getDeclaredMethod("getITelephony");
//                Toast.makeText(context,"after",Toast.LENGTH_SHORT).show();
//                m.setAccessible(true);
//                telephonyService = (ITelephony) m.invoke(tm);
////
//                telephonyService.wait();
////
//                telephonyService.endCall();




            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
                Toast.makeText(context,"Received State",Toast.LENGTH_SHORT).show();
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context,"Idle State",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // public void rejectCall() {
    //   try {
//          Method method = Class.forName("android.os.ServiceManager")
//                   .getMethod("getService", String.class);
//          IBinder binder = (IBinder) method.invoke(null, new Object[]{Context.TELEPHONY_SERVICE});
//            ITelephony telephony = ITelephony.Stub.asInterface(binder);
//           ITelephony telephony = ITelephony.class.asSubclass(binder);
//           telephony.endCall();
//       } catch (NoSuchMethodException e) {
//           // Log.d(TAG, "", e);
//        } catch (ClassNotFoundException e) {
//          //  Log.d(TAG, "", e);
//        } catch (Exception e) {
//        }
//    }
}
//}

//}