package es.jdamiancabello.agendadeestudio.ui.subjectinfo;


import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository;

public class SubjectInfoPresenter implements SubjectInfoContract.Presenter, TopicRepository.TopicRepositoryListener, TopicRepository.TopicRepositorDeleteListener {
    private SubjectInfoContract.View view;

    public SubjectInfoPresenter(SubjectInfoContract.View view) {
        this.view = view;
    }


    @Override
    public void loadData(int idSubject) {
        TopicRepository.getInstance().startLoadData(this,idSubject);
    }

    @Override
    public void startDeleted(Topic topic) {
        view.onDeleted(topic);
    }

    @Override
    public void onConfirmDelete(Topic topic) {
        TopicRepository.getInstance().deleteTopic(this, topic.getId());
    }

    @Override
    public void addTopic(Topic topic, int idSubject) {
        TopicRepository.getInstance().addTopic(this,idSubject, topic);
    }

    @Override
    public void onDataLoaded(List<Topic> topicList) {
        view.refresh(topicList);
    }

    @Override
    public void onTopicAdded(Topic topic, int newPercent) {
        view.onNewTopicAdd(topic, newPercent);
    }

    @Override
    public void onSuccessDeleted(Topic topic, int newPercent) {
        view.onSuccessDelete(topic, newPercent);
    }
}
