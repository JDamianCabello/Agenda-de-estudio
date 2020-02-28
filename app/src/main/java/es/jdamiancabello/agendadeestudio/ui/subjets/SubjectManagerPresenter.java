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
        if(SubjectRepository_room.getInstance().insert(new Subject(name,examDate,subjectColor)) != -1)
            view.onSucess(new Subject(name,examDate,subjectColor));
        else
            view.showGenericError("Ya existe esa asignatura, prueba otro nombre");

    }

    @Override
    public void modifySubject(String name, String examDate, int subjectColor) {
        SubjectRepository_room.getInstance().update(new Subject(name,examDate,subjectColor));
        view.onSucess(new Subject(name,examDate,subjectColor));
    }
}
