package com.example.a02baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        Button button1 = findViewById(R.id.first_activity);
        Button button2 = findViewById(R.id.second_activity);
        Button button3 = findViewById(R.id.third_activity);
        Button button4 = findViewById(R.id.action_start);

        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        });

        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        button3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        });

        button4.setOnClickListener(v -> {
            SecondActivity.actionStart(MainActivity.this, "data 1 from MainActivity", "data2 from MainActivity");
        });
    }
}