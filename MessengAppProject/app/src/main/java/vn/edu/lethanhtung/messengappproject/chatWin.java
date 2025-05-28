package vn.edu.lethanhtung.messengappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatWin extends AppCompatActivity {

    String reciverimg, reciverUid, reciverName, SenderUID;
    CircleImageView profile;
    TextView reciverNName;
    CardView sendbtn;
    EditText textmsg;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    String senderRoom, reciverRoom;
    RecyclerView mmessangesAdpter;
    ArrayList<msgModelclass> messagessArrayList;
    messagesAdpter messagesAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_win);

        // Khởi tạo Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Lấy dữ liệu từ Intent
        reciverName = getIntent().getStringExtra("nameeee");
        reciverimg = getIntent().getStringExtra("reciverImg");
        reciverUid = getIntent().getStringExtra("uid");

        // Khởi tạo giao diện
        sendbtn = findViewById(R.id.sendbtnn);
        textmsg = findViewById(R.id.textmsg);
        profile = findViewById(R.id.profileimgg);
        reciverNName = findViewById(R.id.recivername);

        // Tải ảnh hồ sơ và tên của người nhận
        Picasso.get()
                .load(reciverimg)
                .placeholder(R.drawable.man) // Thêm placeholder
                .error(R.drawable.man) // Thêm ảnh lỗi
                .resize(100, 100) // Resize để tối ưu
                .centerCrop()
                .into(profile);
        reciverNName.setText(reciverName);

        // Thiết lập phòng chat
        SenderUID = firebaseAuth.getUid();
        senderRoom = SenderUID + reciverUid;
        reciverRoom = reciverUid + SenderUID;

        // Khởi tạo RecyclerView và Adapter
        mmessangesAdpter = findViewById(R.id.msgadpter);
        messagessArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mmessangesAdpter.setLayoutManager(linearLayoutManager);
        mmessangesAdpter.setNestedScrollingEnabled(false);
        mmessangesAdpter.setItemAnimator(null);
        messagesAdpter = new messagesAdpter(chatWin.this, messagessArrayList, reciverimg); // Truyền reciverimg
        mmessangesAdpter.setAdapter(messagesAdpter);

        // Tham chiếu tin nhắn
        DatabaseReference chatreference = database.getReference().child("chats").child(senderRoom).child("messages");

        // Lắng nghe tin nhắn
        chatreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int oldSize = messagessArrayList.size();
                ArrayList<msgModelclass> newMessages = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    msgModelclass message = dataSnapshot.getValue(msgModelclass.class);
                    newMessages.add(message);
                }
                if (newMessages.size() != oldSize) {
                    messagessArrayList.clear();
                    messagessArrayList.addAll(newMessages);
                    messagesAdpter.notifyDataSetChanged();
                    if (!messagessArrayList.isEmpty()) {
                        mmessangesAdpter.scrollToPosition(messagessArrayList.size() - 1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(chatWin.this, "Failed to load messages", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý gửi tin nhắn
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = textmsg.getText().toString();
                if (message.isEmpty()) {
                    Toast.makeText(chatWin.this, "Enter The Message First", Toast.LENGTH_SHORT).show();
                    return;
                }
                textmsg.setText("");
                Date date = new Date();
                msgModelclass messagess = new msgModelclass(message, SenderUID, date.getTime());
                database.getReference().child("chats").child(senderRoom).child("messages").push()
                        .setValue(messagess).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                database.getReference().child("chats").child(reciverRoom).child("messages").push()
                                        .setValue(messagess).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (!messagessArrayList.isEmpty()) {
                                                    mmessangesAdpter.scrollToPosition(messagessArrayList.size() - 1);
                                                }
                                            }
                                        });
                            }
                        });
            }
        });
    }
}