package es.jdamiancabello.agendadeestudio.ui.subjets;


import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectListPresenter implements SubjectListContract.Presenter, SubjectRepository.RepositorySubject,SubjectRepository.DeleteSubject{
    private SubjectListContract.View view;

    public SubjectListPresenter(SubjectListContract.View vista) {
        this.view = vista;
    }

    @Override
    public void startDelete(Subject subject) {
        view.startDeleteView(subject);
    }

    public void load(){
        view.showProgress();
        SubjectRepository.getInstance().getSubjectList(this);
    }


    @Override
    public void onDelete(Subject subject) {
        SubjectRepository.getInstance().deleteSubject(this, subject);
    }

    @Override
    public void onSucessUndo(Subject subject) {
    }

    @Override
    public void onLoaded(List<Subject> subjectList) {
        view.hideProgress();
        view.refresh((ArrayList<Subject>) subjectList);
    }

    @Override
    public void onDeleted() {
        //Evento que notifica cuando se ha eliminado una asignatura, no se usa en la vista
    }
}
