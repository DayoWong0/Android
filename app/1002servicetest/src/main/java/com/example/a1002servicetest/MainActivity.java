package com.example.a1002servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startService;

    private Button stopService;

    private Button bindService;

    private Button unbindService;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = findViewById(R.id.start_service);
        stopService = findViewById(R.id.stop_service);
        bindService = findViewById(R.id.bind_service);
        unbindService = findViewById(R.id.unbind_service);

        startService.setOnClickListener(v->{
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        });

        stopService.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        });

        bindService.setOnClickListener(v->{
            Intent bindIntent = new Intent(this, MyService.class);
            // 绑定服务
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        });

        unbindService.setOnClickListener(v->{
            unbindService(connection);

        });
    }
}