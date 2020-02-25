package es.jdamiancabello.agendadeestudio.ui.subjets;


import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository_room;

public class SubjectManagerPresenter implements SubjectManagerContract.Presenter, SubjectRepository.ManageSubject{
    private SubjectManagerContract.View view;

    public SubjectManagerPresenter(SubjectManagerContract.View view) {
        this.view = view;
    }

    @Override
    public void onSaved() {
        view.onSucess();
    }

    @Override
    public void addSubject(String name, String examDate, int subjectColor) {
        SubjectRepository_room.getInstance().insert(new Subject(name,examDate,subjectColor));
    }

    @Override
    public void modifySubject(String name, String examDate, int subjectColor) {
        SubjectRepository_room.getInstance().update(new Subject(name,examDate,subjectColor));
    }
}
