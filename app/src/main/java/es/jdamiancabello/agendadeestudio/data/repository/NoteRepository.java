package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Note;

public class NoteRepository {
    private List<Note> noteList;
    public static NoteRepository getInstance;

    static {
        getInstance = new NoteRepository();
    }

    public NoteRepository(){
        this.noteList = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        noteList.add(new Note("What is Lorem Ipsum?","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
        noteList.add(new Note("Hola mundo","Yo soy la segunda nota"));
        noteList.add(new Note("Soy un título demasiado largo de test","Soy un título demasiado largo de test"));
    }

    public List<Note> getNoteList(){
        return this.noteList;
    }
}
