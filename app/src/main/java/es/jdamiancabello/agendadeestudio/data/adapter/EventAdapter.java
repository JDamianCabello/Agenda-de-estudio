package es.jdamiancabello.agendadeestudio.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> todayEventList;
    private OnManageEventListener onManageEventListener;

    public EventAdapter(OnManageEventListener onManageEventListener) {
        todayEventList = new ArrayList<>();
        this.onManageEventListener = onManageEventListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.eventIcon.setBackgroundResource(todayEventList.get(position).getEvent_iconId());
        holder.eventName.setText(todayEventList.get(position).getEvent_name());
        holder.eventResume.setText(todayEventList.get(position).getEvent_resume());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onManageEventListener.onShowEventInfo(todayEventList.get(position));
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onManageEventListener.onDeleteEvent(todayEventList.get(position));
                return true;
            }
        });
    }
    public void addAll(List<Event> eventList){
        todayEventList.clear();
        todayEventList.addAll(eventList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return todayEventList.size();
    }

    public interface OnManageEventListener{
        void onShowEventInfo(Event event);
        void onDeleteEvent(Event event);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eventName, eventResume;
        private ImageView eventIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.eventItem_subjectName);
            eventResume = itemView.findViewById(R.id.eventItem_tv_eventResume);
            eventIcon = itemView.findViewById(R.id.eventItem_imageView_icon);
        }
    }
}
