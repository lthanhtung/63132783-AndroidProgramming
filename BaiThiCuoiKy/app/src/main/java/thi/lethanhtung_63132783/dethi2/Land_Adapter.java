package thi.lethanhtung_63132783.dethi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Land_Adapter extends RecyclerView.Adapter<Land_Adapter.ViewHolder_Land>{
    //Tạo context và một danh sách lưu trữ
    Context context;
    ArrayList<LandScape> ListData;

    public Land_Adapter(Context context, ArrayList<LandScape> listData) {
        this.context = context;
        ListData = listData;
    }

    @NonNull
    @Override
    public ViewHolder_Land onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Tạo LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(context);
        //Tạo view và sử dụng inflater
        View Iview = inflater.inflate(R.layout.land_item,parent,false);
        //Khởi tạo ViewHolder
        ViewHolder_Land viewHolderCreate = new ViewHolder_Land(Iview);
        return viewHolderCreate;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Land holder, int position) {
        //tạo một biến LandScape
        LandScape landScape = ListData.get(position);
        //Khai báo các biến tương ứng với điều khiển và sử dụng LandScape lên từng biến
        String TieuDe = landScape.getTieuDe();
        String TenAnh = landScape.getTenAnh();
        //Đặt tên ảnh cho các điều khiển trong holder
        holder.TieuDe.setText(TieuDe);
        //Đối với ảnh thì khó hơn
        //Tạo 1 biến là package name nhớ holder.itemview và tìm package name cho ảnh : chú ý đối với String thì dùng getContext
        String PackageName = holder.itemView.getContext().getPackageName();
        //Tạo 1 biến là imgID để lấy Id cho ảnh: chú ý: đối với kiểu số là getResourdt
        int imgID = holder.itemView.getResources().getIdentifier(TenAnh,"mipmap",PackageName);
        //Đặt ảnh
        holder.img.setImageResource(imgID);
    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }


    class ViewHolder_Land extends RecyclerView.ViewHolder{
        TextView TieuDe;
        ImageView img;

        public ViewHolder_Land(@NonNull View itemView) {
            super(itemView);
            TieuDe = itemView.findViewById(R.id.TieuDe);
            img = itemView.findViewById(R.id.image);
        }
    }
}
