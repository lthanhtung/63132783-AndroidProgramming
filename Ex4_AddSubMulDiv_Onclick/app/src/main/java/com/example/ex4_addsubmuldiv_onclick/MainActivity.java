package com.example.ex4_addsubmuldiv_onclick;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText EdtSoThu1,EdtSoThu2;
    TextView ViewKetQua;
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
        EdtSoThu1 = findViewById(R.id.edtSoThu1);
        EdtSoThu2 = findViewById(R.id.edtSoThu2);
        ViewKetQua = findViewById(R.id.ViewKetQua);
    }

    public void Cong(View v){
        String Sothu1 = EdtSoThu1.getText().toString();
        String Sothu2 = EdtSoThu2.getText().toString();
        double sothu1 = Double.parseDouble(Sothu1);
        double sothu2 = Double.parseDouble(Sothu2);

        double add = sothu1 + sothu2;

        ViewKetQua.setText(String.valueOf(add));
    }
    public void Tru(View v){
        String Sothu1 = EdtSoThu1.getText().toString();
        String Sothu2 = EdtSoThu2.getText().toString();
        double sothu1 = Double.parseDouble(Sothu1);
        double sothu2 = Double.parseDouble(Sothu2);

        double sub = sothu1 - sothu2;

        ViewKetQua.setText(String.valueOf(sub));
    }
    public void Nhan(View v){
        String Sothu1 = EdtSoThu1.getText().toString();
        String Sothu2 = EdtSoThu2.getText().toString();
        double sothu1 = Double.parseDouble(Sothu1);
        double sothu2 = Double.parseDouble(Sothu2);

        double mul = sothu1 * sothu2;

        ViewKetQua.setText(String.valueOf(mul));
    }
    public void Chia(View v){
        String Sothu1 = EdtSoThu1.getText().toString();
        String Sothu2 = EdtSoThu2.getText().toString();
        double sothu1 = Double.parseDouble(Sothu1);
        double sothu2 = Double.parseDouble(Sothu2);

        if (sothu2 != 0) {
            double div = sothu1 / sothu2;
            ViewKetQua.setText(String.valueOf(div));
        }
        else {
            ViewKetQua.setText("Lỗi Không thể chia cho 0");
        }
    }
}