package com.example.ex3_simplesumapp;

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

public class MainActivity extends AppCompatActivity {

    EditText EdtSoA,EdtSoB;
    TextView ViewKQ;
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
    //Tìm điều khiển
        EdtSoA = findViewById(R.id.edtSoa);
        EdtSoB = findViewById(R.id.edtSoB);
        ViewKQ = findViewById(R.id.ViewKetQua);
    }
    public void Sum(View v){
        String Soa = EdtSoA.getText().toString();
        String Sob = EdtSoB.getText().toString();

        double soA = Double.parseDouble(Soa);
        double soB = Double.parseDouble(Sob);

        double sum = soA + soB;

        ViewKQ.setText(String.valueOf(sum));
    }

}