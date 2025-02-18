package thi.lethanhtung_63132783.dethi2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelknownCoffeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelknownCoffeeFragment extends Fragment {
    Land_Adapter adapter;
    ArrayList<LandScape> ListData;
    RecyclerView recyclerView;

    public WelknownCoffeeFragment() {
        // Required empty public constructor
    }


    public static WelknownCoffeeFragment newInstance(String param1, String param2) {
        WelknownCoffeeFragment fragment = new WelknownCoffeeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListData = new ArrayList<LandScape>();
        //Chuẩn bị dữ liệu
        ListData.add(new LandScape("Quán Highlands Coffee","quan_highlands_coffee"));
        ListData.add(new LandScape("Quán milano","milano"));
        ListData.add(new LandScape("Quán Cà phê Hoàng Tuấn","ca_phe_hoang_tuan_nha_trang"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewCau3 = inflater.inflate(R.layout.fragment_cau3, container, false);
        recyclerView = viewCau3.findViewById(R.id.RecylerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(viewCau3.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Land_Adapter(viewCau3.getContext(),ListData);
        recyclerView.setAdapter(adapter);
        return viewCau3;
    }
}