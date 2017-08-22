package com.mycompany.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Content1 extends LinearLayout{
    private Context context;

    private LinearLayout itemContaier;

    //context는 액티비티
    public Content1(Context context) {
        super(context);
        this.context=context;
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater=LayoutInflater.from(context);
       // View view = inflater.inflate(R.layout.content1,null);
       // this.addView(view);
        inflater.inflate(R.layout.content1,this);
        itemContaier=(LinearLayout)findViewById(R.id.itemContainer);
    }

    //인플레이션 한 후에 데이터 입력하고 contatiner에 추가
    public void addItem(Item1 item){
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.content1_item,null);//해당 객체가 가지고있는 컨텐트들의 id를 잡아오기 위해선 해당 컨테이너를 리턴 받아야함 따라서 바로 부모를 넣지 말고 null을 입력

        ImageView photo=(ImageView)view.findViewById(R.id.photo); //추가한 뷰안에서만 id검색
        photo.setImageResource(item.getPhoto());

        TextView title=(TextView)view.findViewById(R.id.title);
        title.setText(item.getTitle());

        ImageView star=(ImageView)view.findViewById(R.id.star);
        star.setImageResource(item.getStar());

        TextView content=(TextView)view.findViewById(R.id.content);
        content.setText(item.getContent());

        itemContaier.addView(view);

    }
}
