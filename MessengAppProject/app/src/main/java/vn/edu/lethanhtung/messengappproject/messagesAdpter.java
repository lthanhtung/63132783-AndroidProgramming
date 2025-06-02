package vn.edu.lethanhtung.messengappproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class messagesAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<msgModelclass> messagesAdpterArrayList;
    String reciverImageUrl;

    public messagesAdpter(Context context, ArrayList<msgModelclass> messagesAdpterArrayList, String reciverImageUrl) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
        this.reciverImageUrl = reciverImageUrl;
        setHasStableIds(true);
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
        String time = formatTimestamp(messages.getTimeStamp());

        if (holder instanceof senderViewHolder) {
            senderViewHolder viewHolder = (senderViewHolder) holder;
            viewHolder.timestamp.setText(time);

            // Hiển thị thông báo đã chỉnh sửa
            if (messages.isEdited()) {
                viewHolder.tvEdited.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvEdited.setVisibility(View.GONE);
            }

            if (messages.getImageUrl() != null) {
                viewHolder.msgtxt.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(messages.getImageUrl())
                        .placeholder(R.drawable.man)
                        .error(R.drawable.man)
                        .into(viewHolder.imageView);

                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ImageViewerActivity.class);
                        intent.putExtra("image_url", messages.getImageUrl());
                        context.startActivity(intent);
                    }
                });
            } else {
                viewHolder.msgtxt.setVisibility(View.VISIBLE);
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.msgtxt.setText(messages.getMessage());

                viewHolder.msgtxt.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (context instanceof chatWin) {
                            ((chatWin) context).showMessageOptionsDialog(messagesAdpterArrayList.get(position), v);
                        }
                        return true;
                    }
                });

                viewHolder.msgtxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context instanceof chatWin) {
                            ((chatWin) context).showReactionPopup(v, messages, position);
                        }
                    }
                });

                // Hiển thị icon cảm xúc nếu có
                if (messages.getReaction() != null && !messages.getReaction().isEmpty()) {
                    int resId = 0;
                    switch (messages.getReaction()) {
                        case "like": resId = R.drawable.ic_like; break;
                        case "love": resId = R.drawable.ic_love; break;
                        case "haha": resId = R.drawable.ic_haha; break;
                        case "wow": resId = R.drawable.ic_wow; break;
                        case "sad": resId = R.drawable.ic_sad; break;
                        case "angry": resId = R.drawable.ic_angry; break;
                    }
                    if (resId != 0) {
                        viewHolder.reactionIcon.setImageResource(resId);
                        viewHolder.reactionIcon.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.reactionIcon.setVisibility(View.GONE);
                    }
                } else {
                    viewHolder.reactionIcon.setVisibility(View.GONE);
                }
            }
        } else {
            reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.timestamp.setText(time);

            // Hiển thị thông báo đã chỉnh sửa
            if (messages.isEdited()) {
                viewHolder.tvEdited.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvEdited.setVisibility(View.GONE);
            }

            if (messages.getImageUrl() != null) {
                viewHolder.msgtxt.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(messages.getImageUrl())
                        .placeholder(R.drawable.man)
                        .error(R.drawable.man)
                        .into(viewHolder.imageView);

                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ImageViewerActivity.class);
                        intent.putExtra("image_url", messages.getImageUrl());
                        context.startActivity(intent);
                    }
                });
            } else {
                viewHolder.msgtxt.setVisibility(View.VISIBLE);
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.msgtxt.setText(messages.getMessage());

                if (reciverImageUrl != null) {
                    Picasso.get()
                            .load(reciverImageUrl)
                            .placeholder(R.drawable.man)
                            .error(R.drawable.man)
                            .resize(100, 100)
                            .centerCrop()
                            .into(viewHolder.circleImageView);
                }

                viewHolder.msgtxt.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (context instanceof chatWin) {
                            ((chatWin) context).showMessageOptionsDialog(messagesAdpterArrayList.get(position), v);
                        }
                        return true;
                    }
                });

                viewHolder.msgtxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context instanceof chatWin) {
                            ((chatWin) context).showReactionPopup(v, messages, position);
                        }
                    }
                });

                // Hiển thị icon cảm xúc nếu có
                if (messages.getReaction() != null && !messages.getReaction().isEmpty()) {
                    int resId = 0;
                    switch (messages.getReaction()) {
                        case "like": resId = R.drawable.ic_like; break;
                        case "love": resId = R.drawable.ic_love; break;
                        case "haha": resId = R.drawable.ic_haha; break;
                        case "wow": resId = R.drawable.ic_wow; break;
                        case "sad": resId = R.drawable.ic_sad; break;
                        case "angry": resId = R.drawable.ic_angry; break;
                    }
                    if (resId != 0) {
                        viewHolder.reactionIcon.setImageResource(resId);
                        viewHolder.reactionIcon.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.reactionIcon.setVisibility(View.GONE);
                    }
                } else {
                    viewHolder.reactionIcon.setVisibility(View.GONE);
                }
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
        return messagesAdpterArrayList.get(position).getTimeStamp();
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    class senderViewHolder extends RecyclerView.ViewHolder {
        TextView msgtxt;
        TextView timestamp;
        ImageView imageView;
        TextView tvEdited;
        ImageView reactionIcon;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);
            msgtxt = itemView.findViewById(R.id.msgsendertyp);
            timestamp = itemView.findViewById(R.id.sender_timestamp);
            imageView = itemView.findViewById(R.id.sender_image);
            tvEdited = itemView.findViewById(R.id.tv_edited);
            reactionIcon = itemView.findViewById(R.id.reaction_icon);
        }
    }

    class reciverViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;
        TextView timestamp;
        ImageView imageView;
        TextView tvEdited;
        ImageView reactionIcon;

        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.pro);
            msgtxt = itemView.findViewById(R.id.recivertextset);
            timestamp = itemView.findViewById(R.id.reciver_timestamp);
            imageView = itemView.findViewById(R.id.reciver_image);
            tvEdited = itemView.findViewById(R.id.tv_edited);
            reactionIcon = itemView.findViewById(R.id.reaction_icon);
        }
    }
}