package com.example.callerdetecter;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class CallReceiver extends PhonecallReceiver {
    private static final String TAG = "com.testing.firewall";
    public static String incoming_number;

    public void onIncomingCallStarted(Context ctx, String number, Date start) {
        incoming_number = number;
        Toast.makeText(ctx,"Incoming call detected: SB2318->CallerDetector ",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        super.onMissedCall(ctx, number, start);

        Toast.makeText(ctx,"Missed call: SB2318->CallerDetector  ",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        super.onIncomingCallEnded(ctx, number, start, end);
        Toast.makeText(ctx,"Call ended ",Toast.LENGTH_LONG).show();
    }
}
