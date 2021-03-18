package com.hog2020.ex59recyclerview2;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdaper extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public MyAdaper(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);

        View itemView=inflater.inflate(R.layout.recycler_item,parent,false);
        VH holder= new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH) holder;

        //대입할 데이터 요소 객체(item)
        Item item=items.get(position);
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.msg);

        //이미지설정-gif,network 를 편하게 할 수 있는 외부 라이브러리사용: Glide or Picasso
        //vh.ivIcon.setImageResource(item.icon);//gif 파일은 움직이지 못함
        Glide.with(context).load(item.icon).into(vh.ivIcon);
        Glide.with(context).load(item.img).into(vh.ivImg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //아이템뷰 안에 있는 뷰들의 참조변수를 멤버로 가지는 클래스
    class VH extends RecyclerView.ViewHolder{

        CircleImageView ivIcon;
        TextView tvName;
        TextView tvMsg;
        ImageView ivImg;

        public VH(@NonNull View itemView) {
            super(itemView);

            ivIcon=itemView.findViewById(R.id.iv_icon);
            tvName=itemView.findViewById(R.id.tv_name);
            tvMsg =itemView.findViewById(R.id.tv_msg);
            ivImg=itemView.findViewById(R.id.iv_img);

            //아이템뷰를 클릭했을때
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클릭한 항목 위치(index) 알아내기
                    int position=getLayoutPosition();
                    //그 선택된 항목의 상세내역을 보여주는 새로운 화면을 실행
                    //선택된 아이템 name,img 정보를 전달해주기
                    String name=items.get(position).name;
                    int imgId=items.get(position).img;

                    Intent intent =new Intent(context,DetailActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("imgId",imgId);


                    //전환 효과[LolliPop 버전 이상]
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity) context,new Pair<View,String>(ivImg,"img"));
                        context.startActivity(intent,options.toBundle());
                    }else{

                        context.startActivity(intent);

                    }

                }
            });


            //아이템 롱클릭 리스너 설정
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position= getLayoutPosition();
                    items.remove(position);
                    notifyItemRemoved(position);

                    return true;
                }
            });
        }
    }
}
