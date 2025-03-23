package com.example.luyen_tap_tong_hop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    Button ListView, AutoCompleteView;
    TextView Tendangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent ihome = getIntent();
        String tendangnhap = ihome.getStringExtra("TaiKhoan");
        Tendangnhap = findViewById(R.id.TenDangNhap);
        Tendangnhap.setText(tendangnhap);
        ListView = findViewById(R.id.listView);
        ListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentListview = new Intent(HomeActivity.this, ListView_ArrayAdapterActivity.class);
                startActivity(intentListview);
            }
        });
        AutoCompleteView = findViewById(R.id.autoCompleteView);
        AutoCompleteView.setOnClickListener(autoCompleteView);
    }
    View.OnClickListener autoCompleteView = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Intent intentAutoCompleteView = new Intent(HomeActivity.this, AutoCompleteViewActivity.class);
           startActivity(intentAutoCompleteView);
        }
    };
}