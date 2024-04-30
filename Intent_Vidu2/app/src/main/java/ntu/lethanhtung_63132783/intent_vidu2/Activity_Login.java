package ntu.lethanhtung_63132783.intent_vidu2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void XacNhan(View v)
    {


        EditText edtUserName;
        EditText edtPass;
        EditText edtMail;

        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);
        edtMail = findViewById(R.id.edtMail);

        String username = edtUserName.getText().toString();
        String pass = edtPass.getText().toString();
        String mail = edtMail.getText().toString();

        Intent iHome;
        //Xử lý đăng nhập
        if(pass.equals("123"))
        {
            iHome = new Intent(this, Activity_Home.class);
            //Hiểu thị kết quả nhận được lên màn hình Home
           iHome.putExtra("UserName",username);
           setResult(RESULT_OK,iHome);

        }
        else {
            Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_LONG).show();

        }





    }
}