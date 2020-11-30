package com.example.a02activitylifecycletest;

import android.app.Activity;
import android.os.Bundle;
//  不能继承自AppCompatActivity，会闪退
public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }
}