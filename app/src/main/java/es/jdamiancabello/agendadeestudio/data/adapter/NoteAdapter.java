package es.jdamiancabello.agendadeestudio.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> noteList;
    private OnNoteClickListener onNoteClickListener;

    public NoteAdapter(OnNoteClickListener onNoteClickListener) {
        noteList = new ArrayList<>();
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String aux = "";
        if(noteList.get(position).getTitle().length() > 21)
            aux = noteList.get(position).getTitle().substring(0,21) + "...";
        else
            aux= noteList.get(position).getTitle();

        holder.title.setText(aux);


        if(noteList.get(position).getContent().length()> 179)
            aux = noteList.get(position).getContent().substring(0,179) + "...";
        else
            aux = noteList.get(position).getContent();

        holder.content.setText(aux);
        holder.bind(noteList.get(position),onNoteClickListener);
    }

    public void addAll(List<Note> notes){
        this.noteList.addAll(notes);
    }

    public void clear(){
        this.noteList.clear();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface OnNoteClickListener{
        void onModify(Note note);
        void onDelete(Note note);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notes_tvTitle);
            content = itemView.findViewById(R.id.notes_tvContent);
        }

        public void bind(final Note note, OnNoteClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNoteClickListener.onModify(note);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onNoteClickListener.onDelete(note);
                    return false;
                }
            });
        }
    }
}
