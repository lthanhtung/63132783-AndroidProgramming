package com.example.luyentap_appquizcong;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    int ktra;
    int Caudung = 0;
    int solandoi = 0;

    public void ClickKqDung(View view)
    {
        Button BtnA = findViewById(R.id.BtnA);
        Button BtnB = findViewById(R.id.BtnB);
        Button BtnC = findViewById(R.id.BtnC);
        Button BtnD = findViewById(R.id.BtnD);
        Button BtnGen = findViewById(R.id.BtnGen);
        TextView TextKq = findViewById(R.id.TextKq);
        //Caudung++ : Nếu để bên trong thi khi ấn biến câu đúng sẽ bị reset
        String Srtkq = BtnA.getText().toString();
        int kq = Integer.parseInt(Srtkq);
        if (ktra == kq)
        {
            Caudung++;
            solandoi++;
            RanDomSo(view);
        }
        TextKq.setText(String.valueOf(Caudung) +"/"+String.valueOf(solandoi));
        if (solandoi == 10)
        {
            Toast.makeText(this, "Chúc mừng bạn trả lời được "+ Caudung  +" câu đúng!", Toast.LENGTH_SHORT).show();
            BtnA.setEnabled(false);
            BtnB.setEnabled(false);
            BtnC.setEnabled(false);
            BtnD.setEnabled(false);
            BtnGen.setEnabled(false);
        }
    }
    public void ClickKqSai(View view)
    {
        Button BtnA = findViewById(R.id.BtnA);
        Button BtnB = findViewById(R.id.BtnB);
        Button BtnC = findViewById(R.id.BtnC);
        Button BtnD = findViewById(R.id.BtnD);
        Button BtnGen = findViewById(R.id.BtnGen);
        TextView TextKq = findViewById(R.id.TextKq);
        solandoi++;
        RanDomSo(view);
        TextKq.setText(String.valueOf(Caudung) +"/"+String.valueOf(solandoi));
        if (solandoi == 10)
        {
            Toast.makeText(this, "Chúc mừng bạn trả lời được "+ Caudung  +" câu đúng!", Toast.LENGTH_SHORT).show();
            BtnA.setEnabled(false);
            BtnB.setEnabled(false);
            BtnC.setEnabled(false);
            BtnD.setEnabled(false);
            BtnGen.setEnabled(false);
        }
    }

    public void RanDomSo(View view)
    {
        EditText EdtNum1 = findViewById(R.id.TextNum1);
        EditText EdtNum2 = findViewById(R.id.TextNum2);

        Random random = new Random();
        int so1 = random.nextInt(101);
        int so2 = random.nextInt(101);
        String strSo1 = String.valueOf(so1);
        String strSo2 = String.valueOf(so2);
        int tong = so1 + so2;


        EdtNum1.setText(strSo1);
        EdtNum2.setText(strSo2);

        Button BtnA = findViewById(R.id.BtnA);
        Button BtnB = findViewById(R.id.BtnB);
        Button BtnC = findViewById(R.id.BtnC);
        Button BtnD = findViewById(R.id.BtnD);

        BtnA.setText(String.valueOf(tong));
        BtnB.setText(String.valueOf(tong+3));
        BtnC.setText(String.valueOf(tong *2));
        BtnD.setText(String.valueOf(tong + 9));
        ktra = tong;




    }
}