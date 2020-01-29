package es.jdamiancabello.agendadeestudio.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.DependencyViewHolder> {
    private ArrayList<Subject> list;
    private onManegeSubjectListener listener;

    public SubjectAdapter(SubjectAdapter.onManegeSubjectListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
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
        holder.tvSubjectState.setText(getState(list.get(position).getEstate_priority()));
        holder.bind(list.get(position), listener);
    }

    private String getState(int estate_priority) {
        String aux = "";
        switch (estate_priority){
            case 0:
                aux = FocusApplication.getUserContext().getString(R.string.SubjectAdapter_state_0);
                break;
            case 1:
                aux = FocusApplication.getUserContext().getString(R.string.SubjectAdapter_state_1);
                break;
            case 2:
                aux = FocusApplication.getUserContext().getString(R.string.SubjectAdapter_state_2);
                break;
            case 3:
                aux = FocusApplication.getUserContext().getString(R.string.SubjectAdapter_state_3);
                break;
        }
        return aux;
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





