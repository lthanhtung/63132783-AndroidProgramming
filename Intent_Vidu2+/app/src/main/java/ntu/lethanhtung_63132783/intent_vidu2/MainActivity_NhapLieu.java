package ntu.lethanhtung_63132783.intent_vidu2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity_NhapLieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_nhap_lieu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Save_Close(View v)
    {
        EditText edtHoTen;
        EditText edtNamSinh;

        edtHoTen = findViewById(R.id.editTextHoten);
        edtNamSinh = findViewById(R.id.editTextNamSinh);

        String hoten = edtHoTen.getText().toString();
        int NamSinh = Integer.parseInt(edtNamSinh.getText().toString());
        Intent MhKqNhapLieu = 
    }

}