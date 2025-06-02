package vn.edu.lethanhtung.messengappproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatWin extends AppCompatActivity {

    String reciverimg, reciverUid, reciverName, SenderUID;
    CircleImageView profile;
    TextView reciverNName;
    CardView sendbtn;
    ImageButton imagePickerBtn;
    EditText textmsg;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    String senderRoom, reciverRoom;
    RecyclerView mmessangesAdpter;
    ArrayList<msgModelclass> messagessArrayList;
    messagesAdpter messagesAdpter;

    private boolean justDismissedReactionPopup = false;

    PopupWindow reactionPopupWindow = null;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_win);

        ImageButton backToHome = findViewById(R.id.backToHome);
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chatWin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Khởi tạo Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        // Lấy dữ liệu từ Intent
        reciverName = getIntent().getStringExtra("nameeee");
        reciverimg = getIntent().getStringExtra("reciverImg");
        reciverUid = getIntent().getStringExtra("uid");

        // Khởi tạo giao diện
        sendbtn = findViewById(R.id.sendbtnn);
        imagePickerBtn = findViewById(R.id.image_picker_btn);
        textmsg = findViewById(R.id.textmsg);
        profile = findViewById(R.id.profileimgg);
        reciverNName = findViewById(R.id.recivername);

        // Tải ảnh hồ sơ và tên của người nhận
        Picasso.get()
                .load(reciverimg)
                .placeholder(R.drawable.man)
                .error(R.drawable.man)
                .resize(100, 100)
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
        messagesAdpter = new messagesAdpter(chatWin.this, messagessArrayList, reciverimg);
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
                    message.setMessageId(dataSnapshot.getKey());
                    // Đọc trường reaction
                    String reaction = dataSnapshot.child("reaction").getValue(String.class);
                    message.setReaction(reaction != null ? reaction : "");
                    // Đọc trường isEdited từ database
                    Boolean isEdited = dataSnapshot.child("isEdited").getValue(Boolean.class);
                    message.setEdited(isEdited != null ? isEdited : false);
                    newMessages.add(message);
                }
                messagessArrayList.clear();
                messagessArrayList.addAll(newMessages);
                messagesAdpter.notifyDataSetChanged();
                if (!messagessArrayList.isEmpty()) {
                    mmessangesAdpter.scrollToPosition(messagessArrayList.size() - 1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(chatWin.this, "Failed to load messages", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý gửi tin nhắn văn bản
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
                // Tạo key duy nhất
                String key = database.getReference().child("chats").child(senderRoom).child("messages").push().getKey();
                msgModelclass messagess = new msgModelclass(message, SenderUID, date.getTime(), null);
                messagess.setMessageId(key);
                messagess.setEdited(false);

                // Gửi vào cả hai phòng với cùng key
                database.getReference().child("chats").child(senderRoom).child("messages").child(key)
                        .setValue(messagess).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                database.getReference().child("chats").child(reciverRoom).child("messages").child(key)
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

        // Xử lý chọn ảnh
        imagePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            uploadImageToFirebase(imageUri);
        }
    }

    public void showEditMessageDialog(msgModelclass message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_edit_message, null);
        EditText input = view.findViewById(R.id.edit_message);
        input.setText(message.getMessage());
        builder.setView(view);
        AlertDialog dialog = builder.create();

        view.findViewById(R.id.btn_save).setOnClickListener(v -> {
            String newText = input.getText().toString().trim();
            if (!newText.isEmpty()) {
                updateMessageInFirebase(message, newText);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }


    private void updateMessageInFirebase(msgModelclass message, String newText) {
        DatabaseReference senderRef = database.getReference()
                .child("chats").child(senderRoom).child("messages").child(message.getMessageId());
        DatabaseReference reciverRef = database.getReference()
                .child("chats").child(reciverRoom).child("messages").child(message.getMessageId());

        // Tạo map chứa các trường cần cập nhật
        Map<String, Object> updates = new HashMap<>();
        updates.put("message", newText);
        updates.put("isEdited", true); // Thêm trường isEdited

        // Cập nhật cả hai phòng
        senderRef.updateChildren(updates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reciverRef.updateChildren(updates).addOnCompleteListener(task2 -> {
                    if (task2.isSuccessful()) {
                        // Cập nhật local data
                        message.setMessage(newText);
                        message.setEdited(true);
                        // Thông báo adapter cập nhật
                        int position = messagessArrayList.indexOf(message);
                        if (position != -1) {
                            messagesAdpter.notifyItemChanged(position);
                        }
                    }
                });
            }
        });
    }
    public void showMessageOptionsDialog(msgModelclass message, View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_message_options, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit) {
                    showEditMessageDialog(message);
                    return true;
                } else if (item.getItemId() == R.id.menu_delete) {
                    showDeleteConfirmationDialog(message);
                    return true;
                }
                return false;
            }
        });
        popup.show();
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference storageRef = storage.getReference().child("chat_images/" + System.currentTimeMillis() + ".jpg");
        UploadTask uploadTask = storageRef.putFile(imageUri);

        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    storageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                String imageUrl = task.getResult().toString();
                                Date date = new Date();
                                msgModelclass message = new msgModelclass(null, SenderUID, date.getTime(), imageUrl);
                                database.getReference().child("chats").child(senderRoom).child("messages").push()
                                        .setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                database.getReference().child("chats").child(reciverRoom).child("messages").push()
                                                        .setValue(message);
                                            }
                                        });
                            } else {
                                Toast.makeText(chatWin.this, "Failed to get image URL", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(chatWin.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void deleteMessage(msgModelclass message) {
        DatabaseReference senderRef = database.getReference()
                .child("chats").child(senderRoom).child("messages").child(message.getMessageId());
        DatabaseReference reciverRef = database.getReference()
                .child("chats").child(reciverRoom).child("messages").child(message.getMessageId());

        // Xóa tin nhắn khỏi cả hai phòng
        senderRef.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reciverRef.removeValue().addOnCompleteListener(task2 -> {
                    if (task2.isSuccessful()) {
                        // Cập nhật local data
                        int position = messagessArrayList.indexOf(message);
                        if (position != -1) {
                            messagessArrayList.remove(position);
                            messagesAdpter.notifyItemRemoved(position);
                        }
                        Toast.makeText(chatWin.this, "Đã xóa tin nhắn", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void showDeleteConfirmationDialog(msgModelclass message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Xóa tin nhắn")
                .setMessage("Bạn có chắc chắn muốn xóa tin nhắn này?")
                .setPositiveButton("Xóa", (d, which) -> deleteMessage(message))
                .setNegativeButton("Hủy", null)
                .show();

        // Đổi màu nút sau khi dialog đã show
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
    }

    //icon
    public void showReactionPopup(View anchor, msgModelclass message, int position) {
        if (justDismissedReactionPopup) {
            justDismissedReactionPopup = false; // Reset cờ
            return; // Không bật lại popup ngay sau khi vừa tắt
        }
        if (reactionPopupWindow != null && reactionPopupWindow.isShowing()) {
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
            justDismissedReactionPopup = true;
            return;
        }

        View popupView = LayoutInflater.from(this).inflate(R.layout.layout_reaction_popup, null);
        reactionPopupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                false);

        // Gán sự kiện cho từng emoji
        popupView.findViewById(R.id.reaction_like).setOnClickListener(v -> {
            updateReaction(message, "like", position);
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
        });
        popupView.findViewById(R.id.reaction_love).setOnClickListener(v -> {
            updateReaction(message, "love", position);
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
        });
        popupView.findViewById(R.id.reaction_haha).setOnClickListener(v -> {
            updateReaction(message, "haha", position);
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
        });
        popupView.findViewById(R.id.reaction_wow).setOnClickListener(v -> {
            updateReaction(message, "wow", position);
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
        });
        popupView.findViewById(R.id.reaction_sad).setOnClickListener(v -> {
            updateReaction(message, "sad", position);
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
        });
        popupView.findViewById(R.id.reaction_angry).setOnClickListener(v -> {
            updateReaction(message, "angry", position);
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
        });

        // Đóng popup khi bấm ra ngoài
        reactionPopupWindow.setOutsideTouchable(true);
        reactionPopupWindow.setFocusable(false);
        reactionPopupWindow.setOnDismissListener(() -> reactionPopupWindow = null);

        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        int anchorX = location[0];
        int anchorY = location[1];

// Đo kích thước popup
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = popupView.getMeasuredWidth();
        int popupHeight = popupView.getMeasuredHeight();

// Tính toán vị trí để popup nằm phía trên anchor, căn giữa
        int xOff = anchorX + anchor.getWidth()/2 - popupWidth/2;
        int yOff = anchorY - popupHeight - 16; // 16 là khoảng cách nhỏ phía trên

// Hiển thị popup phía trên anchor
        reactionPopupWindow.showAtLocation(anchor, android.view.Gravity.NO_GRAVITY, xOff, yOff);
    }
    private void updateReaction(msgModelclass message, String reaction, int position) {
        DatabaseReference senderRef = database.getReference()
                .child("chats").child(senderRoom).child("messages").child(message.getMessageId());
        DatabaseReference reciverRef = database.getReference()
                .child("chats").child(reciverRoom).child("messages").child(message.getMessageId());

        Map<String, Object> updates = new HashMap<>();
        updates.put("reaction", reaction);

        senderRef.updateChildren(updates);
        reciverRef.updateChildren(updates);

        // Cập nhật local và giao diện
        message.setReaction(reaction);
        messagesAdpter.notifyItemChanged(position);
    }
    public void dismissReactionPopupIfShowing() {
        if (reactionPopupWindow != null && reactionPopupWindow.isShowing()) {
            reactionPopupWindow.dismiss();
            reactionPopupWindow = null;
            justDismissedReactionPopup = true; // Đánh dấu vừa tắt popup
        }
    }
    public boolean isReactionPopupShowing() {
        return reactionPopupWindow != null && reactionPopupWindow.isShowing();
    }
}