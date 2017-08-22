package com.mycompany.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.util.Path;
import com.mycompany.myapplication.util.RealPathUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG="HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //앱 시작시 모든 권한 요청
        int permissionCall= ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int permissionRES= ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWES= ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCall!=PackageManager.PERMISSION_GRANTED||permissionRES!=PackageManager.PERMISSION_GRANTED||permissionWES!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }

    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==1){
            //권한 요청 목록이 1개뿐이었으므로 [0]배열을 검사
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"권한을 얻음",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"권한을 얻지 못함",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void handleBtnUIActivity(View v){

        Intent intent=new Intent(this,UIActivity.class); //(현재 액티비티,이동하고자하는 액티비티)
        //안드로이드에 화면이동을 위해 intent를 제공하는 메소드
        startActivity(intent);

    }
//해당 주소를 브라우저로 띄우기
    public void handleBtnWebBrowser(View v){
        //어떤 데이터를 가지고 있으니 보여달라 정도까지만 안드로이드에게 제공하면 안드로이드가 알아서 선택해서 띄워준다.
        //만약 안드로이드의 선택사항이 여러가지 라면 사용자에게 다이얼로 선택하도록 함
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));//(보여달라명령,제공할 정보)
        startActivity(intent);
    }
    public void handleBtnDialActivity(View v){
        //권한을 부여받는 방법
        //현재 이앱이 전화 걸수 있는 얻었느냐를 검사하는 코드
        int permission= ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(permission== PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-3040-0929"));
            startActivity(intent);
        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE},1);  //(현재액티비티,권한목록의 스트링배열,안드로이에게 요청하는 번호(나중에 요청번호가옴) )

        }


        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-123-12345"));
}
/*
// ( 안드로이에게 요청했던 번호가 여기 매개변수로 들어옴,
//해당 요청 번호를 처리함
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //아까 1번으로 요청했던 전화걸기라면
        if(requestCode==1){
            //권한 요청 목록이 1개뿐이었으므로 [0]배열을 검사
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                handleBtnDialActivity(null);
            }else{
                Toast.makeText(this,"권한을 얻지 못함",Toast.LENGTH_SHORT).show();
            }
        }
    }
*/
    public void handlebtnMapActivity(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:37.515889,127.07275?z=16")); //z는 확대레벨(1~23, 높을수록 가깝게)
        startActivity(intent);
    }

    public void handleBtnDataSend(View v){
        Intent intent = new Intent(this, DataReceiveActivity.class);
        intent.putExtra("key1",10);
        intent.putExtra("key2","안드로이드");
        Review review=new Review();
        review.setTitle("오늘은 화요일");
        intent.putExtra("key3",review); //review는 Serializable을 구현해야 intent로 전송가능
        startActivity(intent);

    }
    public void handleBtnReturnValue(View v){
        Intent intent = new Intent(this,ReturnvalueActivity.class);
        intent.putExtra("x",10);
        intent.putExtra("y",20);
        startActivityForResult(intent,1); //단순이동 시가 아닌 결과값을 받기 위하여 부르는 코드//(intent,intent 요청번호(추후에 구분하기 위한 번호))

    }

    //리턴값을 받고자 하는 액티비티가 종료 되었을때 자동실행
    //(startActivityForResult의 요청번호, 리턴값 받고자 하는 액티비티비의 setResult안의 처리결과코드,
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                int result=data.getIntExtra("result",0);
                Log.i(TAG,String.valueOf(result));
            }else{
                Log.i(TAG,"전달된 결과값이 없음!!"); // back버튼 눌렸을때 실행되는 코드
            }
        }

        //이미지파일 얻기
        if(requestCode==2){
            if(resultCode==RESULT_OK){

                Uri uri=data.getData();
                Log.i(TAG,uri.toString());
                String path = RealPathUtil.getRealPath(this, uri);
               Log.i(TAG,path);
            }
        }
    }

    public void handleBtnFileSelect(View v){

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"이미지 선택"),2); //intent.creatchooser의 리턴값은 intent


    }

//realpath를 얻기 위한곳, 선택된 경로에 대해 스마트폰 안에 있는 파일의 실제경로를 얻어내는 코드
    //미디어 파일이 저장되면 ANDROID가 빨리 찾기 위한 목적으로 MediaStore라는 데이터베이스에 저장됨. 따라서 거기에서 컬럼값을 얻고 경로를 찾아서 리턴하는 메소드
    public String getRealPath(Uri uri) {
        String realPath = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            realPath = cursor.getString(column_index);
        }
        cursor.close();
        return realPath;
    }




}
