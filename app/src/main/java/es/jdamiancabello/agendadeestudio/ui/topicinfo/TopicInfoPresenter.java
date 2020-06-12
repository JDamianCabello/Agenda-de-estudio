package es.jdamiancabello.agendadeestudio.ui.topicinfo;

import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository;

public class TopicInfoPresenter implements TopicInfoContract.Presenter, TopicRepository.TopicRepositoryAddListener {
    private TopicInfoContract.View view;

    public TopicInfoPresenter(TopicInfoContract.View view) {
        this.view = view;
    }

    @Override
    public void updateTopicData(Topic topic) {
        TopicRepository.getInstance().updateTopic(this,topic);
    }

    @Override
    public void onSuccessUpdated() {
        view.onUpdated();
    }

    @Override
    public void onNotUpdated() {
        view.onErrorInUpdated();
    }
}
