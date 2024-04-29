package ntu.lethanhtung_63132783.intent_vidu2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    public void MhNhapLieu(View v)
    {
        Intent MhNhap = new Intent(this, MainActivity_NhapLieu.class);
        startActivityForResult(MhNhap,8000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 8000)
        {
            if (resultCode == RESULT_OK) {
                String HotenNhap = data.getStringExtra("Hoten");
                int NamSinhNhap = data.getIntExtra("namsinh", 2023);
                TextView textHoten = findViewById(R.id.textView_hoten);
                TextView textNamSinh = findViewById(R.id.textView_namSinh);
                textHoten.setText(HotenNhap);
                textNamSinh.setText(String.valueOf(NamSinhNhap));
            }
            else Toast.makeText(this, "Trả về thất bại", Toast.LENGTH_SHORT).show();
        }
        else  super.onActivityResult(requestCode, resultCode, data);
    }
}