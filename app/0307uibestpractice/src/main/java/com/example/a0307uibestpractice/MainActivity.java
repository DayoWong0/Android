package com.example.a0307uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<Msg> msgList = new ArrayList<>();
    private Button mBtnSend;
    private EditText mEtInputText;
    private RecyclerView mRecycler;
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        mBtnSend = findViewById(R.id.send);
        mEtInputText = findViewById(R.id.input_text);
        mRecycler = findViewById(R.id.msg_recycler_view);
        msgAdapter = new MsgAdapter(msgList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(msgAdapter);

        mBtnSend.setOnClickListener(v -> {
            String content = mEtInputText.getText().toString();
            if (content != null && !content.equals("")){
                Msg msg = new Msg(content, Msg.TYPE_SENT);
                msgList.add(msg);
                //有新消息时，刷新 RecyclerView 中的显示
                msgAdapter.notifyItemInserted(msgList.size() - 1);
                // 将 RecyclerView 定位到最后一行
                mRecycler.scrollToPosition(msgList.size() - 1);
                // 清空输入内容
                mEtInputText.setText("");
            }
        });
    }

    private void initMsg() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Log.d(TAG, "initMsg: 消息初始化成功");
    }
}