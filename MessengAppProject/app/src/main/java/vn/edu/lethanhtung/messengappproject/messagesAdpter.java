package vn.edu.lethanhtung.messengappproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class messagesAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<msgModelclass> messagesAdpterArrayList;
    String reciverImageUrl;

    public messagesAdpter(Context context, ArrayList<msgModelclass> messagesAdpterArrayList, String reciverImageUrl) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
        this.reciverImageUrl = reciverImageUrl;
        setHasStableIds(true); // Cải thiện hiệu suất cuộn
    }

    int ITEM_SEND = 1;
    int ITEM_RECIVE = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false);
            return new senderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
            return new reciverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        msgModelclass messages = messagesAdpterArrayList.get(position);
        if (holder instanceof senderViewHolder) {
            senderViewHolder viewHolder = (senderViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());
        } else {
            reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());
            if (reciverImageUrl != null) {
                Picasso.get()
                        .load(reciverImageUrl)
                        .placeholder(R.drawable.man)
                        .error(R.drawable.man)
                        .resize(100, 100) // Resize để tối ưu
                        .centerCrop()
                        .into(viewHolder.circleImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return messagesAdpterArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        msgModelclass messages = messagesAdpterArrayList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderid())) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }

    @Override
    public long getItemId(int position) {
        return messagesAdpterArrayList.get(position).getTimeStamp(); // ID duy nhất cho mỗi tin nhắn
    }

    class senderViewHolder extends RecyclerView.ViewHolder {
        TextView msgtxt;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            msgtxt = itemView.findViewById(R.id.msgsendertyp);
        }
    }

    class reciverViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;

        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.pro);
            msgtxt = itemView.findViewById(R.id.recivertextset);
        }
    }
}