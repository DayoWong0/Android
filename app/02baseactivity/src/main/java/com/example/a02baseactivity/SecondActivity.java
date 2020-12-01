package com.example.a02baseactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 获取 MainActivity 传来的数据
        String data1 = getIntent().getStringExtra("data1");
        String data2 = getIntent().getStringExtra("data2");
        Log.d(TAG, "data1: " + data1);
        Log.d(TAG, "data2: " + data2);
    }

    // 启动此活动的方法，便于多人协作，可以从方法名看出所需要的参数
    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("data1", data1);
        intent.putExtra("data2", data2);
        context.startActivity(intent);
    }
}