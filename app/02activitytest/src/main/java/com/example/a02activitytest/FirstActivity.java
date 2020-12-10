package com.example.a02activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
//        点击后的 Toast 事件
        button1.setOnClickListener(
                v ->Toast.makeText(FirstActivity.this, "You Clicked Button 1", Toast.LENGTH_SHORT).show()
                // finish 销毁活动
//                v -> finish()
        );

        //显式 intent 启动活动, 指定启动某一个活动，这里指定启动 SecondActivity
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        //隐式 intent 启动活动 使用默认的 category
        button3.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.a02activitytest.ACTION_START");
            //intent 默认携带 category：android.intent.category.DEFAULT
            startActivity(intent);
        });

        // 隐式 intent 启动活动，让系统去寻找匹配的 action 和 category, action 和 category 在 AndroidManifest 中注册
        button4.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.a02activitytest.ACTION_START");
            // 添加自定义的 category
            intent.addCategory("com.example.a02activitytest.MyCategory");
            startActivity(intent);
        });

        // 隐式 intent 调用浏览器打开网站
        button5.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // 此方法接受 Uri 参数，所以需要先调用 Uri.parse
            intent.setData(Uri.parse("https://www.baidu.com"));
            startActivity(intent);
        });

        // 隐式 intent 点击打开拨号页面 并携带电话号码
        button6.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:1001011"));
            startActivity(intent);
        });

        // intent 携带数据到另一个 SecondActivity
        button7.setOnClickListener(v -> {
            String data = "data from FirstActivity";
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        });

        // startActivityForResult 希望从另一个活动中获得返回的数据 配合 onActivityResult 方法使用
        button8.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            // 请求码需要唯一，用做此类的 onActivityResult 方法的参数
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    //下一行代码是错的!!!
                    //String returnedData = getIntent().getStringExtra("data_return");
                    String returnedData = null;
                    if (data != null) {
                        returnedData = data.getStringExtra("data_return");
                    }
                    Log.d(TAG, "onActivityResult: returnedData: " + returnedData);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // 设置菜单
        getMenuInflater().inflate(R.menu.main, menu);
        // true 启用菜单，false 不启用
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,
                        "You clicked add item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,
                        "You clicked remove item", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}