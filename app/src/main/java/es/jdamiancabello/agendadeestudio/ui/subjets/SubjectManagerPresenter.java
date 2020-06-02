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
        if(!notNullString(name) && !notNullString(examDate)) {
            if (SubjectRepository_room.getInstance().insert(new Subject(name, examDate, subjectColor, 0)) != -1)
                view.onSucess(new Subject(name, examDate, subjectColor,0));
            else
                view.showGenericError("Ya existe esa asignatura, prueba otro nombre");
        }
        else{
            view.showGenericError("Uno de los campos está vacío.");
        }
    }

    private boolean notNullString(String s){
        return s.trim().isEmpty();
    }

    @Override
    public void modifySubject(String name, String examDate, int subjectColor) {
        SubjectRepository_room.getInstance().update(new Subject(name,examDate,subjectColor,0));
        view.onSucess(new Subject(name,examDate,subjectColor,0));
    }
}
