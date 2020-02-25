package es.jdamiancabello.agendadeestudio.ui.topic;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class TopicListContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void noTopics();
        void refresh(List<Topic> topicList);
        void onSuccessDeleted(Topic topic);
        void onSucessUndo(Topic topic);
        void onUndo(Topic topic);
    }

    interface Presenter{
        void delete(Topic topic);
        void load();
        void undo(Topic topic);
        void onSucessUndo(Topic topic);
    }
}
