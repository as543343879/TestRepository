package com.mycompany.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class BackgroundService extends Service {
    private static final String TAG="BackgroundService";
    private Thread thread;


    //백그라운드에서는 사용하지 않고 binding Service에서만 사용하지만 추상메소드 이기 때문에 일단 존재해야함
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //최초로 StartService()를 호출할 때 실행됨.
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"oncreate");
    }

    //StartService()를 호출할 때 마다 실행됨.
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.i(TAG,"onStartCommand");

        if(thread==null||!thread.isAlive()){
            thread=new Thread(){
                private int count=0;
                @Override
                public void run() {
                    while (true) {
                        Log.i(TAG, "count :" + count++);

                        try {

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    stopSelf();

                }
            };
            thread.start();
        }


        //sticky는 앱을 강제종료 시키지 않고 뒤로가기 버튼이나 작업관리자로 지우게 했을시에 안드로이드가 확실히 살리지 않는다.
        return super.onStartCommand(intent,flags,startId);
    }

    //작업관리자에서 앱을 종료시켰을 때 실행됨.
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
        if(thread!=null&&thread.isAlive()){
            thread.interrupt();
            thread=null;
        }
    }

}
