package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Review;

public class DataReceiveActivity extends AppCompatActivity {
private static final String TAG="DataReceiveActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_receive);

        Intent intent=getIntent(); //인텐트를 얻는 방법
        int key1=intent.getIntExtra("key1",0); // 만약 key1이 없다면 두번째 매개변수의 값을 여기에 넣어라
        String key2=intent.getStringExtra("key2"); //만약 해당 key2가 없다면 null값이 자동으로 들어감
        Review key3=(Review)intent.getSerializableExtra("key3");

        Log.i(TAG,""+key1);
        Log.i(TAG,key2);
        Log.i(TAG,key3.getTitle());
    }
}
