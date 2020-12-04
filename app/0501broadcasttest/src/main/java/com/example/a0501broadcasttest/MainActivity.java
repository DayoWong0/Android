package com.example.a0501broadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private IntentFilter intentFilter;
    private IntentFilter intentFilterMyBroadcast;

    private NetworkChangeReceiver networkChangeReceiver;
    private MyBroadcastReceiver myBroadcastReceiver;

    // 无序（标准）广播
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(v -> {
//            // 发送广播
//            Intent intent = new Intent("com.example.a0501broadcasttest.MY_BROADCAST");
//            intent.putExtra("data", "data from Broadcast");
//            sendBroadcast(intent);
//            Toast.makeText(MainActivity.this,"Broadcast have sent", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "Broadcast have sent");
//        });
//
//        IntentFilter intentFilterMyBroadcast = new IntentFilter();
//        intentFilterMyBroadcast.addAction("com.example.a0501broadcasttest.MY_BROADCAST");
//        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
//        registerReceiver(myBroadcastReceiver, intentFilterMyBroadcast);
//
//        intentFilter = new IntentFilter();
//        // intentFilter 添加 网络变化 action
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        // 事件发生调用 onReceive 方法
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);
//    }


    // 有序广播
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            // 发送广播
            Intent intent = new Intent("com.example.a0501broadcasttest.MY_BROADCAST");
            intent.putExtra("data", "data from Broadcast");
            sendOrderedBroadcast(intent, null);
            Toast.makeText(MainActivity.this,"Broadcast have sent", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Broadcast have sent");
        });

        intentFilterMyBroadcast = new IntentFilter();
        intentFilterMyBroadcast.addAction("com.example.a0501broadcasttest.MY_BROADCAST");
        intentFilterMyBroadcast.setPriority(100);
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, intentFilterMyBroadcast);

        intentFilter = new IntentFilter();
        // intentFilter 添加 网络变化 action
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        // 事件发生调用 onReceive 方法
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        unregisterReceiver(myBroadcastReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "Network is available",
                        Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}