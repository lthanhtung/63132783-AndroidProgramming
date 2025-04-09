package com.lethanhtung.thigk_63132783;

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

public class ActivityChucNang3 extends AppCompatActivity {

    ArrayList<String> databaihat;
    ListView listbaihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaihat = new ArrayList<>();
        databaihat.add("Tiến về sài gòn");
        databaihat.add("Giải phóng miền nam");
        databaihat.add("Bài ca thống nhất");
        databaihat.add("Mùa xuân trên thành phố");

        databaihat.add("Đom đóm");
        databaihat.add("Em của ngày hôm qua");
        databaihat.add("Khuông mặt đáng thương");
        databaihat.add("Muộn rồi mà sao còn");
        ArrayAdapter<String> adapterBaiHat  = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line,databaihat
        );
        listbaihat = findViewById(R.id.listviewBaiHai);
        listbaihat.setAdapter(adapterBaiHat);
        listbaihat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String giatri = databaihat.get(position);
                Toast.makeText(ActivityChucNang3.this, "Bạn vừa ấn vào: " + giatri, Toast.LENGTH_SHORT).show();
            }
        });
    }
}