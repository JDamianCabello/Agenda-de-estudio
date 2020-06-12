package es.jdamiancabello.agendadeestudio.ui.subjectinfo;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class SubjectInfoContract {
    interface View extends BaseView<Presenter> {
        void refresh(List<Topic> topicList);
        void onDeleted(Topic topic);
        void onNewTopicAdd(Topic newTopic, int newPercent);
        void noTopics();
        void onSuccessDelete(Topic topic, int newPercent);
    }

    interface Presenter{
        void loadData(int idSubject);
        void startDeleted(Topic topic);
        void onConfirmDelete(Topic topic);
        void addTopic(Topic topic, int idSubject);
    }
}
