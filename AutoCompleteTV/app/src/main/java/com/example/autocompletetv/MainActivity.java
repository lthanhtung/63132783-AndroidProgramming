package com.example.autocompletetv;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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
//B1: Chuẩn bị dữ liệu
//Khai báo biến ArrayList
    ArrayList<String> ListYoutubeLove = new ArrayList<>();
// Hard code cho ArrayList
    ListYoutubeLove.add("TiiL Tutors");
    ListYoutubeLove.add("Vũ Nguyên Coder");
    ListYoutubeLove.add("Trực tiếp game");
    ListYoutubeLove.add("HowKTeam");
    ListYoutubeLove.add("Hỏi Dân IT");
//Tìm điều khiển AutoCompleteTextView
    AutoCompleteTextView autoCompleteTV = findViewById(R.id.AutoCompleteTV);
//    Bước 2: Tạo ArrayAdapter bao gồm 3 tham số:
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,     // Ngữ Cảnh
                android.R.layout.simple_dropdown_item_1line, // ItemLayout
                ListYoutubeLove    // Data
        );
//  Bước 3: Gắn Adapter vào autoComplete TextView
        autoCompleteTV.setAdapter(arrayAdapter);
// Có thể Thiết lập số ký tự tối thiểu để hiển thị gợi ý
    autoCompleteTV.setThreshold(1);
    }
}