package com.example.ontap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YoutuberAdapter extends RecyclerView.Adapter<YoutuberAdapter.YoutuberItemViewHolder> {
    Context context;
    ArrayList<youtuber> listYoutuber = new ArrayList<youtuber>();

    public YoutuberAdapter(Context context, ArrayList<youtuber> listYoutuber) {
        this.context = context;
        this.listYoutuber = listYoutuber;
    }

    @NonNull
    @Override
    public YoutuberItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View iview = inflater.inflate(R.layout.youtuber_item,parent,false);
        YoutuberItemViewHolder holderCreate = new YoutuberItemViewHolder(iview);
        return holderCreate;
    }

    @Override
    public void onBindViewHolder(@NonNull YoutuberItemViewHolder holder, int position) {
        youtuber youtuberShow = listYoutuber.get(position);

        //Trích thông tin sẵn
        String YoutuberName = youtuberShow.getYoutuber_name();
        String YoutuberImg = youtuberShow.getYoutuber_Img();

        holder.YoutuberName.setText(YoutuberName);

        //Về phần hình ảnh
        //Lấy package
        String packageName = holder.itemView.getContext().getPackageName();

        //Lấy id cho ảnh
        int imageID = holder.itemView.getResources().getIdentifier(YoutuberImg,"mipmap",packageName);
        holder.Youtuber_Img.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return listYoutuber.size();
    }

    class YoutuberItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView YoutuberName;
        ImageView Youtuber_Img;

        public YoutuberItemViewHolder(@NonNull View itemView) {
            super(itemView);
            YoutuberName = itemView.findViewById(R.id.YoutuberName);
            Youtuber_Img = itemView.findViewById(R.id.Youtuberimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Lấy vị trí
            int vitriClick = getAdapterPosition();
            youtuber youtuberDuocChon = listYoutuber.get(vitriClick);
            //Bơm data
            String YoutuberName = youtuberDuocChon.getYoutuber_name();
            String YoutuberImg = youtuberDuocChon.getYoutuber_Img();
            //Toast
            Toast.makeText(v.getContext(), "Bạn vừa nhân"+ YoutuberName, Toast.LENGTH_SHORT).show();

            Intent iChuyenThongTin  = new Intent(v.getContext(),Activity_ChuyenThongTin.class);
            iChuyenThongTin.putExtra("youtuberName",YoutuberName);
            iChuyenThongTin.putExtra("youtuberImg",YoutuberImg);
            v.getContext().startActivity(iChuyenThongTin);

        }
    }
    }
