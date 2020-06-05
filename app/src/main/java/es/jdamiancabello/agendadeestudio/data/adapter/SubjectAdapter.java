package es.jdamiancabello.agendadeestudio.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private ArrayList<Subject> list;
    private onManegeSubjectListener listener;

    public SubjectAdapter(SubjectAdapter.onManegeSubjectListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_v2_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {

        holder.bind(list.get(position), listener);
        holder.tvSubjectName.setText(list.get(position).getSubject_name());
        holder.tvSubjectName.setTextColor(list.get(position).getColor());
        holder.tvSubjectDate.setText(list.get(position).getExam_date());
        holder.tvSubjectProgressNumber.setText(Integer.toString(list.get(position).getPercent()));
        holder.progressBar.setProgress(list.get(position).getPercent());
        holder.icon.setBackgroundResource(list.get(position).getIconId());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEdditSubject(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void clear() {
        list.clear();
    }

    public void addAll(ArrayList<Subject> subjectArrayList) {
        list.addAll(subjectArrayList);
    }

    public void removeSubject(Subject subject) {
        this.list.remove(subject);
        this.notifyDataSetChanged();
    }

    public void addSubject(Subject subject) {
        list.add(subject);
    }

    public void sortByName() {
        this.list.sort(new Subject.SortByName());
        this.notifyDataSetChanged();
    }
    public void sortByColor() {
        this.list.sort(new Subject.SortByColor());
        this.notifyDataSetChanged();
    }

    public void sortByExam() {
        this.list.sort(new Subject.SortByExamdate());
        this.notifyDataSetChanged();
    }


    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSubjectName, tvSubjectDate, tvSubjectProgressNumber;
        private ProgressBar progressBar;
        public LinearLayout topicList;
        public ImageButton edit;
        public ImageView icon;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.subjectItem_subjectName);
            tvSubjectDate = itemView.findViewById(R.id.subjectItem_subjectDate);
            tvSubjectProgressNumber = itemView.findViewById(R.id.subjectItem_subjectProgressNumber);
            progressBar = itemView.findViewById(R.id.subjectItem_subjectProgressBar);
            topicList = itemView.findViewById(R.id.subjectItem_topicList);
            edit = itemView.findViewById(R.id.subjectItem_subjectExpand);
            icon = itemView.findViewById(R.id.subjectItem_imageView_icon);
        }

        public void bind(final Subject subject, final onManegeSubjectListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShowSubjectInfo(subject);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onDeleteSubjectListener(subject);
                    return true;
                }
            });
        }
    }

    public interface onManegeSubjectListener {
        void onShowSubjectInfo(Subject subject);
        void onDeleteSubjectListener(Subject subject);
        void onEdditSubject(Subject subject);
    }
}





