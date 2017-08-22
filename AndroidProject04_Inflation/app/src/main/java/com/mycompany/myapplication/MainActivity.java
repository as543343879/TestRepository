package com.mycompany.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String Tag = "MainActivity";

    private LinearLayout linearLayoutTop;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop=(LinearLayout)findViewById(R.id.linearLayoutTop);
        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);
    }

    public void handleRadioButton1(View v){
        linearLayoutTop.setBackgroundResource(R.drawable.photo1);

    }
    public void handleRadioButton2(View v){
        linearLayoutTop.setBackgroundResource(R.drawable.photo2);
    }
    public void handleRadioButton3(View v){
        linearLayoutTop.setBackgroundResource(R.drawable.photo3);
    }


    public void handleButton1(View v){

        frameLayout.removeAllViews();
        Content1 content1=new Content1(this);
        frameLayout.addView(content1);
        for(int i=1;i<=10;i++) {
            Item1 item = new Item1();
            item.setPhoto(getResources().getIdentifier("member"+i,"drawable",getPackageName())); //패키지에서 해당 이름을 찾아라
            item.setTitle("반복학습" + i);
            //item.setStar(R.drawable.star10);
            item.setStar(getResources().getIdentifier("star"+i,"drawable",getPackageName()));
            item.setContent("이거너무쉽다.????????");
            content1.addItem(item);
        }
/*
        //인플레이터를 얻는 세가지 방법
        //첫번째
       // LayoutInflater inflater=getLayoutInflater();
        //두번째
       // LayoutInflater inflater=LayoutInflater.from(this);
        //세번째(현재가 액티비티가 아닌페이지면 이방법을 사용)
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//------------------------------------------------------------------------------------------------------------------

        //inflater를 이용하여 inflate 메소드를 호출하는 세가지방법
        //첫번째방법
       // View view=inflater.inflate(R.layout.content1,null); // 해당파일을 해석하여 모든 태그들을 객체로 만듬, 부모가 널이기때문에 최상위 layout을 리턴
      //  frameLayout.addView(view);

        //두번째 방법
        // View view=inflater.inflate(R.layout.content1,frameLayout); //파싱하고 만들어진 객체결과를 부모에 추가해줌줌 , 부모를 리턴해준다.
        //세번째 방법
        View view=inflater.inflate(R.layout.content1,frameLayout,false); //부모가 제공되었지만 부모에 넣지는 않겠다.

        Log.i(Tag,String.valueOf(frameLayout.getChildCount()));
*/

    }
   public void handleButton2(View v){

    }
    public void handleButton3(View v){

    }
}
