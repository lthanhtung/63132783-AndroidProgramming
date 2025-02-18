package ntu.lethanhtung_63132783.recyclerview_example;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImgAdapterExam Adapter;
    RecyclerView recyclerView;
    ClickListiner clickListiner;
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
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("");
        setActionBar(toolbar);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(toolbar);
        linearLayout.addView(scrollView);
        setContentView(linearLayout);


        List<DateExam> list = new ArrayList<DateExam>();
        list = getData();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_Example);
        clickListiner  = new ClickListiner() {
            @Override
            public void click(int index){
                Toast.makeText(MainActivity.this, "Click item index is" + index, Toast.LENGTH_SHORT).show();
            }
        };
        Adapter = new ImgAdapterExam(list, getApplication(),clickListiner);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private List<DateExam> getData()
    {
        List<DateExam> list = new ArrayList<>();
        list.add(new DateExam("First Exam",
                "May 23, 2015",
                "Best Of Luck"));
        list.add(new DateExam("Second Exam",
                "June 09, 2015",
                "b of l"));
        list.add(new DateExam("My Test Exam",
                "April 27, 2017",
                "This is testing exam .."));

        return list;
    }
}