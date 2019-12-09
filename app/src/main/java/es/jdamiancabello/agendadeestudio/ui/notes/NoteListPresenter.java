package es.jdamiancabello.agendadeestudio.ui.notes;

import es.jdamiancabello.agendadeestudio.data.repository.NoteRepository;

public class NoteListPresenter implements NoteListContract.Presenter {
    private NoteListContract.View view;

    public NoteListPresenter(NoteListContract.View view) {
        this.view = view;
    }

    @Override
    public void load() {
        view.refresh(NoteRepository.getInstance.getNoteList());
    }
}
