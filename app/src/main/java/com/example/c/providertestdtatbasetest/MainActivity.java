package com.example.c.providertestdtatbasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addData=(Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
// 添加数据
                Uri uri = Uri.parse("content://com.example.c.databasetest.provider/book");
//                存放数据
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
//                添加到新的newURI
                Uri newUri = getContentResolver().insert(uri, values);
//                设置新的id
                newId = newUri.getPathSegments().get(1);
            }

        });
//查询数据
        Button queryData=(Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("content://com.example.c.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);

                    }

                }
//                查询结束
                cursor.close();
            }

        });
        Button updataData=(Button)findViewById(R.id.update_data);
        updataData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
// 更新数据
//                不让其他行受影响添加新的newId
                Uri uri = Uri.parse("content://com.example.c.databasetest.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 100.00);
                getContentResolver().update(uri, values, null, null);
            }

        });

        Button deleteData=(Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
// 删除数据
                Uri uri = Uri.parse("content://com.example.c.databasetest.provider/book/" + newId);
                getContentResolver().delete(uri, null, null);

            }

        });



























































    }

}
