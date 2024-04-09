package ntu.lethanhtung_63132783.recyclerview_tutorial;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Khai báo các biến toàn cục
    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> ListRecyclerData;
    RecyclerView recyclerViewLandScape;
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
        // Bước 3
        ListRecyclerData = getListRecyclerData();
        // Bước 4
        recyclerViewLandScape = findViewById(R.id.RecyclerViewLand);
        // Bước 5
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerViewLandScape.setLayoutManager(layoutLinear);
        // Bước 6
        landScapeAdapter = new LandScapeAdapter(this,ListRecyclerData);
        // Bước 7
        recyclerViewLandScape.setAdapter(landScapeAdapter);

    }


    //Tạo phương thức chứa các dữ liệu
    ArrayList<LandScape> getListRecyclerData(){
        ArrayList<LandScape> dsdulieu = new ArrayList<>();
        LandScape landScape1 = new LandScape("anh1","Chiều tà");
        dsdulieu.add(landScape1);
        dsdulieu.add(new LandScape("anh2","Bình Minh"));
        dsdulieu.add(new LandScape("anh3","Vui Nhộn"));
        dsdulieu.add(new LandScape("anh4","Đồng Quê"));
    return dsdulieu;
    }

}