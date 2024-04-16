package ntu.lethanhtung_63132783.viewpager_fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class QuocGiaAdapter extends FragmentStateAdapter {
    List<QuocGia> QuocGiaList;

    public QuocGiaAdapter(@NonNull FragmentActivity fragmentActivity,List<QuocGia> quocgiaList) {
        super(fragmentActivity);
        QuocGiaList = quocgiaList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        QuocGia quocGiaX = QuocGiaList.get(position);
        QuocGiaFragment quocGiaFragment = new QuocGiaFragment(quocGiaX);
        return quocGiaFragment;
    }

    @Override
    public int getItemCount() {
        return QuocGiaList.size();
    }
}
