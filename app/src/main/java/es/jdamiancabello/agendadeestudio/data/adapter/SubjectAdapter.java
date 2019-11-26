package es.jdamiancabello.agendadeestudio.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.DependencyViewHolder> {
    private ArrayList<Subject> list;
    private onManegeSubjectListener listener;

    public SubjectAdapter() {
        list = (ArrayList<Subject>) SubjectRepository.getInstance().getSubjectList();
    }

    @NonNull
    @Override
    public DependencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item, parent, false);
        return new DependencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DependencyViewHolder holder, int position) {
        holder.tvSubjectName.setText(list.get(position).getName());
        holder.tvSubjectState.setText(list.get(position).getStateEnum().toString());

        holder.bind(list.get(position), listener);
    }

    public void setOnManageSubjectListener(onManegeSubjectListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DependencyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubjectName, tvSubjectState;

        public DependencyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.subjectItem_subjectName);
            tvSubjectState = itemView.findViewById(R.id.subjectItem_subjectState);
        }

        public void bind(final Subject subject, final onManegeSubjectListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditSubjectListener(subject);
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
        void onEditSubjectListener(Subject subject);
        void onDeleteSubjectListener(Subject subject);
    }
}





