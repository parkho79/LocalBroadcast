package com.parkho.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.parkho.broadcast.receive.PhReceiver;

public class PhMainActivity extends AppCompatActivity {

    private PhReceiver mReceiver = new PhReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LocalBroadcast 등록
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.parkho.broadcat.local");
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);

        // LocalBroadcast 보내기
        findViewById(R.id.btn_broadcast).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View a_view) {
                Intent intent = new Intent("com.parkho.broadcat.local");
                LocalBroadcastManager.getInstance(PhMainActivity.this).sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // LocalBroadcast 등록 해제
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }
}