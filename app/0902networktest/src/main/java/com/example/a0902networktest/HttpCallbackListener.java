package com.example.a0902networktest;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}
