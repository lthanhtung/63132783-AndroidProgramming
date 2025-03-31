package com.example.ontap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_ChuyenThongTin extends AppCompatActivity {
    ImageView youtuberImg;
    TextView youtuberName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuyen_thong_tin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        youtuberImg = findViewById(R.id.Youtuberimage);
        youtuberName = findViewById(R.id.YoutuberName);

        Intent intentThongTin = getIntent();
        String YoutuberName = intentThongTin.getStringExtra("youtuberName");
        String YoutuberImg = intentThongTin.getStringExtra("youtuberImg");

        //Hiển thị
        youtuberName.setText(YoutuberName);

        //Ảnh
        String packageName = getPackageName();
        int ImgId =getResources().getIdentifier(YoutuberImg,"mipmap",packageName);
        youtuberImg.setImageResource(ImgId);

    }
}