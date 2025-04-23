package vn.edu.lethanhtung.ex9_bottomnavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Cau2Fragment extends Fragment {



    public Cau2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cau2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Cau2Fragment newInstance(String param1, String param2) {
        Cau2Fragment fragment = new Cau2Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewCau2 = inflater.inflate(R.layout.fragment_cau2, container, false);
        EditText EdtSoKm = viewCau2.findViewById(R.id.editKm);
        EditText EdtSoKg = viewCau2.findViewById(R.id.editKg);
        EditText EdtSoByte = viewCau2.findViewById(R.id.editSoByte);
        TextView textViewM =viewCau2.findViewById(R.id.textSom);
        TextView textViewG =viewCau2.findViewById(R.id.textSog);
        TextView textViewBit =viewCau2.findViewById(R.id.textBit);
        Button BtnChuyen = viewCau2.findViewById(R.id.buttonChuyen);
        BtnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soKm = EdtSoKm.getText().toString();
                String soKg = EdtSoKg.getText().toString();
                String soByte = EdtSoByte.getText().toString();
                double sokm = Double.parseDouble(soKm);
                double sokg = Double.parseDouble(soKg);
                double sobyte = Double.parseDouble(soByte);
                double ChuyenKM = sokm * 1000;
                double ChuyenKG = sokg * 1000;
                double ChuyenByte = sobyte * 8;
                textViewM.setText(String.valueOf(ChuyenKM));
                textViewG.setText(String.valueOf(ChuyenKG));
                textViewBit.setText(String.valueOf(ChuyenByte));
            }
        });

        return viewCau2;

    }
}