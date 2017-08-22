package com.mycompany.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static String TAG="MainActivity.class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBtnMessageDialog(View v){
        AlertDialog dialog=new AlertDialog.Builder(this) //AlertDialog의 내부클래스 Builder
        //다이얼로그 구성요소 만들기
        .setIcon(R.mipmap.ic_launcher)
        .setTitle("제목")
        .setMessage("알려 줄 메세지")//다이얼로그의 기본메세지
        .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG,"닫기 버튼 클릭");
            }
        })//가장 오른쪽 위치,한개씩만 사용가능, 여기서 POSITIVE는 긍정적이라는 의미X,표시위치만의미
        .setNegativeButton("취소",null)//오른쪽에서 두번째, (표시내용,리스너등록)
                .setNeutralButton("확인",null)//가장 왼쪽
         .create();
        dialog.show();
    }
    public void handleBtnListDialog(View v){
        final String[] menus={"메뉴1","메뉴2","메뉴3"};
        AlertDialog dialog=new AlertDialog.Builder(this) //AlertDialog의 내부클래스 Builder
                //다이얼로그 구성요소 만들기
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setItems(menus, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMenu=menus[which];
                        Log.i(TAG,selectedMenu);

                    }
                })
                .create();
        dialog.show();
    }
    public void handleBtnSingleChoiceDialog(View v){
        final String[] menus={"메뉴1","메뉴2","메뉴3"};
        AlertDialog dialog=new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setSingleChoiceItems(menus,1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMenu=menus[which];
                        Log.i(TAG,selectedMenu);
                        dialog.dismiss(); //다이얼로그 창닫기

                    }
                })
                .create();
        dialog.show();

    }

    public void handleBtnMultiChoiceDialog(View v){
        final String[] menus={"메뉴1","메뉴2","메뉴3","메뉴4","메뉴5","메뉴6"};
        final boolean[] selected={false,true,false,false,true,false};
        AlertDialog dialog=new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setMultiChoiceItems(menus, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which]=isChecked;
                    }
                })
                .setPositiveButton("취소",null)
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0;i<selected.length;i++){
                            if(selected[i]==true){
                                String menu=menus[i];
                                Log.i(TAG,menu+"");
                            }
                        }
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnStickProgressDialog(final View v){
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("통신 상태");
        dialog.setMessage("다운로드 중입니다.");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(1024);//다운로드 사이즈가 1메가일때
       // dialog.setProgress(500);//다운로드의 양을 계속 막대의 진행정도가 나온다.쓰레드 처리가 되어 계속 반복실행됨
        dialog.show();

        //
        Thread thread=new Thread(){
            @Override
            public void run() {
                for(int i=0;i<1024;i++) {
                    final int value=i;
                    //main 스레드 이외의 스레드는 직접 UI변경이 안되기 때문에 RUNNABLE객체 만들어서 메인스레드의 작업큐에 넣음
                    Runnable runnable=new Runnable() {
                        @Override
                        public void run() {
                            dialog.setProgress(value);// 값을 셋팅해주는 코드
                            dialog.setSecondaryProgress(value*5);//기존의 progress의 진행 막대 이외의 다른 작업을 넣을수 있음.
                    }
                };
                v.post(runnable); //ui객체의 post()를 이용하여 작업을 큐에넣음
                try {
                    Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public void handleBtnCircleProgressDialog(final View v){
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("통신 상태");
        dialog.setMessage("다운로드 중입니다.");
        dialog.show();

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {Thread.sleep(5000);} catch (InterruptedException e) {}
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                };
                v.post(runnable);
            }
        };
        thread.start();
    }

    public void handleBtnCustomDialog(View v){
        CustomDialog dialog=new CustomDialog();
        dialog.show(getSupportFragmentManager(),null);
    }

}
