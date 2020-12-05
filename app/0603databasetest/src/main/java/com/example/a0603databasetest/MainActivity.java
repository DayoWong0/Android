package com.example.a0603databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button createDatabase =  findViewById(R.id.create_database);

        // 创建数据库
        createDatabase.setOnClickListener(v -> dbHelper.getWritableDatabase());

        Button addData = findViewById(R.id.add_data);
        // 增加数据
        addData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            // 开始组装第一条数据
            values.put("name", "The Da Vinci Code");
            values.put("author", "Dan Brown");
            values.put("pages", 454);
            values.put("price", 16.96);
            db.insert("Book", null, values); // 插入第一条数据
            values.clear();
            // 开始组装第二条数据
            values.put("name", "The Lost Symbol");
            values.put("author", "Dan Brown");
            values.put("pages", 510);
            values.put("price", 19.95);
            db.insert("Book", null, values); // 插入第二条数据
            Log.d(TAG,"insert: " +  values.toString());
        });

        // 更新数据
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 10.99);
            db.update("Book", values, "name = ?", new String[] { "The Da Vinci Code" });
            Log.d(TAG, "update: " + values.toString());
        });

        // 删除数据
        Button deleteButton = findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int affectRows = db.delete("Book", "pages > ?", new String[] { "500" });
            Log.d(TAG, "delete: affectRow is " + affectRows);
        });

        // 查询数据
        Button queryButton = findViewById(R.id.query_data);
        queryButton.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            // 查询Book表中所有的数据
            Cursor cursor = db.query("Book", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Log.d(TAG, "book name is " + name);
                    Log.d(TAG, "book author is " + author);
                    Log.d(TAG, "book pages is " + pages);
                    Log.d(TAG, "book price is " + price);
                } while (cursor.moveToNext());
            }
            cursor.close();
        });
    }
}
