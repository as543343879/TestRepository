package com.mycompany.myapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.mycompany.myapplication.R;


public class HomeActivity extends AppCompatActivity {
    private static final String TAG="HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

public void handleBtnSendBroadcast(View v){
    //방송 발생
    Intent intent =new Intent("com.mycompany.myapplication.SelfBroadcast");
    //방송을 통한 데이터 전달
    intent.putExtra("title","MQTT알림");
    intent.putExtra("content","온도가 비정상적으로 높습니다.");
    sendBroadcast(intent);

}




}
