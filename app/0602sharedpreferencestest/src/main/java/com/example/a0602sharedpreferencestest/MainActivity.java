package com.example.a0602sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button saveData;
    private Button restoreData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveData = findViewById(R.id.save_data);
        restoreData = findViewById(R.id.restore_data);

        saveData.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("name", "tom")
                    .putInt("age", 28)
                    .putBoolean("married", false)
                    .apply();
        });

        restoreData.setOnClickListener(v->{
            SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
            String name = data.getString("name", "");
            int age = data.getInt("age", 0);
            boolean married = data.getBoolean("married", false);
            Toast.makeText(this, "info from sharedPreferences: name: " + name +  "age: " + age + "married: " + married, Toast.LENGTH_SHORT ).show();
            Log.d(TAG, "onCreate: name is " + name + " ");
            Log.d(TAG, "onCreate: age is " + age + " ");
            Log.d(TAG, "onCreate: married " + married + " ");
        });


    }
}