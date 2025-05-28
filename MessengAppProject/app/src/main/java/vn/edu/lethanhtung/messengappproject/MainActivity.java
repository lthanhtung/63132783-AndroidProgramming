package vn.edu.lethanhtung.messengappproject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

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
    RecyclerView mainUserRecyclerView;
    UserAdapter adapter;
    FirebaseDatabase database;
    ArrayList<Users> usersArrayList;
    ArrayList<Users> filteredList;
    ImageView imglogout;
    ImageView cambut, setbut;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        cambut = findViewById(R.id.cambut);
        setbut = findViewById(R.id.settingBut);
        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Tìm theo mã khách hàng"); // Gợi ý tìm kiếm

        usersArrayList = new ArrayList<>();
        filteredList = new ArrayList<>();

        DatabaseReference reference = database.getReference().child("user");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    usersArrayList.add(users);
                }
                adapter.setFilteredList(usersArrayList); // Hiển thị danh sách đầy đủ
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        imglogout = findViewById(R.id.logoutimg);
        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        mainUserRecyclerView = findViewById(R.id.mainUserRecyclerView);
        mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(MainActivity.this, usersArrayList);
        mainUserRecyclerView.setAdapter(adapter);

        setbut.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, setting.class);
            startActivity(intent);
        });

        cambut.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 10);
        });

        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        }

        // Lọc danh sách theo customUserID
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Không xử lý khi submit
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListByCustomId(newText);
                return true;
            }
        });
    }

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
