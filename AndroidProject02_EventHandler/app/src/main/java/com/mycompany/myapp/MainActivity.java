package com.mycompany.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private ImageView imgTitle;
    private Button btnImg1;
    private Button btnImg2;
    private RadioButton rbImg1,rbImg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //xml이 구현되어 객체가 만들어지고

        imgTitle=(ImageView)findViewById(R.id.imgTitle); // id를 찾아서 객체가져옴
        btnImg1=(Button)findViewById(R.id.btnImg1);
        btnImg2=(Button)findViewById(R.id.btnImg2);
        rbImg1=(RadioButton)findViewById(R.id.rbImg1);
        rbImg2=(RadioButton)findViewById(R.id.rbImg2);

        btnImg1.setOnClickListener(handleBtnImg); //익명객체를 만들어도 되지만 길어지기 때문에 필드로 선언함
        btnImg2.setOnClickListener(handleBtnImg);
        rbImg1.setOnClickListener(handleBtnImg);
        rbImg2.setOnClickListener(handleBtnImg);
    }

    //view는 모든위젯의 최상위 클래스로 해당 위젯을 클릭했을때 그 위젯이 어떤것이든 매개변수로
    //받을수 있도록 View v 로 onclick 메소드가 매개변수를 받는다.
    private View.OnClickListener handleBtnImg= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==btnImg1||v==rbImg1) {
                imgTitle.setImageResource(R.drawable.photo1);
            }else if(v==btnImg2||v==rbImg2){
                imgTitle.setImageResource(R.drawable.photo2);
            }
        }
    };


    //onClick 에서 지정한 이름과 (View v)를 매개변수로 한 메소드 만들면 이벤트 처리 가능
    public void handleBtnImg3(View v){
        imgTitle.setImageResource(R.drawable.photo3);
    }
    public void handleRbImg3(View v){
        imgTitle.setImageResource(R.drawable.photo3);
    }
}
