package com.example.ex5_addsubmuldiv_anynomous;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //1. Khai báo các điều khiển
    EditText edta;
    EditText edtb;
    EditText edtkq;
    Button btnCong,btnTru,btnNhan,btnChia;

    //2.Tạo hàm tìm điều khiển
    void TimDieuKhien()
    {
        edta = (EditText)findViewById(R.id.edtSoa);
        edtb = (EditText)findViewById(R.id.edtSob);
        edtkq = (EditText)findViewById(R.id.sdtKetQua);
        btnCong = (Button)findViewById(R.id.btnCong);
        btnTru =  (Button)findViewById(R.id.btnTru);
        btnNhan = (Button)findViewById(R.id.btnNhan);
        btnChia = (Button)findViewById(R.id.btnChia);
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
        //3. gắn hàm tìm điều khiển vào hàm oncreate
        TimDieuKhien();
        //4.Gắn bộ lắng nghe cho sự kiện và đoạn mã xử lý cho
        //4.1 Tạo mới biến 1 OnclickListener phục vụ việc lắng nghe 1 chức năng nhất định
        View.OnClickListener BoXuLyCong = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Đoạn mã Xử lý chức năng
                String Soa = edta.getText().toString();
                String Sob = edtb.getText().toString();

                double soA = Double.parseDouble(Soa);
                double soB = Double.parseDouble(Sob);

                double Tong = soA + soB;
                String tong = String.valueOf(Tong);

                edtkq.setText(tong);


            }
        };
        //4.2 Gắn biến OnclickListener vào SetOnClickListener để thực hiện chức năng tương ứng
        btnCong.setOnClickListener(BoXuLyCong);

        View.OnClickListener BoXuLyTru = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Soa = edta.getText().toString();
                String Sob = edtb.getText().toString();

                double soA = Double.parseDouble(Soa);
                double soB = Double.parseDouble(Sob);

                double Tru = soA - soB;
                String tru = String.valueOf(Tru);

                edtkq.setText(tru);
            }
        };
        btnTru.setOnClickListener(BoXuLyTru);

        View.OnClickListener BoXuLyNhan = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Soa = edta.getText().toString();
                String Sob = edtb.getText().toString();

                double soA = Double.parseDouble(Soa);
                double soB = Double.parseDouble(Sob);

                double Nhan = soA * soB;
                String nhan = String.valueOf(Nhan);

                edtkq.setText(nhan);
                }
        };
        btnNhan.setOnClickListener(BoXuLyNhan);

        View.OnClickListener BoXuLyChia = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Soa = edta.getText().toString();
                String Sob = edtb.getText().toString();


                double SoA = Double.parseDouble(Soa);
                double SoB = Double.parseDouble(Sob);

                if (SoB == 0)   edtkq.setText("Lỗi!!Không thể chia cho 0");
                else {
                    double Chia = SoA / SoB;
                    String chia = String.valueOf(Chia);
                    edtkq.setText(chia);
                }
            }
        };
        btnChia.setOnClickListener(BoXuLyChia);
    }

}