package com.mycompany.myapplication.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.util.RealPathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class HttpActivity extends AppCompatActivity {
    private static final String TAG = "HttpActivity";
    private EditText txtParam1, txtParam2, txtAttach;
    private TextView txtDisplay;
    private WebSocket webSocket;
    private ScrollView scrollView;
    private EditText txtAngle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        txtParam1 = (EditText) findViewById(R.id.txtParam1);
        txtParam2 = (EditText) findViewById(R.id.txtParam2);
        txtAttach = (EditText) findViewById(R.id.txtAttach);
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        scrollView = (ScrollView)findViewById(R.id.scrollView2);
        txtAngle=(EditText)findViewById(R.id.txtAngle);
    }

    public void handleBtnGet(View v) throws IOException {
        String url = "http://192.168.3.11:8080/IoTWebProgramming/http/exam03";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("param1", txtParam1.getText().toString())
                .addQueryParameter("param2", txtParam2.getText().toString())
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                println(json);
            }
        });
    }

    public void handleBtnPost(View v) throws Exception {
        String url = "http://192.168.3.133:8080/IoTWebProgramming/http/exam03";
        RequestBody requestBody=new FormBody.Builder()
                .add("param1",txtParam1.getText().toString())
                .add("param2",txtParam2.getText().toString())
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                println(json);
            }
        });
    }


    private boolean checkPermission(){

        int permissionRES= ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWES= ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionRES!=PackageManager.PERMISSION_GRANTED||permissionWES!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return false;
        }else{
            return true;
        }

        }

    public void handleBtnAttach(View v) {
        if(checkPermission()) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "이미지 선택"), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Uri selectedImageUri=data.getData();
                String realPath=RealPathUtil.getRealPath(this,selectedImageUri);
                txtAttach.setText(realPath);
                Log.i(TAG,realPath);


            }
        }
    }

    public void handleBtnMultipart(View v) {
        String filePath=txtAttach.getText().toString();
        String[] paths=filePath.split("/");
        String fileName=paths[paths.length-1];
        Log.i(TAG,fileName);

        String url = "http://192.168.3.133:8080/IoTWebProgramming/http/exam04";
        RequestBody requestBody=new MultipartBody.Builder()
                .addFormDataPart("param1",txtParam1.getText().toString())
                .addFormDataPart("param2",txtParam1.getText().toString())
                .addFormDataPart("attach",fileName,RequestBody.create(MultipartBody.FORM,new File(filePath))) //파일패스에서 파일을 읽어서 멀티파트 바디를 만들어 넣음
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                println(json);
            }
        });
    }

    public void handleBtnWebSocketOpen(View v) {
        String url="ws://192.168.3.133:8080/SensingCarRemoteWebControl/websocket/thermistorsensor";
        Request request=new Request.Builder()
                .url(url)
                .build();
        OkHttpClient client=new OkHttpClient();
        client.newWebSocket(request, new WebSocketListener() {
            //웹소켓이 처음 연결되었을때
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                HttpActivity.this.webSocket=webSocket;
                println("[WebSocket Opened]");
            }
            //text가 날라왔을때 실행(ex. JSON)
            @Override
            public void onMessage(WebSocket webSocket, String text) {
                String json=text;
                println(json);


            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {

            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {

            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                HttpActivity.this.webSocket=null;
                println("[WebSocket closed]");

            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {

            }
        });



    }

    public void handleBtnWebSocketClose(View v) {
        //Code must be 1000-5000 or 0
        webSocket.close(1000,null);

    }



    public void println(final String text){

        txtDisplay.post(new Runnable() {
            @Override
            public void run() {
                txtDisplay.append(text+"\n");
                scrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                },100);
            }
        });
    }

    public void handleBtnChangeAngle(View v){
        String url="http://192.168.3.133:8080/SensingCarRemoteWebControl/ultrasonicsensor";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("command", "change")
                .addQueryParameter("angle", txtAngle.getText().toString())
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                println(json);
            }
        });
    }
}
