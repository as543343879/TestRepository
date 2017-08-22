package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mycompany.myapplication.R;

public class ReturnvalueActivity extends AppCompatActivity {
    private TextView txtY;
    private TextView txtX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnvalue);
        txtX=(TextView)findViewById(R.id.txtX);
        txtY=(TextView)findViewById(R.id.txtY);
        Intent intent=getIntent();
        txtX.setText(String.valueOf(intent.getIntExtra("x",0)));
        txtY.setText(String.valueOf(intent.getIntExtra("y",0)));
    }
    public void handleBtnCalcValueReturn(View v){
        int x=Integer.parseInt(txtX.getText().toString());
        int y=Integer.parseInt(txtY.getText().toString());
        int result=x+y;


        Intent resultIntent=new Intent();//다시 돌아가는 것이기 때문에 인텐트의 매개변수를 안넣어도 된다.
        resultIntent.putExtra("result",result);
        setResult(RESULT_OK,resultIntent); //결과를 셋팅하는것 , (성공적으로 계산이 되었다, 성공된 계산값(전달될값))

        finish();//다시 넘어오기전 화면으로 돌아가기 위하여 강제로 액티비티를 죽인다.
    }
    //back 버튼이 눌렸을때 자동실행
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed(); //해당 부모의 onbackpressed에 back버튼이 눌렸을 시에 종료 시키는 코드가 있음
    }
}
