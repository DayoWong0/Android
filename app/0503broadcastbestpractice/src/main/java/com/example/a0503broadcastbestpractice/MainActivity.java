package com.example.a0503broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button forceOffline = findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(v->{
            Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
            sendBroadcast(intent);
        });
    }
}