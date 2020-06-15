package es.jdamiancabello.agendadeestudio.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private List<Topic> topicList;
    private ItemActions itemActions;
    private Context context;

    public TopicAdapter(ItemActions actions, Context context) {
        this.topicList = new ArrayList<>();
        itemActions = actions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(topicList.get(position).isTask())
            holder.topicName.setText(context.getResources().getString(R.string.topic_item_isTask)+ ": "+topicList.get(position).getName());
        else
            holder.topicName.setText(topicList.get(position).getName());
        holder.topicState.setText(getState(topicList.get(position).getState(), topicList.get(position).isTask()));
        holder.topicProgress.setProgress((topicList.get(position).getState() * 100) / 3);
        holder.topicPercent.setText(holder.topicProgress.getProgress() + "%");
        holder.imagePriority.setImageResource(getPriorityImage(topicList.get(position).getPriority()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActions.onClick(topicList.get(position));
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemActions.onLongClick(topicList.get(position));
                return true;
            }
        });
    }

    private int getPriorityImage(int priority) {
        switch (priority){
            case 0:
                return R.drawable.ic_warninglow;
            case 1:
                return R.drawable.ic_warningmed;
            case 2:
                return R.drawable.ic_warningred;
        }
        return R.drawable.checkbox_unchecked;
    }

    private String getState(int state, boolean isTask) {
        if(isTask){
            switch (state){
                case 0:
                    return context.getResources().getString(R.string.topic_item_task_state_0);
                case 3:
                    return context.getResources().getString(R.string.topic_item_task_state_3);
            }
        }
        else {
            switch (state) {
                case 0:
                    return context.getResources().getString(R.string.topic_item_topic_state_0);
                case 1:
                    return context.getResources().getString(R.string.topic_item_topic_state_1);
                case 2:
                    return context.getResources().getString(R.string.topic_item_topic_state_2);
                case 3:
                    return context.getResources().getString(R.string.topic_item_topic_state_3);
            }
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
        notifyDataSetChanged();
    }

    public void addAll(List<Topic> topics){
        topicList.clear();
        topicList.addAll(topics);
    }

    public void delete(Topic topic) {
        topicList.remove(topic);
    }

    public void sortByName() {
        this.topicList.sort(new Topic.SortByName());
        this.notifyDataSetChanged();
    }

    public void sortByState() {
        this.topicList.sort(new Topic.SortByState());
        this.notifyDataSetChanged();
    }

    public void sortByPriority() {
        this.topicList.sort(new Topic.SortByPriority());
        this.notifyDataSetChanged();
    }

    public void sortByTask() {
        this.topicList.sort(new Topic.SortByTask());
        this.notifyDataSetChanged();
    }

    public void removeTopic(Topic topic) {
        topicList.remove(topic);
        notifyDataSetChanged();
    }


    public interface ItemActions{
        void onClick(Topic topic);
        void onLongClick(Topic topic);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView topicName, topicState, topicPercent;
        public ProgressBar topicProgress;
        public ImageView imagePriority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topicName = itemView.findViewById(R.id.topicitem_topicName);
            topicState = itemView.findViewById(R.id.topicitem_topicState);
            topicProgress = itemView.findViewById(R.id.topicitem_topicStateProgress);
            topicPercent = itemView.findViewById(R.id.topicItem_tv_topicPercent);
            imagePriority = itemView.findViewById(R.id.topicitem_priorityImageView);
        }
    }
}
