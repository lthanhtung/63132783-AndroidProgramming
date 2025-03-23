package com.example.luyen_tap_tong_hop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText taiKhoan;
    EditText matKhau;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        taiKhoan = findViewById(R.id.editTaiKhoan);
        matKhau = findViewById(R.id.editMatKhau);
        Login = findViewById(R.id.buttonLogIn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = taiKhoan.getText().toString();
                String matkhau = matKhau.getText().toString();
                if (taikhoan.equals("admin") && matkhau.equals("1234")){
                    Intent iHome = new Intent(MainActivity.this, HomeActivity.class);
                    iHome.putExtra("TaiKhoan",taikhoan);
                    startActivity(iHome);
                }
                else Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

            }
        });
    }
}