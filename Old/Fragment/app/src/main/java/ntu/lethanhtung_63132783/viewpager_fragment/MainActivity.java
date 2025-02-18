package ntu.lethanhtung_63132783.viewpager_fragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<QuocGia> dsQuocGia;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    QuocGiaAdapter quocGiaAdapter;

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
        dsQuocGia = new ArrayList<QuocGia>();
        QuocGia qg1 = new QuocGia("Việt Nam","vn",80);
        QuocGia qg2 = new QuocGia("Trung Quốc","ch",200);
        QuocGia qg3 = new QuocGia("Nga","ru",120);
        QuocGia qg4 = new QuocGia("Mỹ","us",70);
        dsQuocGia.add(qg1);
        dsQuocGia.add(qg2);
        dsQuocGia.add(qg3);
        dsQuocGia.add(qg4);

        viewPager2 = findViewById(R.id.ViewPagerQuocGia);
        quocGiaAdapter = new QuocGiaAdapter(this,dsQuocGia);
        viewPager2.setAdapter(quocGiaAdapter);
        //sử dụng tabLayout
        tabLayout = findViewById(R.id.TabQuocGia);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> tab.setText("Quốc gia"+ (position +1)));
        tabLayoutMediator.attach();
    }
}