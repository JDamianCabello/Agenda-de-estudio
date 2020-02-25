package es.jdamiancabello.agendadeestudio.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private ArrayList<Subject> list;
    private onManegeSubjectListener listener;
    private final int TOTALMAXTOPICSTATE = 3;

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
        holder.tvSubjectName.setText(list.get(position).getName());
        holder.tvSubjectName.setTextColor(list.get(position).getColor());
        holder.tvSubjectDate.setText(list.get(position).getDate());
        //holder.tvSubjectProgressNumber.setText(Integer.toString(getProgress(dataExample())));
        holder.tvSubjectProgressNumber.setText(Integer.toString(0));
        holder.progressBar.setProgress(Integer.parseInt(holder.tvSubjectProgressNumber.getText().toString()));
    }


    private int getProgress(List<Topic> topics) {
        if(topics.isEmpty() || topics == null)
            return 0;

        int aux = 0;
        for (Topic t:topics) {
            aux += t.getState();
        }
        return (aux * 100) / (topics.size()*TOTALMAXTOPICSTATE);
        //TODO: mirar si el porcentaje está bien
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
        list.remove(subject);
    }

    public void addSubject(Subject subject) {
        list.add(subject);
    }

    public void sortByName(Comparator comparator) {
        Collections.sort(list,comparator);
    }


    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSubjectName, tvSubjectDate, tvSubjectProgressNumber;
        private ProgressBar progressBar;
        public LinearLayout topicList;
        private ImageButton expand;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.subjectItem_subjectName);
            tvSubjectDate = itemView.findViewById(R.id.subjectItem_subjectDate);
            tvSubjectProgressNumber = itemView.findViewById(R.id.subjectItem_subjectProgressNumber);
            progressBar = itemView.findViewById(R.id.subjectItem_subjectProgressBar);
            topicList = itemView.findViewById(R.id.subjectItem_topicList);
            expand = itemView.findViewById(R.id.subjectItem_subjectExpand);
        }

        public void bind(final Subject subject, final onManegeSubjectListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShowTopics(null, v);
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
        void onShowTopics(List<Topic> topicsList, View view);
        void onDeleteSubjectListener(Subject subject);
    }
}





