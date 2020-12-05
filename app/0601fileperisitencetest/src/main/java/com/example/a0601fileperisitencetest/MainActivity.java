package com.example.a0601fileperisitencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String inputText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit);
        inputText = load();
        if (!TextUtils.isEmpty(inputText)){
            editText.setText(inputText);
            // 输入框光标跳到最后一行
            editText.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "存入的数据为空或不存在", Toast.LENGTH_SHORT).show();
        }
    }

    private String load() {
        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();

        try {
            inputStream = openFileInput("data");
            bufferedReader =  new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (true){
                try {
                    if ((line = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inputText = editText.getText().toString();
        save(inputText);
    }

    private void save(String inputText) {

        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}