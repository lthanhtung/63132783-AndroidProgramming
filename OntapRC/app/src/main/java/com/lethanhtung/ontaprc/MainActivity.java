package com.lethanhtung.ontaprc;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Khai báo 3 thành phần
    ArrayList<youtuber> dsyoutuber = new ArrayList<>();
    adapter adapter;
    RecyclerView recyclerYoutuber;


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

        //3
        dsyoutuber = getdata();
        //4 tìm điều khiển
        recyclerYoutuber = findViewById(R.id.rcyoutuber);
        //5 Tạo layer
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerYoutuber.setLayoutManager(layoutManager);
        //6 gắn adapter với nguồn dữ liệu
        adapter = new adapter(this,dsyoutuber);
        //setadapter cho recylerview
        recyclerYoutuber.setAdapter(adapter);


    }
    //Tạo hàm để lấy dữ liệu
    ArrayList<youtuber> getdata(){
        ArrayList<youtuber> data = new ArrayList<youtuber>();
        data.add(new youtuber("Trực tiếp game","tructiepgame"));
        data.add(new youtuber("Vũ nguyễn Coder","vunguyen_coder"));
        data.add(new youtuber("tiil tutors","tiil_tutors"));
        data.add(new youtuber("F8","f8"));
        return data;
    }


}