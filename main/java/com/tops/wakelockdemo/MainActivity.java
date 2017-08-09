package com.tops.wakelockdemo;

import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnApply);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyWake");

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == button.getId()) {
            wakeLock.acquire();
            Toast.makeText(this, "Wake lock acquired...", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.btnRelease) {
            wakeLock.release();
            Toast.makeText(this, "Wake lock release...", Toast.LENGTH_SHORT).show();
        }
    }
}
