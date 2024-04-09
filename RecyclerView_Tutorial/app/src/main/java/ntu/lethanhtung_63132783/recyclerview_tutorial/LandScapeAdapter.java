package ntu.lethanhtung_63132783.recyclerview_tutorial;

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

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder > {
    Context context;
    ArrayList<LandScape> listData;

    public LandScapeAdapter(Context context, ArrayList<LandScape> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Bom = LayoutInflater.from(context);
        View viewItem = Bom.inflate(R.layout.item_landscape,parent,false);
        ItemLandHolder ViewholderCreated = new ItemLandHolder(viewItem);
        return ViewholderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
    //Lấy đối tượng để hiển thị
        LandScape landScapeShow = listData.get(position);
        //Lấy thông tin
        String caption = landScapeShow.getLandCaption();
        String tenAnh = landScapeShow.getLandImageFileName();
        //Đặt vào các trường thông tin của holder
        holder.tvCaption.setText(caption);
        //Đặt ảnh
        String packageName = holder.itemView.getContext().getPackageName();
        //Lấy id của ảnh
        int imageID = holder.itemView.getResources().getIdentifier(tenAnh,"mipmap",packageName);
        holder.imgViewLandScape.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    //Xây dựng lớp ViewHolder trước
    static class ItemLandHolder extends RecyclerView.ViewHolder{
        TextView tvCaption;
        ImageView imgViewLandScape;


    public ItemLandHolder(@NonNull View itemView) {
        super(itemView);
        tvCaption = itemView.findViewById(R.id.textViewCaption);
        imgViewLandScape = itemView.findViewById(R.id.imageViewLand);
        }
    }
}
