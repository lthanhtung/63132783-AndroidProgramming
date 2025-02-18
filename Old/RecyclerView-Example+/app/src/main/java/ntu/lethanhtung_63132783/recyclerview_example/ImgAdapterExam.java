package ntu.lethanhtung_63132783.recyclerview_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;


public class ImgAdapterExam extends RecyclerView.Adapter<ViewHolderExam> {

    List<DateExam> dateExamList = Collections.emptyList();
    Context context;
    ClickListiner clickListiner;

    public ImgAdapterExam(List<DateExam> dateExamList, Context context, ClickListiner clickListiner) {
        this.dateExamList = dateExamList;
        this.context = context;
        this.clickListiner = clickListiner;
    }


    @NonNull
    @Override
    public ViewHolderExam onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View PhotoView =layoutInflater.inflate(R.layout.example_card,parent,false);
        ViewHolderExam viewHolderExam = new ViewHolderExam(PhotoView);
        return viewHolderExam;
    }

    @Override
    public void onBindViewHolder(final ViewHolderExam viewHolderExam,final int position) {
        final int index = viewHolderExam.getAdapterPosition();
        viewHolderExam.examName.setText(dateExamList.get(position).Ten);
        viewHolderExam.examDate.setText(dateExamList.get(position).Ngay);
        viewHolderExam.examMessage.setText(dateExamList.get(position).TinNhan);
        viewHolderExam.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListiner.click(index);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dateExamList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
