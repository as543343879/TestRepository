package com.mycompany.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private LocationManager locationManager;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private List<LatLng> listLocation=new ArrayList<>();
    private Polyline polyline;
    private LatLng currlatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkPermission();

    }

    public void checkPermission() {

        int permissionGps = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionNetwork = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionGps != PackageManager.PERMISSION_GRANTED || permissionNetwork != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }


    public void handleBtnCurrentLocation(View view) {
        checkPermission();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //(어디서 위치정보를 받을 것이냐,다시 위치를 체크할 시간(10000일경우 10초),해당 거리의 변화마다 위치를 체크(0으로 주면 거리는 사용하지 않겠다),리스너)
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 10000, 0, locationListener
        );

    }

    private LocationListener locationListener = new LocationListener() {
        //위치정보를 얻게 되면 자동으로 실행
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.i(TAG, "위도: " + latitude);
            Log.i(TAG, "경도: " + longitude);

            //위치정보를 한번만 받도록 리스너를 제거하는 코드
            locationManager.removeUpdates(locationListener);
            showMap(latitude,longitude);
        }

        //현재 프로바이드의 상태가 변경이 되면실행 ex)gps 신호를 받고 있다가 받지 못하게 되었을때 실행
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        //현재 위치를 제공하는 자가 활성화 되어있을때 실행
        @Override
        public void onProviderEnabled(String provider) {

        }

        ////현재 위치를 제공하는 자가 비활성화 되어있을때 실행

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    //다음지도사용(라이브러리 추가 필요)

    public void handleBtnFromAddressToLocation(View v) {
        String url = "http://apis.daum.net/local/geo/addr2coord";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("apikey", "ed39f1a2d5d0ef6900f484030523afe2")
                .addQueryParameter("q", "서울특별시 송파구 가락동 78-1")
                .addQueryParameter("output", "json")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // \u00aa 등을 한글로 변환하기 위해 StringEscapeUtils 사용
                // compile 'commons-lang:commons-lang:2.6' 추가해야 함
                final String json = StringEscapeUtils.unescapeJava(response.body().string());
                Log.i(TAG,json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    jsonObject=jsonObject.getJSONObject("channel");
                    JSONArray jsonArray=jsonObject.getJSONArray("item");
                    jsonObject=jsonArray.getJSONObject(0);
                    double latitude=jsonObject.getDouble("lat");
                    double longitude=jsonObject.getDouble("lng");
                    Log.i(TAG,"위도 :"+latitude);
                    Log.i(TAG,"경도 :"+longitude);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, e.toString());
            }
        });
    }

    private void showMap(double latitude,double longitude){
        currlatLng=new LatLng(latitude,longitude);
        //***지도를 드로잉하기 위한 코드
        //movecamera는 지도가 바로 뜨고, animatecamera는 이동모습까지 보여주고 지도나옴
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currlatLng,16)); // (위치정보객체,줌확대값)
        //맵의 타입설정(일반,위성사진 등등)
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //현재위치의 마크표시
        checkPermission();
        googleMap.setMyLocationEnabled(true);

        //**경로를 드로잉하기 위한 코드
        listLocation.clear();
        listLocation.add(currlatLng);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                listLocation.add(latLng);
                drawingPath();


                }

        });
    }



    public void handleBtnMap(View view){

        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        //지도를 그리기 위해 준비해야 할 객체의 초기화작업시간이 필요하기 때문에 비동기로 얻음.
        //지도가 준비가 되면 onmapreadycallback이 실행됨
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MainActivity.this.googleMap=googleMap;
                handleBtnCurrentLocation(null);//위치정보얻기위해부름름
            }
        });

    }

    private void drawingPath(){
        if(polyline !=null) {
            polyline.remove();
        }
        LatLng[] paths=new LatLng[listLocation.size()];
        PolylineOptions polylineOptions=new PolylineOptions()
                .add(listLocation.toArray(paths))
                .width(10)
                .color(Color.RED);
        polyline=googleMap.addPolyline(polylineOptions);
    }


    public void handleBtnCuurLocationReturn(View v){
        listLocation.add(currlatLng);
        drawingPath();
    }

    public void handleBtnPathRemove(View v){
        listLocation.clear();
        listLocation.add(currlatLng);
        polyline.remove();
    }
}

