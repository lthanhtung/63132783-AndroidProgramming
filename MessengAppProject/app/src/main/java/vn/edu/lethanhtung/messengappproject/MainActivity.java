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

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;

    RecyclerView mainUserRecyclerView;
    UserAdapter adapter;
    ArrayList<Users> usersArrayList;
    ArrayList<Users> filteredList;

    ImageView imglogout, cambut, setbut;
    SearchView searchView;
    TextView currentCustomIdTextView; // TextView hiển thị customUserID

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
        currentCustomIdTextView = findViewById(R.id.currentCustomIdTextView); // ánh xạ TextView
        imglogout = findViewById(R.id.logoutimg);
        mainUserRecyclerView = findViewById(R.id.mainUserRecyclerView);

        usersArrayList = new ArrayList<>();
        filteredList = new ArrayList<>();

        adapter = new UserAdapter(MainActivity.this, usersArrayList);
        mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainUserRecyclerView.setAdapter(adapter);

        // Lấy dữ liệu danh sách người dùng từ Firebase
        DatabaseReference reference = database.getReference().child("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    usersArrayList.add(users);
                }
                adapter.setFilteredList(usersArrayList); // cập nhật danh sách hiển thị
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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
        }

        // Tìm kiếm theo customUserID
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

    // Hàm lọc danh sách theo customUserID
    private void filterListByCustomId(String inputText) {
        filteredList.clear();
        for (Users user : usersArrayList) {
            if (user.getCustomUserID() != null &&
                    user.getCustomUserID().toLowerCase().contains(inputText.toLowerCase())) {
                filteredList.add(user);
            }
        }
        adapter.setFilteredList(filteredList);
    }
}
