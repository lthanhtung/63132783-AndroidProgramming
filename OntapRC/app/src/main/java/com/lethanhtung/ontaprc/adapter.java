package com.lethanhtung.ontaprc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.holder> {

    Context context;
    ArrayList<youtuber> listyoutuber = new ArrayList<youtuber>();

    public adapter(Context context, ArrayList<youtuber> listyoutuber) {
        this.context = context;
        this.listyoutuber = listyoutuber;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.youtuberitem,parent,false);
        holder CreateHolder = new holder(itemView);

        return CreateHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        youtuber youtuberShow = listyoutuber.get(position);
        String ten = youtuberShow.getTen();
        String anh = youtuberShow.getImg();

        holder.ten.setText(ten);
        //Ảnh
        String packageName = holder.itemView.getContext().getPackageName();
        int ImgId = holder.itemView.getResources().getIdentifier(anh,"mipmap",packageName);
        holder.img.setImageResource(ImgId);
    }

    @Override
    public int getItemCount() {
        return listyoutuber.size();
    }

    class holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView ten;

        public holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.Youtuberimage);
            ten = itemView.findViewById(R.id.YoutuberName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int vitrClick = getAdapterPosition();
            youtuber youtuberDuocClick = listyoutuber.get(vitrClick);

            String ten = youtuberDuocClick.getTen();
            String img = youtuberDuocClick.getImg();
            Toast.makeText(v.getContext(), "Bạn vừa nhấn" + ten, Toast.LENGTH_SHORT).show();
        }
    }
}
