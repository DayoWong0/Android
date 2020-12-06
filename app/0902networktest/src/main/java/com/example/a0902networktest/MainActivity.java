package com.example.a0902networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView responseText;
    private Button callbackTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = findViewById(R.id.send_request);
        responseText = findViewById(R.id.response_text);
        callbackTest = findViewById(R.id.callback_test);
        sendRequest.setOnClickListener(this);
        callbackTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId() ) {
            case R.id.send_request:
                // sendRequestWithHttpURLConnection();
                sendRequestWithOkHttp();
                break;
            case R.id.callback_test:
                callbackTestMethod();
                break;
            default:
                break;
        }
    }

    private void callbackTestMethod() {
//        HttpUtil.sendOkHttpRequest("https://wwww.baidu.com", new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                showResponse("error in sendOkHttpRequest ");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                showResponse(response.body().toString());
//            }
//        });

        HttpUtil.sendHttpRequest("https://www.baidu.com", new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                showResponse(response);
            }

            @Override
            public void onError(Exception e) {
                showResponse("error in sendHttpRequest ");
            }
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
//                    http://192.168.123.208:8080/get_data.json
//                            .url("https://www.baidu.com")
                            .url("http://192.168.123.208:8080/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
//                    parseJSONWithJSONObject(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseXMLWithPull(responseData);
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {}.getType());
        for (App app : appList) {
            Log.d("MainActivity", "id is " + app.getId());
            Log.d("MainActivity", "name is " + app.getName());
            Log.d("MainActivity", "version is " + app.getVersion());
        }
    }

    private void showResponse(final String response) {
        runOnUiThread(() -> {
            // 在这里进行UI操作，将结果显示到界面上
            responseText.setText(response);
        });
    }
}