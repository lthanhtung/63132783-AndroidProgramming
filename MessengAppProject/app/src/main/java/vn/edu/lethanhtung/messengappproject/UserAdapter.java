package vn.edu.lethanhtung.messengappproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewholder> {

    MainActivity mainActivity;
    ArrayList<Users> usersArrayList;

    public UserAdapter(MainActivity mainActivity, ArrayList<Users> usersArrayList) {
        this.mainActivity = mainActivity;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.user_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewholder holder, int position) {
        Users users = usersArrayList.get(position);
        holder.username.setText(users.userName);
        holder.userstatus.setText(users.status);
        Picasso.get().load(users.profilepic).into(holder.userimg);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, chatWin.class);
            intent.putExtra("nameeee", users.getUserName());
            intent.putExtra("reciverImg", users.getProfilepic());
            intent.putExtra("uid", users.getUserId());
            mainActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public void setFilteredList(ArrayList<Users> filteredList) {
        this.usersArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        CircleImageView userimg;
        TextView username;
        TextView userstatus;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            userimg = itemView.findViewById(R.id.userimg);
            username = itemView.findViewById(R.id.username);
            userstatus = itemView.findViewById(R.id.userstatus);
        }
    }
}
