package com.hog2020.ex59recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdaper adaper;

    ArrayList<Item> items= new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가[실무에서는 DB or Network 서버에서 데이터를 읽어옴]
        items.add(new Item("루피","해적단 선장",R.drawable.ch_luffy,R.drawable.img02));
        items.add(new Item("조로","해적단 부선장",R.drawable.ch_zoro,R.drawable.img03));
        items.add(new Item("나미","해적단 항해사",R.drawable.ch_nami,R.drawable.img04));
        items.add(new Item("우솝","해적단 저격수",R.drawable.ch_usoup,R.drawable.img05));
        items.add(new Item("상디","해적단 요리사",R.drawable.ch_sandi,R.drawable.moana));
        items.add(new Item("쵸파","해적단 의사",R.drawable.ch_chopa,R.drawable.winter));

        recyclerView=findViewById(R.id.recycler);
        adaper=new MyAdaper(this,items);
        recyclerView.setAdapter(adaper);

        //리사이클러뷰는 리스트뷰와 다르게 아이템클릭리스너가 없음
        //아답터에서 itemView 에 직접 클릭리스너 설정해 주어야함

    }

    public void clickAdd(View view) {
        //새로운 아이템 추가(리사이클러뷰에 하는 것이 아니라 ArrayList 에 추가)
//        items.add(new Item("New","Message",R.drawable.ch_sandi,R.drawable.img01));
//        //아답터에게 새로운 데이터가 추가 되었다고 공지- 자동 갱신
//        //adaper.notifyDataSetChanged();//1개 추가 되었지만 전체를 다시 갱신함(비효율적)
//        //새로운 아이템 1개가 추가되었다고 공지
//        adaper.notifyItemInserted(items.size()-1);
//        //강제로 리사이클뷰 스크롤위치를 조정
//        recyclerView.scrollToPosition(items.size()-1);

        //보통 새로 추가된 아이템은 첫번째(index:0)로 추가되는경우가 더많다(최신순)
        items.add(0,new Item("New", "message",R.drawable.ch_luffy,R.drawable.img06));
        adaper.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);

    }

    public void clickdelete(View view) {
        if(items.size()==0)return;

        //리사이클러뷰의 아이템뷰를 삭제하는 것이 아니라 Arraylist의 요소제거
        items.remove(0);
        adaper.notifyItemRemoved(0);
    }

    public void clicklinear(View view) {
        //리사이클러뷰의 배치관리자(LayoutManager)
        LinearLayoutManager layoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void clickgrid(View view) {
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }
}