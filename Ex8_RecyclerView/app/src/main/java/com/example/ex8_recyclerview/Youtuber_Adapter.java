package com.example.ex8_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Youtuber_Adapter extends RecyclerView.Adapter<Youtuber_Adapter.YoutuberItemViewHolder>{
    Context context;
    ArrayList<Youtuber> listdata;

    public Youtuber_Adapter(Context context, ArrayList<Youtuber> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public YoutuberItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewItem = layoutInflater.inflate(R.layout.youtuberitem, parent,false);
        YoutuberItemViewHolder viewholderCreate = new YoutuberItemViewHolder(viewItem);
        return viewholderCreate;
    }

    @Override
    public void onBindViewHolder(@NonNull YoutuberItemViewHolder holder, int position) {
    //Lấy đối tượng cần hiển thị
        Youtuber youtuberShow = listdata.get(position);

        //Trích sẵn thông tin
        String youtuberName = youtuberShow.getYoutuberName();
        String youtuberImg = youtuberShow.getYoutuberImg();

        //Đăt vào các trường thông tin vào holder
        holder.YoutuberName.setText(youtuberName);

        //Đặt ảnh ( Hơi phức tạp)
        String PackageName =  holder.itemView.getContext().getPackageName();

        //Lấy về id cho ảnh
        int imageId = holder.itemView.getResources().getIdentifier(youtuberImg,"mipmap",PackageName);
        holder.YoutuberImg.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class YoutuberItemViewHolder extends RecyclerView.ViewHolder{
        TextView YoutuberName;
        ImageView YoutuberImg;

        public YoutuberItemViewHolder(@NonNull View itemView) {
            super(itemView);

            //Tìm điều khiển tương ứng
            YoutuberName = itemView.findViewById(R.id.YoutuberName);
            YoutuberImg = itemView.findViewById(R.id.YoutuberImg);

        }

        //Override các phương thức của Adapter

    }
}
