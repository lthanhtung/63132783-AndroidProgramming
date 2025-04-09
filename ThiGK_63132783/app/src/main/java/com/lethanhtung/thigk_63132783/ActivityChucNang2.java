package com.lethanhtung.thigk_63132783;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityChucNang2 extends AppCompatActivity {

    EditText editNgay,editThang,editNam;
    TextView kq;
    Button btnKq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editNgay = findViewById(R.id.editNgay);
        editThang = findViewById(R.id.editThang);
        editNam= findViewById(R.id.editNam);
        kq = findViewById(R.id.textViewkq);
        btnKq = findViewById(R.id.buttonKetQua);
        btnKq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngay = editNgay.getText().toString();
                String thang = editThang.getText().toString();
                String nam = editNam.getText().toString();
                if (ngay.equals("30") && thang.equals("04") && nam.equals("1975")){
                    kq.setText("Đúng rồi");
                }
                else {
                    kq.setText("Sai rồi");
                }
            }
        });
    }
}