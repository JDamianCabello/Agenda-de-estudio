package es.jdamiancabello.agendadeestudio.ui.topicinfo;

import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicInfoContract {
    interface View{
        void setPresenter(Presenter presenter);
        void onUpdated();
        void onErrorInUpdated();
    }

    interface Presenter{
        void updateTopicData(Topic topic);
    }
}
