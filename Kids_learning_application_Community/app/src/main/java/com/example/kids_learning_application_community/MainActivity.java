package com.example.kids_learning_application_community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnLanding_page;
    Button btnHome_page;

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
        btnLanding_page = findViewById(R.id.buttonLanding_page);
        btnLanding_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLandingPage = new Intent(MainActivity.this, Landing_page_Activity.class);
                startActivity(iLandingPage);
            }
        });

        btnHome_page = findViewById(R.id.buttonHome_page);
        btnHome_page.setOnClickListener(HomePage);
    }
    View.OnClickListener HomePage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent iHomePage = new Intent(MainActivity.this, Home_page_Activity.class);
            startActivity(iHomePage);
        }
    };
}