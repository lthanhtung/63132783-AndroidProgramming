package com.example.listview;

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

public class MainActivity extends AppCompatActivity {

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
        //Tìm điều khiển ListView
        ListView listViewData = findViewById(R.id.ListView);
        //B1 Chuẩn bị dữ liệu
        //Khai báo biến ArrayList
        ArrayList<String> listDataYoutube = new ArrayList<>();
        listDataYoutube.add("TiiL Tutors");
        listDataYoutube.add("Vũ Nguyễn Coder");
        listDataYoutube.add("Trực Tiếp Game");
        listDataYoutube.add("HowKTeam");
        listDataYoutube.add("Hỏi Dân IT");

        //Bước 2: Khai báo ArrayAdapTer
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,listDataYoutube
        );

        //Bước 3: Gắn adapter vào ListView
        listViewData.setAdapter(arrayAdapter);
        //Bước 3.1: Dùng phương thức OnItemClickListener
        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Với các tham số:
                //- parent: AdapterView chứa ListView
                // - view: View của item được nhấn
                // - position: Vị trí của item trong danh sách (bắt đầu từ 0)
                // - id: ID của item (thường trùng position nếu không chỉ định khác)

                //Lấy giá trị phần tử được nhấn
                String giaTri = listDataYoutube.get(position);

                //Hiển thị thông báo về giá trị vừa được nhấn
                Toast.makeText(MainActivity.this, "Giá trị vừa nhấn là:" + giaTri, Toast.LENGTH_SHORT).show();

            }
        });

    }
}