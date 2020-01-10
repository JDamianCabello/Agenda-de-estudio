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
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class StudyOrganicerAdapter extends RecyclerView.Adapter<StudyOrganicerAdapter.ViewHolder> {
    private List<StudyOrganicer> studyOrganicerList;
    private OnManageStudyOrganicerListener viewOnManageStudyOrganicerListener;

    public StudyOrganicerAdapter() {
        studyOrganicerList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.studyorganicer_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.organicerDateTime.setText(studyOrganicerList.get(position).getDateTime());
            holder.organicerDuration.setText(Integer.toString(studyOrganicerList.get(position).getDuration()));
            holder.organicerTitle.setText(getState(studyOrganicerList.get(position).getEventTitle()));
            holder.organicerSubtitle.setText(studyOrganicerList.get(position).getSubject().toString());
            holder.organicerTimeQuantifier.setText(studyOrganicerList.get(position).getDurationQuantifier());


        holder.bind(studyOrganicerList.get(position),viewOnManageStudyOrganicerListener);
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

    public void setOnManageStudyOrganicerListener(OnManageStudyOrganicerListener listener){
        this.viewOnManageStudyOrganicerListener = listener;
    }

    @Override
    public int getItemCount() {
        return studyOrganicerList.size();
    }

    public void clear() {
        this.studyOrganicerList.clear();
    }

    public void addAll(ArrayList<StudyOrganicer> studyOrganicerList) {
        this.studyOrganicerList.addAll(studyOrganicerList);
    }

    public void delete(StudyOrganicer studyOrganicer) {
        this.studyOrganicerList.remove(studyOrganicer);
    }

    public void add(StudyOrganicer studyOrganicer) {
        this.studyOrganicerList.add(studyOrganicer);
    }

    public void Sort(Comparator<StudyOrganicer> comparator){
        Collections.sort(studyOrganicerList, comparator) ;
    }

    public void sortByName() {
        studyOrganicerList.sort(new StudyOrganicer.IdSort());
    }

    public interface OnManageStudyOrganicerListener{
        void onDeleteStudyOrganicerListener(StudyOrganicer studyOrganicer);
        void onAddorEditStudyOrganicerListener(StudyOrganicer studyOrganicer);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView organicerDateTime;
        private TextView organicerDuration;
        private TextView organicerTimeQuantifier;
        private TextView organicerTitle;
        private TextView organicerSubtitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            organicerDateTime = itemView.findViewById(R.id.tv_studyOrganicer_dateTime);
            organicerDuration = itemView.findViewById(R.id.tv_studyOrganicer_duration);
            organicerTimeQuantifier = itemView.findViewById(R.id.tv_studyOrganicer_timeQuantifier);
            organicerTitle = itemView.findViewById(R.id.tv_studyOrganicer_title);
            organicerSubtitle = itemView.findViewById(R.id.tv_studyOrganicer_subtitle);
        }

        public void bind(final StudyOrganicer studyOrganicer,final OnManageStudyOrganicerListener onManageStudyOrganicerListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onManageStudyOrganicerListener.onAddorEditStudyOrganicerListener(studyOrganicer);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onManageStudyOrganicerListener.onDeleteStudyOrganicerListener(studyOrganicer);
                    return false;
                }
            });
        }
    }
}
