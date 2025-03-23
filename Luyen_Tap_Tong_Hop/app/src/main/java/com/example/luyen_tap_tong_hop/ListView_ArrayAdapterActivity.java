package com.example.luyen_tap_tong_hop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListView_ArrayAdapterActivity extends AppCompatActivity {
    ListView listBaiHoc;
    ArrayList<String> listdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_array_adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listdata.add("Bài 1: CÁC THÀNH PHẦN GIAO DIỆN CƠ BẢN");
        listdata.add("Bài 2: BẮT VÀ SỬ LÝ SỰ KIỆN");
        listdata.add("Bài 3: LÀM VIỆC VỚI: INTENT, FRAGMENT, RECYCLERVIEW");
        listdata.add("Bài 4: DỊCH VỤ LƯU TRỮ");
        listdata.add("Tuần học số: Lập trình Android kết nối Firebase realtime DB");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
          this, android.R.layout.simple_dropdown_item_1line,listdata
        );

        listBaiHoc = findViewById(R.id.listview);
        listBaiHoc.setAdapter(adapter);
        listBaiHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String baihoc = listdata.get(position);
                Toast.makeText(ListView_ArrayAdapterActivity.this, baihoc, Toast.LENGTH_SHORT).show();
            }
        });

    }
}