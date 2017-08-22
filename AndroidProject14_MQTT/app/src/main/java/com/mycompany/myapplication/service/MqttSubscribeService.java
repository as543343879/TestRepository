package com.mycompany.myapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.activity.MqttActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscribeService extends Service {
    private static final String TAG = "MqttSubscribeService";
    private String serverURI = "tcp://192.168.3.133:1883";
    private String topic = "/devices/#";
    private int qos = 0;
    private MqttAndroidClient mqttAndroidClient;
    private Thread thread;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mqttAndroidClient = new MqttAndroidClient(this, serverURI, MqttClient.generateClientId());
        mqttAndroidClient.setCallback(mqttCallback);
    }

    private MqttCallback mqttCallback = new MqttCallback() {
        @Override
        public void connectionLost(Throwable cause) {
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            Log.i(TAG, message.toString());
            String strMessage = message.toString();

            Intent intent = new Intent(MqttSubscribeService.this, MqttActivity.class);
            intent.putExtra("message", strMessage);

            PendingIntent pendingIntent = TaskStackBuilder.create(MqttSubscribeService.this)
                    .addParentStack(MqttActivity.class)
                    .addNextIntent(intent)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = new NotificationCompat.Builder(MqttSubscribeService.this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("MQTT 알림")
                    .setContentText("MQTT 메시지가 도착했습니다.")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setVibrate(new long[] {1000, 500, 1000, 500})
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .build();

            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(1, notification);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!mqttAndroidClient.isConnected()) {
            thread = new Thread() {
                @Override
                public void run() {
                    try {
                        mqttAndroidClient.connect()
                                .setActionCallback(new IMqttActionListener() {
                                    @Override
                                    public void onSuccess(IMqttToken asyncActionToken) {
                                        Log.i(TAG, "MQTT 서버 연결 성공");
                                        subscribe();
                                    }
                                    @Override
                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                        Log.i(TAG, "MQTT 서버 연결 실패: " + exception.toString());
                                        stopSelf();
                                    }
                                });
                    } catch (MqttException e) {
                        Log.i(TAG, "MQTT 서버 연결 실패: " + e.toString());
                        stopSelf();
                    }
                }
            };
            thread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void subscribe() {
        try {
            mqttAndroidClient.subscribe(topic, qos)
                    .setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i(TAG, "MQTT 구독 시작");
                        }
                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i(TAG, "MQTT 구독 실패: " + exception.toString());
                            stopSelf();
                        }
                    });
        } catch (MqttException e) {
            Log.i(TAG, "MQTT 구독 실패: " + e.toString());
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mqttAndroidClient.isConnected()) {
            try {
                mqttAndroidClient.unsubscribe(topic);
                Log.i(TAG, "MQTT 구독 중지");
                mqttAndroidClient.disconnect();
                Log.i(TAG, "MQTT 서버 연결 끊김");
            } catch (MqttException e) {}
        }
    }
}
