package com.mycompany.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mycompany.myapplication.content.CacaoList;
import com.mycompany.myapplication.content.FoodList;
import com.mycompany.myapplication.content.ReviewList;
import com.mycompany.myapplication.dto.Cacao;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.R;

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
        ReviewList reviewList=new ReviewList(this);
        frameLayout.addView(reviewList);

        for(int i=1;i<=10;i++) {
            Review item = new Review();
            item.setPhoto(R.drawable.member2);
            item.setTitle("리스트뷰와 어댑터...후");
            item.setStar(R.drawable.star10);
            item.setContent("어댑터가뭐? item XML를 Inflation해서 데이터를 세팅한 다음 리스트뷰에 제공하는 역할을 한다");
            reviewList.addItem(item);
        }
    }
   public void handleButton2(View v){
       frameLayout.removeAllViews();
       FoodList foodList=new FoodList(this);
       frameLayout.addView(foodList);
       for(int i=1;i<=6;i++) {
           Food food=new Food();
           food.setFno(i);
           food.setFname("음식종류 :"+i);
           food.setFphoto(getResources().getIdentifier("food"+i,"drawable",getPackageName()));
           food.setFstar(getResources().getIdentifier("star"+i,"drawable",getPackageName()));
           food.setFdesc("한국음식 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
           foodList.addItem(food);
       }


    }
    public void handleButton3(View v){
        frameLayout.removeAllViews();
        CacaoList cacaoList=new CacaoList(this);
        frameLayout.addView(cacaoList);

        for(int i=1;i<=6;i++) {
            Cacao cacao=new Cacao();
            cacao.setcNo(i);
            cacao.setcName("프로도");
            cacao.setcStar(getResources().getIdentifier("star"+i,"drawable",getPackageName()));
            cacao.setcPhoto(getResources().getIdentifier("friends"+i,"drawable",getPackageName()));
            cacao.setcContent("zzz");
            cacaoList.addItem(cacao);
        }



    }


}
