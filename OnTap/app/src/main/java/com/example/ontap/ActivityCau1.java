package com.example.ontap;

import android.content.Intent;
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

public class ActivityCau1 extends AppCompatActivity {
    Button btn_Home,btn_Cau2;

    EditText edtSoa,edtSob;
    TextView ketqua;
    Button Cong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_Home =findViewById(R.id.btn_Home);
        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome = new Intent(ActivityCau1.this, MainActivity.class);
                startActivity(iHome);
            }
        });

        btn_Cau2 = findViewById(R.id.btn_Cau2);
        btn_Cau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCau2 = new Intent(ActivityCau1.this, ActivityCau2.class);
                startActivity(iCau2);
            }
        });

        edtSoa = findViewById(R.id.edtSoa);
        edtSob = findViewById(R.id.edtSob);
        ketqua = findViewById(R.id.ketqua);
        Cong = findViewById(R.id.btnCong);

        Cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soa = edtSoa.getText().toString();
                String sob = edtSob.getText().toString();

                double soA = Double.parseDouble(soa);
                double soB = Double.parseDouble(sob);

                double kq = soA + soB;
                ketqua.setText(String.valueOf(kq));
            }
        });


    }
}