package es.jdamiancabello.agendadeestudio.ui.subjets;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository_room;

public class SubjectListPresenter implements SubjectListContract.Presenter, SubjectRepository.RepositorySubject {
    private SubjectListContract.View view;

    public SubjectListPresenter(SubjectListContract.View vista) {
        this.view = vista;
    }

    @Override
    public void delete(Subject subject) {
        if (SubjectRepository_room.getInstance().delete(subject)) {
            view.onSuccessDeleted(subject);
        }
    }

    public void load(){
        view.showProgress();
        if(SubjectRepository_room.getInstance().getList().size() == 0)
            view.noSubjets();
        else {
            view.refresh((ArrayList<Subject>) SubjectRepository_room.getInstance().getList());
        }
        view.hideProgress();
    }


    @Override
    public void undo(Subject subject) {
        view.onUndo(subject);
    }

    @Override
    public void onSucessUndo(Subject subject) {
//        if(SubjectRepository.getInstance().addSubject(subject))
//            view.onSucessUndo(subject);
    }


    @Override
    public void onLoaded() {

    }
}
