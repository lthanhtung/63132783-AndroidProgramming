package ntu.lethanhtung_63132783.bottomnavigation_fragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AlgorithmFragment()).commit();

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment ChonFragment = null;
        int itemId = item.getItemId();
        if(itemId == R.id.Profile){
            ChonFragment = new ProfileFragment();
        } else if (itemId == R.id.algorithm) {
            ChonFragment = new AlgorithmFragment();

        } else if (itemId == R.id.PhuongTrinh) {
            ChonFragment = new PhuongTrinhFragment();
        }
        if (ChonFragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,ChonFragment).commit();
        }
        return true;
    };
}