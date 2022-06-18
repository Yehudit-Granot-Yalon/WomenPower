package com.yehuditg.womenpower;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;
public class BlockCallReceiver extends BroadcastReceiver {
    @SuppressLint("MissingPermission")
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context," incoming call ",Toast.LENGTH_SHORT).show();
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        try {
            if(state!=null&&state.equals(TelephonyManager.EXTRA_STATE_RINGING)){//Detects an incoming call status
                TelecomManager telecomManager;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    telecomManager = (TelecomManager)context.getSystemService(Context.TELECOM_SERVICE);
                    if (telecomManager == null) {
                        throw new NullPointerException("telecomManager is null");
                    }
                    telecomManager.endCall();//block call
                    Toast.makeText(context,"after block call",Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
