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
    ArrayList<String> dsTenTinh; // Khai báo
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
    /*Hiển dữ liệu lên ListView
      B1: Chuẩn bị Dữ liệu
      ???Từ đâu có --->dữ liệu từ cơ sở dữ liệu.
      Cần biến phù hợp để chứa dữ liệu
    */

        dsTenTinh = new ArrayList<String>(); // Tạo thể hiện cụ thể, xin mới
        //Thêm dữ liệu vào danh sách (Đúng ra ta phải đọc từ một nguồn nào đó)
        // Nhưng ta hard-code(cho sẵn để demo)
        dsTenTinh.add("Hà Nội");
        dsTenTinh.add("Nha Trang");
        dsTenTinh.add("Thành phố Hồ Chí Minh");
        dsTenTinh.add("Phú Yên");
        dsTenTinh.add("Thái Bình");
        dsTenTinh.add("Huế");

        //B2: Tạo adapter
        ArrayAdapter<String>  adapterTinh;
        adapterTinh = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dsTenTinh);

        //Gắn adapter vào ListView
        //B3.1: Tìm điều kiển
        ListView lvTenTinh = findViewById(R.id.LvDanhSachTinh);
        //B3.2 Gắn adapter
        lvTenTinh.setAdapter(adapterTinh);
        //B3.3 Lắng nghe và xử lý sự kiện người dùng tương tác
        //gắn bộ lắng nghe vào
        lvTenTinh.setOnItemClickListener(BoLangNgheXL);

    }
    //Tạo Bộ lắng nghe và xử lý sự kiện OnItemClick, đặt vào một biến
    //vd BoLangNgheXL
    AdapterView.OnItemClickListener BoLangNgheXL = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Code xử lý
            //position là vị trí phần tử vừa click
            // ví dụ 1 xử lý: Hiện lên 1 thông báo nhanh về vị trí của phần tử vừa chọn
            //Toast.makeText(MainActivity.this,"Bạn vừa chọn" + String.valueOf(position),Toast.LENGTH_LONG).show();

            //Ví dụ 2: Hiển thị giá trị
            String TenTinhDuocChon = dsTenTinh.get(position);
            Toast.makeText(MainActivity.this,TenTinhDuocChon,Toast.LENGTH_LONG).show();


        }
    };
}