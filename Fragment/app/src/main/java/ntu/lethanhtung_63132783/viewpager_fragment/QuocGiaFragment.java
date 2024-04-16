package ntu.lethanhtung_63132783.viewpager_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class QuocGiaFragment extends Fragment {
    QuocGia quocgia;
    public QuocGiaFragment(QuocGia quocGia) {
        // Required empty public constructor
    quocgia = quocGia;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFrg =  inflater.inflate(R.layout.fragment_quoc_gia, container, false);
        TextView textViewTenQuocGia = viewFrg.findViewById(R.id.textViewTenQuocGia);
        TextView textViewDanSo = viewFrg.findViewById(R.id.textViewDanSo);
        ImageView ImgViewCo = viewFrg.findViewById(R.id.imageViewCo);
        textViewTenQuocGia.setText(quocgia.getTenQuocGia());
        textViewDanSo.setText("Dân số: " + String.valueOf( quocgia.getDanSo()));
        int resId = viewFrg.getResources().getIdentifier(quocgia.getCoQuocGia(), "mipmap", viewFrg.getContext().getPackageName());
        ImgViewCo.setImageResource(resId);
        return viewFrg;
    }
}