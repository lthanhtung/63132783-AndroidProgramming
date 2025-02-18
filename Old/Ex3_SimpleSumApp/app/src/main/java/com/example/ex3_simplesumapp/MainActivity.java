package com.example.ex3_simplesumapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); //gắn layout tương ứng
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    //Hàm thực hiện việc lắng nghe và xử lý sự kiện
    public void XuLyCong(View view)
    {
        //Tìm và thàm chiếu đến điều khiển trên XML, mapping sang java File
        EditText editText_a = findViewById(R.id.editText_a); // tim điều khiển cần sử dụng có trên Giao diện
        EditText editText_b = findViewById(R.id.editText_b);
        EditText editText_Kq = findViewById(R.id.editText_kq);
        //Lấy dữ liệu của số a trên điều khiển về:
         String SoA =  editText_a.getText().toString();
        //Lấy dữ liệu của số b trên điều khiển về:
        String SoB =  editText_b.getText().toString();
        //Chuyển đổi kiểu dữ liệu sang số
        int So_a = Integer.parseInt(SoA);
        int So_b = Integer.parseInt(SoB);
        //Tính toán theo yêu cầu
        int Tong = So_a + So_b;

        //Hiểu thị kết quả


        String tong = String.valueOf(Tong);//chuyển sang dạng chuổi
        editText_Kq.setText(tong);


    }
}