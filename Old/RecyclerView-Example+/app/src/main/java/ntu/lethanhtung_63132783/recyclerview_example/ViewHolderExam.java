package ntu.lethanhtung_63132783.recyclerview_example;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderExam
        extends RecyclerView.ViewHolder {
    TextView examName;
    TextView examMessage;
    TextView examDate;
    View view;

    ViewHolderExam(View itemView)
    {
        super(itemView);
        examName = (TextView)itemView.findViewById(R.id.examName);
        examDate = (TextView)itemView.findViewById(R.id.DateExam);
        examMessage = (TextView)itemView.findViewById(R.id.examMessage);
        view  = itemView;
    }
}