package com.mycompany.myapplication.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Cacao;

import java.util.ArrayList;
import java.util.List;


public class CacaoList extends LinearLayout {
    ListView listView;
    private ItemAdapter itemAdapter;
    private List<Cacao> list=new ArrayList<>();

    public CacaoList(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        listView=(ListView)inflater.inflate(R.layout.cacao_list,null);
        itemAdapter=new ItemAdapter();
        listView.setAdapter(itemAdapter);
        addView(listView);
    }
    class ItemAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return list.get(position).getcNo();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView= inflater.inflate(R.layout.cacao_item, null);
            }

            ImageView cPhoto=(ImageView)convertView.findViewById(R.id.cphoto);
            TextView cName=(TextView)convertView.findViewById(R.id.cname);
            ImageView cStar=(ImageView)convertView.findViewById(R.id.cstar);
            TextView cContent=(TextView)convertView.findViewById(R.id.ccontent);

            Cacao cacao=list.get(position);
            cPhoto.setImageResource(cacao.getcPhoto());
            cName.setText(cacao.getcName());
            cStar.setImageResource(cacao.getcStar());
            cContent.setText(cacao.getcContent());


            return convertView;
        }
    }

    public void addItem(Cacao item){
        list.add(item);
        //데이터 변경시에 어댑터에게 알려주기 위한 코드
        itemAdapter.notifyDataSetChanged();
    }
    public void removeItem(Cacao item){
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }
}
