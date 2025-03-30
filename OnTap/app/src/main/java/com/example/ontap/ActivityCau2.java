package com.example.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivityCau2 extends AppCompatActivity {

    Button btn_Cau1;
    Button btn_Cau3;

    ArrayList<String> datayoutuber;
    ListView listYoutuber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_Cau1 = findViewById(R.id.btn_Cau1);
        btn_Cau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icau1 = new Intent(ActivityCau2.this, ActivityCau1.class);
                startActivity(icau1);
            }
        });
        btn_Cau3 = findViewById(R.id.btn_Cau3);
        btn_Cau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icau3 = new Intent(ActivityCau2.this, ActivityCau3.class);
                startActivity(icau3);
            }
        });

        datayoutuber = new ArrayList<>();
        datayoutuber.add("Tin học Till");
        datayoutuber.add("Tin học Till");
        datayoutuber.add("Tin học Till");
        datayoutuber.add("Tin học Till");





        ArrayAdapter<String> adapterYoutuber  = new ArrayAdapter<>(
          this, android.R.layout.simple_dropdown_item_1line,datayoutuber
        );

        listYoutuber = findViewById(R.id.lvYoutuber);
        listYoutuber.setAdapter(adapterYoutuber);
        listYoutuber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String giatri = datayoutuber.get(position);
                Toast.makeText(ActivityCau2.this, "Bạn vừa ấn vào: " + giatri, Toast.LENGTH_SHORT).show();
            }
        });
    }
}