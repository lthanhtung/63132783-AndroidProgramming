package com.example.ex8_recyclerview;

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

    Youtuber_Adapter youtuberAdapter;
    ArrayList<Youtuber> recyclerViewdata = new ArrayList<>();
    RecyclerView YoutuberrecyclerView;


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
        // 3 gán dữ liệu vừa tạo cho recyclerViewdata
        recyclerViewdata = getDataforRecyclerView();
        // 4 Tìm điều khiển
        YoutuberrecyclerView = findViewById(R.id.Youtuber_RecyclerView);
        // 5 Tạo layout hiển thị
        RecyclerView.LayoutManager layoutlinear = new LinearLayoutManager(this);
        //set layout cho RecyclerView
        YoutuberrecyclerView.setLayoutManager(layoutlinear);
        // 6 Tạo adapter gắn với nguồn dữ liệu
        youtuberAdapter = new Youtuber_Adapter(this,recyclerViewdata);
        //7 gắn adapter vào RecyclerView
        YoutuberrecyclerView.setAdapter(youtuberAdapter);


    }

    //Tạo 1 hàm để chuẩn bị dữ liệu
    ArrayList<Youtuber> getDataforRecyclerView(){
        ArrayList<Youtuber> dsYoutuber = new ArrayList<Youtuber>();
        Youtuber TiiL_Tutors = new Youtuber("tiil_tutors", "TiiL Tutors");
        dsYoutuber.add(TiiL_Tutors);

        //làm nhanh
        dsYoutuber.add(
          new Youtuber("howkteam","HowKteam.com")
        );

        dsYoutuber.add(
                new Youtuber("f8","F8 - JavaScrip - React")
        );

        dsYoutuber.add(
                new Youtuber("truc_tiep_game","Trực tiếp game")
        );

        dsYoutuber.add(
                new Youtuber("gndtt","GNDTT")
        );
        return dsYoutuber;
    }

}