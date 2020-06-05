package es.jdamiancabello.agendadeestudio.ui.subjets;


import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class SubjectListPresenter implements SubjectListContract.Presenter, SubjectRepository.RepositorySubject, SubjectRepository.DeleteSubject, SubjectRepository.RestoreSubject {
    private SubjectListContract.View view;

    public SubjectListPresenter(SubjectListContract.View vista) {
        this.view = vista;
    }

    @Override
    public void delete(Subject subject) {
        SubjectRepository.getInstance().deleteSubject(this, subject);
    }

    public void load(){
        view.showProgress();
        SubjectRepository.getInstance().getSubjectList(this);
    }


    @Override
    public void undo(Subject subject, List<Topic> topicList) {
        view.onUndo(subject,topicList);
    }

    @Override
    public void onSucessUndo(Subject subject, List<Topic> subjectTopics) {
        SubjectRepository.getInstance().restoreSubject(this, subject);
    }

    @Override
    public void onLoaded() {
        view.hideProgress();
        view.refresh((ArrayList<Subject>)SubjectRepository.getInstance().getList());
    }

    @Override
    public void onDeleted(Subject deletedSubject, List<Topic> deletedTopicsList) {
        view.onSuccessDeleted(deletedSubject, deletedTopicsList);
    }

    @Override
    public void onRestored(Subject subject) {
        view.onSucessUndo(subject);
    }
}
