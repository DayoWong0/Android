package com.example.a02baseactivity;


import android.os.Bundle;
import android.os.Process;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button button = findViewById(R.id.finish_all);
        button.setOnClickListener(v -> {
            ActivityCollector.finishAll();
            //可选 kill current process
            android.os.Process.killProcess(Process.myPid());
        });
    }
}