package com.example.a02activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

//        获取从 FirstActivity 中传过来的数据
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(v -> {
            Intent intent = getIntent();
            String data = intent.getStringExtra("extra_data");
            Log.d(TAG, data);
        });

//        对应 FirstActivity 的 startActivityForResult 方法
        Button button10 = findViewById(R.id.button_10);
        button10.setOnClickListener(v -> {
            // new Intent，因为不用打开另一个程序，所以用空参数构造方法，否则就成了启动另一个活动
            Intent intent = new Intent();
            // 设置返回的数据
            intent.putExtra("data_return", "result from SecondActivity");
            // 设置处理结果，便于主调用活动 onActivityResult 方法处理逻辑
            setResult(RESULT_OK, intent);
            // 直接 finish，此时就会调用主调活动的 onActivityResult 方法
            finish();
        });
    }

//    若不是点击 button10 返回活动而是点击 返回键时的处理，主调活动依然会调用 onActivityResult
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("data_return", "data from " + TAG + "(onBackPressed)");
        setResult(RESULT_OK, intent);
        finish();
    }
}