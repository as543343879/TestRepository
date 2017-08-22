package com.mycompany.myapplication.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.mycompany.myapplication.fragment.FoodListFragment;
import com.mycompany.myapplication.fragment.ReviewListFragment;

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
        ReviewListFragment rf=new ReviewListFragment();

        FragmentManager fm=getSupportFragmentManager();
        //트랜잭션 작업시작
        FragmentTransaction ft=fm.beginTransaction();
        //
        ft.replace(R.id.frameLayout,rf);
        //모든 작업이 다 성공하면 커밋해라
        ft.commit();

    }

   public void handleButton2(View v){
       frameLayout.removeAllViews();
       FoodListFragment ff=new FoodListFragment();

       FragmentManager fm=getSupportFragmentManager();
       //트랜잭션 작업시작
       FragmentTransaction ft=fm.beginTransaction();
       //
       ft.replace(R.id.frameLayout,ff);
       //모든 작업이 다 성공하면 커밋해라
       ft.commit();


    }
    public void handleButton3(View v){
        frameLayout.removeAllViews();
        CacaoList cacaoList=new CacaoList(this);
        frameLayout.addView(cacaoList);

        for(int i=1;i<=6;i++) {
            Cacao cacao=new Cacao();
            cacao.setcNo(i);
            if(i==1){
                cacao.setcName("어피치");
                cacao.setcContent("코냥이");
            }else if(i==2){
                cacao.setcName("튜브");
                cacao.setcContent("오리색히");

            }else if(i==3){
                cacao.setcName("무지");
                cacao.setcContent("단무지");

            }else if(i==4){
                cacao.setcName("네오");
                cacao.setcContent("고양이");

            }else if(i==5){
                cacao.setcName("프로도");
                cacao.setcContent("개시키");

            }else if(i==6){
                cacao.setcName("제이지");
                cacao.setcContent("하하");

            }

            cacao.setcStar(getResources().getIdentifier("star"+i,"drawable",getPackageName()));
            cacao.setcPhoto(getResources().getIdentifier("friends"+i,"drawable",getPackageName()));

            cacaoList.addItem(cacao);
        }



    }


}
