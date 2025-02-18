package thi.lethanhtung_63132783.dethi2;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment ChonFragment = null;
                int itemID  = menuItem.getItemId();
                if (itemID == R.id.Cau1){
                    ChonFragment = new WelcomeFragment();
                } else if (itemID == R.id.Cau2) {
                    ChonFragment = new UnitConverterFragment();
                } else if (itemID == R.id.Cau3) {
                    ChonFragment = new WelknownCoffeeFragment();
                } else if (itemID == R.id.Cau4) {
                    ChonFragment = new SubjectsFragment();
                } else if (itemID == R.id.Cau5) {
                    ChonFragment = new MyCVFragment();
                }
                if (ChonFragment != null)
                {
            getSupportFragmentManager().beginTransaction().replace(R.id.HienThiFragment,ChonFragment).commit();
                }
                return true;
            }
        });
    }
}