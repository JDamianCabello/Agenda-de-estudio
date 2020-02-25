package es.jdamiancabello.agendadeestudio.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    List<Topic> topicList;

    public TopicAdapter(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.topicName.setText(topicList.get(position).getName());
        holder.topicState.setText(getState(topicList.get(position).getState()));
        holder.topicProgress.setProgress((topicList.get(position).getState() * 100) / 3);
    }

    private String getState(int state) {
        switch (state){
            case 0:
                return "Ignorado";
            case 1:
                return "Leido";
            case 2:
                return "Resumido";
            case 3:
                return "Dominado";
        }
        return "";
    }

    public void clear(){
        topicList.clear();
    }

    public List<Topic> getTopicList(){
        return topicList;
    }

    public void add(Topic t){
        topicList.add(t);
    }

    public void addAll(List<Topic> topics){
        topics.clear();
        topics.addAll(topics);
    }


    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView topicName, topicState;
        public ProgressBar topicProgress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topicName = itemView.findViewById(R.id.topicitem_topicName);
            topicState = itemView.findViewById(R.id.topicitem_topicState);
            topicProgress = itemView.findViewById(R.id.topicitem_topicStateProgress);
        }
    }
}
