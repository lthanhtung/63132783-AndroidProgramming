package com.example.luyen_tap_tong_hop;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AutoCompleteViewActivity extends AppCompatActivity {

    AutoCompleteTextView autoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auto_complete_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<String> listData = new ArrayList<>();
        listData.add("Dandadan");
        listData.add("Dragon ball");
        listData.add("Kamen rider amazon");
        listData.add("Attach on tian");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line,listData
        );

        autoData = findViewById(R.id.autoCompleteView);
        autoData.setAdapter(adapter);
        autoData.setThreshold(1);

    }
}