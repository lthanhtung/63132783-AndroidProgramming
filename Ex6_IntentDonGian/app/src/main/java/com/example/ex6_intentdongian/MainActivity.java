package com.example.ex6_intentdongian;

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

    Button nutMh2;
    Button nutMh3;

    //Hàm tìm điều khiển
    void TimDieuKhien(){
        nutMh2 = findViewById(R.id.btnMH2);
        nutMh3 = findViewById(R.id.btnMH3);
    }

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
        TimDieuKhien();

//        Button nutMh2 = (Button) findViewById(R.id.btnMH2);
//        Button nutMh3 = (Button) findViewById(R.id.btnMH3);

        //Gắn bộ lắng nghe
        nutMh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý chuyển màn hình
                //B1: TẠO INTENT với 2 tham số :
                //1: Màn hình hiện tạo.this ,
                //2: MÀn hin chuyển tới.class
                Intent intentMh2 = new Intent(MainActivity.this, ManHinh2.class);
                // Bước 2 : Gửi đến màn hình yêu cầu
                startActivity(intentMh2);
            }
        });

        //Gắn bộ lắng nghe
        nutMh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý chuyển màn hình
                //B1: TẠO INTENT với 2 tham số :
                //1: Màn hình hiện tạo.this ,
                //2: MÀn hin chuyển tới.class
                Intent intentMh3 = new Intent(MainActivity.this, ManHinh3.class);
                // Bước 2 : Gửi đến màn hình yêu cầu
                startActivity(intentMh3);
            }
        });

    }
}