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

public void handleBtnNotification(View v){
    Intent intent=new Intent(this,UIActivity.class);
    //언제가 될지는 모르겠지만 불려지게되면 화면을 이동시키는 intent. 기존의 intent는 바로 화면이동
    //(현재액티비티,요청코드,불렸을때진짜하고싶은의도)  , FLAG_UPDATE_CURRENT  =(가장최근의 인텐트로 덮어써서 업데이트하겠다)
    //back버튼 클릭시 마지막 화면이 기본적으로 나옴
   // PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

    //공지를 눌러서 들어간 화면에서 back버튼 눌렀을시 이전화면을 보여주는 것이 아닌 해당 액티비티의 부모화면으로 가고싶을때
    PendingIntent pendingIntent=TaskStackBuilder.create(this)
            .addParentStack(UIActivity.class)//Manifests 파일에서 이전 화면에 대한 정보를 얻어 스택에 넣음,해당 클래스의 부모를 넣음
            .addNextIntent(intent) //부모의 다음화면
            .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);//(요청번호,불렀을때하고싶은의도)

    Notification notification=new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("MQTT 알림")
            .setContentText("온도가 비정상적으로 높습니다.")
            .setAutoCancel(true) //클릭시 통지가 사라짐
            .setVibrate(new long[]{1000,500,1000,500}) // 총 두번의 진동 1초간 울리고 0.5초 쉬고
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) //
            .setContentIntent(pendingIntent)
            .build();

    NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    nm.notify(1,notification); //(ID(인식용고유번호),notification) 똑같은 아이디로 공지가 뜨면 기존것에 덮어쓰기가 된다.

    nm.notify(2,notification);
}


}
