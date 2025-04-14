package vn.edu.lethanhtung.replacing_fragments_dynamically;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FooterFragment extends Fragment {

    public FooterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_footer, container, false);

        // Tìm các điều khiển nút bấm
        Button nut1,nut2,nut3;
        nut1 = view.findViewById(R.id.buttonOne);
        nut2 = view.findViewById(R.id.buttonTwo);
        nut3 = view.findViewById(R.id.buttonThree);


        //Tìm Fragment Manager
        FragmentManager fragmentManager = getParentFragmentManager();

        //Thiết lập sự kiện cho các nút
        nut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView_Content,new Fragment_One())
                        .commit();
            }
        });

        //Thiết lập sự kiện cho các nút
        nut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView_Content,new Fragment_Two())
                        .commit();
            }
        });

        //Thiết lập sự kiện cho các nút
        nut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView_Content,new Fragment_Three())
                        .commit();
            }
        });



        return view;

    }
}