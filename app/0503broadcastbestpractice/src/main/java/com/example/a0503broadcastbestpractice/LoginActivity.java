package com.example.a0503broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private CheckBox mCbRemPass;
    private String account;
    private String password;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtAccount = findViewById(R.id.account);
        mEtPassword = findViewById(R.id.password);
        mBtnLogin = findViewById(R.id.login);
        mCbRemPass = findViewById(R.id.remember_pass);

//        mEtAccount.setText("admin");
//        mEtPassword.setText("123456");

        // 从文件中读取保存的登录信息
        SharedPreferences preferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        account = preferences.getString("account", "");
        password = preferences.getString("password", "");
        mCbRemPass.setChecked(preferences.getBoolean("rememberPwd", false));
        // 应用到 UI
        mEtAccount.setText(account);
        mEtAccount.setSelection(account.length());
        mEtPassword.setText(password);
        mEtPassword.setSelection(password.length());

        // 点击登录
        mBtnLogin.setOnClickListener(v -> {
            account = mEtAccount.getText().toString();
            password = mEtPassword.getText().toString();

            // 密码正确
            if (account.equals("admin") && password.equals("123456")) {
                // 若勾选保存密码，保存账号密码
                editor = preferences.edit();
                if (mCbRemPass.isChecked()){
                    editor.putString("account", account);
                    editor.putString("password", password);
                    editor.putBoolean("rememberPwd", true);
                }
                // 保存账号，不保存密码、取消勾选
                else {
                    editor.putString("account", account);
                    editor.putString("password", "");
                    editor.putBoolean("rememberPwd", false);
                }
                editor.apply();

                // 跳转到 MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            // 密码错误
            else {
                Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}