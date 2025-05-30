package vn.edu.lethanhtung.messengappproject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;

    RecyclerView mainUserRecyclerView;
    UserAdapter adapter;
    ArrayList<Users> usersArrayList;
    ArrayList<Users> filteredList;
    Set<String> chattedUserIds;

    ImageView imglogout, cambut, setbut;
    SearchView searchView;
    TextView currentCustomIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Khởi tạo Firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        // Ánh xạ các thành phần giao diện
        cambut = findViewById(R.id.cambut);
        setbut = findViewById(R.id.settingBut);
        searchView = findViewById(R.id.searchView);
        currentCustomIdTextView = findViewById(R.id.currentCustomIdTextView);
        imglogout = findViewById(R.id.logoutimg);
        mainUserRecyclerView = findViewById(R.id.mainUserRecyclerView);

        usersArrayList = new ArrayList<>();
        filteredList = new ArrayList<>();
        chattedUserIds = new HashSet<>();

        adapter = new UserAdapter(MainActivity.this, usersArrayList);
        mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainUserRecyclerView.setAdapter(adapter);

        // Lấy danh sách người dùng đã nhắn tin
        String currentUserId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : null;
        if (currentUserId != null) {
            loadChattedUsers(currentUserId);
        }

        // Hiển thị customUserID người dùng hiện tại
        if (auth.getCurrentUser() != null) {
            String currentUid = auth.getCurrentUser().getUid();
            DatabaseReference currentUserRef = database.getReference().child("user").child(currentUid);

            currentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users currentUser = snapshot.getValue(Users.class);
                    if (currentUser != null && currentUser.getCustomUserID() != null) {
                        currentCustomIdTextView.setText("Mã khách hàng: " + currentUser.getCustomUserID());
                    } else {
                        currentCustomIdTextView.setText("Mã khách hàng: (Không có)");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    currentCustomIdTextView.setText("Mã khách hàng: (Lỗi)");
                }
            });
        }

        // Xử lý sự kiện đăng xuất
        imglogout.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this, R.style.dialoge);
            dialog.setContentView(R.layout.dialog_layout);
            Button yes = dialog.findViewById(R.id.yesbnt);
            Button no = dialog.findViewById(R.id.nobnt);

            yes.setOnClickListener(view -> {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, login.class));
                finish();
            });

            no.setOnClickListener(view -> dialog.dismiss());
            dialog.show();
        });

        // Mở setting
        setbut.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, setting.class);
            startActivity(intent);
        });

        // Mở camera
        cambut.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 10);
        });

        // Nếu chưa đăng nhập, quay về màn hình đăng nhập
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
        }

        // Tìm kiếm tất cả người dùng theo customUserID
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListByCustomId(newText);
                return true;
            }
        });
    }

    // Hàm lấy danh sách userId của những người đã nhắn tin
    private void loadChattedUsers(String currentUserId) {
        DatabaseReference chatsRef = database.getReference().child("chats");
        chatsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chattedUserIds.clear();
                for (DataSnapshot chatSnapshot : snapshot.getChildren()) {
                    String chatRoomId = chatSnapshot.getKey();
                    if (chatRoomId != null && chatRoomId.contains(currentUserId)) {
                        String otherUserId = chatRoomId.replace(currentUserId, "");
                        if (!otherUserId.isEmpty()) {
                            chattedUserIds.add(otherUserId);
                        }
                    }
                }
                loadUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Có thể thêm thông báo lỗi nếu cần
            }
        });
    }

    // Hàm tải danh sách người dùng, chỉ hiển thị những người đã nhắn tin
    private void loadUsers() {
        DatabaseReference reference = database.getReference().child("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String userId = dataSnapshot.getKey();
                    if (chattedUserIds.contains(userId)) {
                        Users user = dataSnapshot.getValue(Users.class);
                        if (user != null) {
                            user.setUserId(userId);
                            usersArrayList.add(user);
                        }
                    }
                }
                adapter.setFilteredList(usersArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Có thể thêm thông báo lỗi nếu cần
            }
        });
    }

    // Hàm lọc danh sách tất cả người dùng theo customUserID
    private void filterListByCustomId(String inputText) {
        if (inputText.isEmpty()) {
            // Khi input rỗng, hiển thị lại danh sách người dùng đã nhắn tin
            adapter.setFilteredList(usersArrayList);
        } else {
            // Khi có input, tìm kiếm trên tất cả người dùng
            DatabaseReference reference = database.getReference().child("user");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    filteredList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Users user = dataSnapshot.getValue(Users.class);
                        if (user != null) {
                            user.setUserId(dataSnapshot.getKey());
                            if (user.getCustomUserID() != null &&
                                    user.getCustomUserID().toLowerCase().contains(inputText.toLowerCase())) {
                                filteredList.add(user);
                            }
                        }
                    }
                    adapter.setFilteredList(filteredList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Có thể thêm thông báo lỗi nếu cần
                }
            });
        }
    }
}