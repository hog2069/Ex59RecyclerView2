package com.hog2020.ex59recyclerview2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv=findViewById(R.id.iv);

        //Intent 로 부터 전달된 추가데이터 받기
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        int imgId=intent.getIntExtra("imgId",R.drawable.img01);

        //name은 제목줄에 제목으로 표시
        getSupportActionBar().setTitle(name+"");

        //이미지 설정 - Glide 라이브 러리 사용
        Glide.with(this).load(imgId).into(iv);

        //iv에게 Transition
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            iv.setTransitionName("img");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    //업버튼 클릭 반응하기 -사실업버튼은 일종의 옵션메뉴 아이템과 같은 것임

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        if(item.getItemId()==android.R.id.home){
            //디바이스의 뒤로가기 버튼(back 버튼)을 누른 것처럼
            //finish();//그냥 액티비티를 종료시키는 메소드(back과 기능이 다름)
            super.onBackPressed();//액티비티의 back 버튼 클릭시 발동하는 콜백메소드를 강제로 호출
        }

        return super.onOptionsItemSelected(item);
    }

}