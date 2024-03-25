package ntu.lethanhtung_63132783.cau2_giaipt;

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
    public void TinhNghiem(View view)
    {

        EditText EdtSoa = findViewById(R.id.EdtSoA);
        EditText EdtSob = findViewById(R.id.EdtSoB);
        EditText EdtSoc = findViewById(R.id.EdtSoc);

        Button btnTinhNghiem = findViewById(R.id.BtnTinhNghiem);
        TextView LoaiPT = findViewById(R.id.LoaiPT);
        EditText delta = findViewById(R.id.Delta);
        TextView textViewx1 = findViewById(R.id.TextViewX1);
        EditText x1 = findViewById(R.id.Nghiem1);
        TextView textViewx2 = findViewById(R.id.TextViewX2);
        EditText x2 = findViewById(R.id.Nghiem2);

        String StrSoA = EdtSoa.getText().toString();
        String StrSoB = EdtSob.getText().toString();
        String StrSoC = EdtSoc.getText().toString();

        double Soa = Double.parseDouble(StrSoA);
        double Sob = Double.parseDouble(StrSoB);
        double Soc = Double.parseDouble(StrSoC);

        if (Soa == 0)
        {
            LoaiPT.setText("Đây là Phương trình bậc 1");
            delta.setText("không thể tính được vì đây là phương trình bậc 1");
            double Nghiem = -Soc/Sob;
            x1.setText(String.valueOf(Nghiem));
            textViewx2.setVisibility(View.GONE);
            x2.setVisibility(View.GONE);
        }
        else
        {
            LoaiPT.setText("Đây là phương trình bậc 2");
            double Delta = Math.pow(Sob,2) - 4*Soa*Soc;
            delta.setText(String.valueOf(Delta));
            if (Delta <0)
            {
                textViewx1.setText("Phương trình vô nghiệm");
                x1.setVisibility(View.GONE);
                x2.setVisibility(View.GONE);
                textViewx2.setVisibility(View.GONE);
            }
            if(Delta == 0)
            {
                double Nghiem = -Sob/(2*Soa);
                x1.setText(String.valueOf(Nghiem));
                textViewx2.setVisibility(View.GONE);
                x2.setVisibility(View.GONE);
            }
            else
            {
                double Nghiem1 = (-Sob + Math.sqrt(Delta))/(2*Soa);
                double Nghiem2 = (-Sob - Math.sqrt(Delta))/(2*Soa);
                x1.setText(String.valueOf(Nghiem1));
                x2.setText(String.valueOf(Nghiem2));
            }
        }



    }
}