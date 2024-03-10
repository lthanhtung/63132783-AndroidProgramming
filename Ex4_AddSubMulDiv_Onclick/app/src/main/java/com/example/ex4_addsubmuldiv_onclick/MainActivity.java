package com.example.ex4_addsubmuldiv_onclick;

import android.annotation.SuppressLint;
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
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void XuLyCong(View view)
    {
        EditText edtSoA = findViewById(R.id.edtSoa);
        EditText edtSoB = findViewById(R.id.edtSob);
        EditText edtKetQua = findViewById(R.id.sdtKetQua);

        String Soa = edtSoA.getText().toString();
        String Sob = edtSoB.getText().toString();


        double SoA = Double.parseDouble(Soa);
        double SoB = Double.parseDouble(Sob);

        double Tong = SoA + SoB;
        String tong = String.valueOf(Tong);
        edtKetQua.setText(tong);
    }

    public void XuLyTru(View view)
    {
        EditText edtSoA = findViewById(R.id.edtSoa);
        EditText edtSoB = findViewById(R.id.edtSob);
        EditText edtKetQua = findViewById(R.id.sdtKetQua);

        String Soa = edtSoA.getText().toString();
        String Sob = edtSoB.getText().toString();


        double SoA = Double.parseDouble(Soa);
        double SoB = Double.parseDouble(Sob);

        double Tru = SoA - SoB;
        String tru = String.valueOf(Tru);
        edtKetQua.setText(tru);
    }
    public void XuLyNhan(View view)
    {
        EditText edtSoA = findViewById(R.id.edtSoa);
        EditText edtSoB = findViewById(R.id.edtSob);
        EditText edtKetQua = findViewById(R.id.sdtKetQua);

        String Soa = edtSoA.getText().toString();
        String Sob = edtSoB.getText().toString();


        double SoA = Double.parseDouble(Soa);
        double SoB = Double.parseDouble(Sob);

        double Nhan = SoA * SoB;
        String nhan = String.valueOf(Nhan);
        edtKetQua.setText(nhan);
    }

    public void XuLyChia(View view)
    {
        EditText edtSoA = findViewById(R.id.edtSoa);
        EditText edtSoB = findViewById(R.id.edtSob);
        EditText edtKetQua = findViewById(R.id.sdtKetQua);

        String Soa = edtSoA.getText().toString();
        String Sob = edtSoB.getText().toString();


        double SoA = Double.parseDouble(Soa);
        double SoB = Double.parseDouble(Sob);

        if (SoB == 0)   edtKetQua.setText("Lỗi!!Không thể chia cho 0");
        else {
            double Chia = SoA / SoB;
            String chia = String.valueOf(Chia);
            edtKetQua.setText(chia);
        }
    }

}