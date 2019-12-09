package es.jdamiancabello.agendadeestudio.ui.notes;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Note;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class NoteListContract {
    interface View extends BaseView<Presenter> {
        void refresh(List<Note> noteList);
    }

    interface Presenter{
        void load();
    }
}
