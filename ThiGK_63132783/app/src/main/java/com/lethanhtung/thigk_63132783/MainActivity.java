package com.lethanhtung.thigk_63132783;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn_chucnang2,btn_chucnang3,btn_chucnang4,btn_aboutme;


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

        btn_chucnang2 = findViewById(R.id.btnChucnang2);
        btn_chucnang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ichucnang2 = new Intent(MainActivity.this, ActivityChucNang2.class);
                startActivity(ichucnang2);
            }
        });

        btn_chucnang3 = findViewById(R.id.btnChucnang3);
        btn_chucnang3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ichucnang3 = new Intent(MainActivity.this, ActivityChucNang3.class);
                startActivity(ichucnang3);
            }
        });
        btn_chucnang4 = findViewById(R.id.btnChucnang4);
        btn_chucnang4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ichucnang4 = new Intent(MainActivity.this, ActivityChucNang4.class);
                startActivity(ichucnang4);
            }
        });
        btn_aboutme = findViewById(R.id.btnAboutMe);
        btn_aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iaboutme = new Intent(MainActivity.this, ActivityAboutMe.class);
                startActivity(iaboutme);
            }
        });
    }
}