package com.example.a0603databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnCreateDB;
    private MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnCreateDB = findViewById(R.id.create_database);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        mBtnCreateDB.setOnClickListener(v -> {
            myDatabaseHelper.getWritableDatabase();
        });
    }
}