package com.mycompany.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Utils.getTag(),"실행");
        super.onCreate(savedInstanceState);
        //현재 화면을 해당 xml을 해석하여 구성해라
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        Log.i(Utils.getTag(),"실행");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.i(Utils.getTag(),"실행");
        super.onStart();
    }


    @Override
    protected void onResume() {
        Log.i(Utils.getTag(),"실행");
        super.onResume();
    }

    //이 메소드를 재정의한 해당 activity에서 메뉴를 생성

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(Utils.getTag(),"실행");
        //xml을 해석하여 해당 메뉴를 만들어냄//AppCompatActivity가 가지고 있는 menuinflater를 불러서 inflate를 실행하여 xml을 해석함
        getMenuInflater().inflate(R.menu.menu_main,menu); //(해석할 xml파일 위치, mainActivity가 가지고 있는 menu) xml파일을 해석하여 해당 menu에 넣어라
        return super.onCreateOptionsMenu(menu);
    }
    //메뉴를 선택했을 시에 실행되는 메소드

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(Utils.getTag(),"실행");
        if(item.getItemId()==R.id.action_full_activity){
            Intent intent=new Intent(this, FullActivity.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.action_dialog_activity1){
            Intent intent=new Intent(this, DialogActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    //#############################################


    @Override
    protected void onPause() {
        Log.i(Utils.getTag(),"실행");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(Utils.getTag(),"실행");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(Utils.getTag(),"실행");
        super.onDestroy();
    }
}
