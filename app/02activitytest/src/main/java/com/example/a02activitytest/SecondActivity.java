package com.example.a02activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d(TAG, data);
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(v -> {
            // new Intent 因为不用打开另一个程序，所以用空参数构造方法，否则就成了启动另一个活动
            Intent intent = new Intent();
            // 设置返回的数据
            intent.putExtra("data_return", "result from SecondActivity");
            // 设置处理结果，便于调用此活动的另一个活动 onActivityResult 方法处理逻辑
            setResult(RESULT_OK, intent);
            // 直接 finish，此时就会调用 onActivityResult 方法
            finish();
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("data_return", "data from " + TAG + "(onBackPressed)");
        setResult(RESULT_OK, intent);
        finish();
    }
}