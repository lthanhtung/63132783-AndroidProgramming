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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityCau3 extends AppCompatActivity {

    Button btn_Cau2,btn_Cau4;
    //Khai báo các thành phần
    YoutuberAdapter youtuberAdapter;
    ArrayList<youtuber>  recyclerViewdata = new ArrayList<>() ;
    RecyclerView YoutuberrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_Cau2 = findViewById(R.id.btn_Cau2);
        btn_Cau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCau2 = new Intent(ActivityCau3.this, ActivityCau2.class);
                startActivity(iCau2);
            }
        });
        btn_Cau4 = findViewById(R.id.btn_Cau4);
        btn_Cau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCau4 = new Intent(ActivityCau3.this, ActivityCau4.class);
                startActivity(iCau4);
            }
        });

        //gắn dữ liệu cho recyclerViewData
        recyclerViewdata = getDataforRecylerview();
        //Tìm điều khiển
        YoutuberrecyclerView = findViewById(R.id.recyclerViewYoutuber);
        // Tạo layer
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //Set layout cho RecylerView
        YoutuberrecyclerView.setLayoutManager(layoutManager);
        //Gắn adapter với nguồn dữ liệu
        youtuberAdapter = new YoutuberAdapter(this,recyclerViewdata);
        //set apdapter cho recyler view
        YoutuberrecyclerView.setAdapter(youtuberAdapter);

    }
    //Tạo 1 hàm để lấy dữ liệu
    ArrayList<youtuber> getDataforRecylerview(){
        ArrayList<youtuber> dsYoutuber = new ArrayList<youtuber>();
        dsYoutuber.add(new youtuber("Trực tiếp game","gndtt"));
        dsYoutuber.add(new youtuber("F8","avatarbotron"));
        dsYoutuber.add(new youtuber("Vũ nguyễn coder","gndtt"));
        dsYoutuber.add(new youtuber("Vũ nguyễn coder","gndtt"));
        dsYoutuber.add(new youtuber("Vũ nguyễn coder","gndtt"));

        return dsYoutuber;
    }
}