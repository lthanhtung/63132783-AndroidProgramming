package com.example.ontap;

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
    Button btn_cau1,btn_cau2,btn_cau3,btn_cau4;

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
        btn_cau1 = findViewById(R.id.btn_Cau1);
        btn_cau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icau1 = new Intent(MainActivity.this, ActivityCau1.class);
                startActivity(icau1);
            }
        });

        btn_cau2 = findViewById(R.id.btn_Cau2);
        btn_cau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icau2 = new Intent(MainActivity.this, ActivityCau2.class);
                startActivity(icau2);
            }
        });
        btn_cau3 = findViewById(R.id.btn_Cau3);
        btn_cau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCau3 = new Intent(MainActivity.this, ActivityCau3.class);
                startActivity(iCau3);
            }
        });

        btn_cau4 = findViewById(R.id.btn_Cau4);
        btn_cau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icau4 = new Intent(MainActivity.this, ActivityCau4.class);
                startActivity(icau4);
            }
        });
    }
}