package vn.edu.lethanhtung.messengappproject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
    ImageView imglogout;
    ImageView cambut, setbut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Kết nối csdl
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        cambut = findViewById(R.id.cambut);
        setbut = findViewById(R.id.settingBut);

        DatabaseReference reference  = database.getReference().child("user");
        //Lấy danh sách user
        usersArrayList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Xóa danh sách cũ trước khi thêm mới
                usersArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Users users = dataSnapshot.getValue(Users.class);
                    usersArrayList.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imglogout = findViewById(R.id.logoutimg);

        imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tạo hộp thoại thông báo logout
                Dialog dialog = new Dialog(MainActivity.this,R.style.dialoge);
                dialog.setContentView(R.layout.dialog_layout);
                Button no,yes;
                yes = dialog.findViewById(R.id.yesbnt);
                no = dialog.findViewById(R.id.nobnt);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                        finish();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();// đóng hộp thoại
                    }
                });
                dialog.show();
            }
        });

        mainUserRecyclerView = findViewById(R.id.mainUserRecyclerView);
        mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(MainActivity.this,usersArrayList);
        mainUserRecyclerView.setAdapter(adapter);

        setbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, setting.class);
                startActivity(intent);
            }
        });

        cambut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mỏ camera
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10);
            }
        });


        if (auth.getCurrentUser() == null){
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);

        }


    }
}