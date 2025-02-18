package thi.lethanhtung_63132783.dethi2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UnitConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UnitConverterFragment extends Fragment {
    public UnitConverterFragment() {
        // Required empty public constructor
    }
    public static UnitConverterFragment newInstance(String param1, String param2) {
        UnitConverterFragment fragment = new UnitConverterFragment();
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
        // Inflate the layout for this fragment
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