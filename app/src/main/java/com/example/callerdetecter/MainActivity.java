package com.example.callerdetecter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_READ_PHONE_STATE = 1;
    private Button callButton;
    private CallReceiver handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callButton = findViewById(R.id.call_button);
        callButton.setOnClickListener(v -> {

            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                askPermission();
                Toast.makeText(MainActivity.this, "Please grant the necessary permission", Toast.LENGTH_SHORT).show();

            } else {
               registerPhoneReceiver();
            }

        });
    }


    private void askPermission() {

        int phoneReadStatePermission = getApplicationContext().checkSelfPermission("READ_PHONE_STATE");
        int readCallLogPermission = getApplicationContext().checkSelfPermission("READ_CALL_LOG");
        Boolean hasPhoneReadStatePermission  = phoneReadStatePermission == PackageManager.PERMISSION_GRANTED;
        Boolean hasReadCallLogPermission = readCallLogPermission == PackageManager.PERMISSION_GRANTED;

        if (!hasPhoneReadStatePermission || !hasReadCallLogPermission) {
            this.requestPermissions(new String[]{
                            Manifest.permission.READ_CALL_LOG,
                            Manifest.permission.READ_PHONE_STATE},
                    1);
            this.registerPhoneReceiver();
        } else {
            this.registerPhoneReceiver();
        }
    }

    void registerPhoneReceiver() {
       handler = new CallReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        Log.d("Recever register","sb2318");
        //  registerReceiver(handler, filter);
        //sendBroadcast(new Intent("android.intent.action.PHONE_STATE"));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(handler);
        super.onDestroy();

    }
}