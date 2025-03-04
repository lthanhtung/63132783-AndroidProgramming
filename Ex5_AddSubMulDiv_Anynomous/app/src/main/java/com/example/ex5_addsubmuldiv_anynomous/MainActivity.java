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
    EditText edta;
    EditText edtb;
    EditText edtkq;
    Button btnCong,btnTru,btnNhan,btnChia;

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
        TimDieuKhien();
        //Cách 1 sử dụng bộ lắng nghe ẩn danh
        btnCong.setOnClickListener(new View.OnClickListener() {
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
        });

        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Đoạn mã Xử lý chức năng
                String Soa = edta.getText().toString();
                String Sob = edtb.getText().toString();

                double soA = Double.parseDouble(Soa);
                double soB = Double.parseDouble(Sob);

                double Nhan = soA * soB;
                String nhan = String.valueOf(Nhan);

                edtkq.setText(nhan);
            }
        });
// <-------------------------------------------------------------------------------------------------->
        //Cách 2: sử dụng biến để lắng nghe
        btnTru.setOnClickListener(BoxulyTru);
        btnChia.setOnClickListener(BoxulyChia);
    }
    View.OnClickListener BoxulyTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Đoạn mã Xử lý chức năng
            String Soa = edta.getText().toString();
            String Sob = edtb.getText().toString();

            double soA = Double.parseDouble(Soa);
            double soB = Double.parseDouble(Sob);

            double Tru = soA - soB;
            String tru = String.valueOf(Tru);

            edtkq.setText(tru);
        }
    };
    View.OnClickListener BoxulyChia = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Đoạn mã Xử lý chức năng
            String Soa = edta.getText().toString();
            String Sob = edtb.getText().toString();

            double soA = Double.parseDouble(Soa);
            double soB = Double.parseDouble(Sob);

            double Chia = soA / soB;
            String chia = String.valueOf(Chia);

            edtkq.setText(chia);

        }
    };
}