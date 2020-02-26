package es.jdamiancabello.agendadeestudio.ui.subjets;


import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository_room;
import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository_room;

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
        view.onUndo(subject,TopicRepository_room.getInstance().getListFromSubject(subject.getSubject_name()));
    }

    @Override
    public void onSucessUndo(Subject subject, List<Topic> subjectTopics) {
        SubjectRepository_room.getInstance().insert(subject);
        for (int i = 0; i < subjectTopics.size(); i++) {
            TopicRepository_room.getInstance().insert(subjectTopics.get(i));
        }
        view.onSucessUndo(subject);
    }

    @Override
    public List<Topic> getTopicsBySubject(String s) {
        return TopicRepository_room.getInstance().getListFromSubject(s);
    }


    @Override
    public void onLoaded() {

    }
}
