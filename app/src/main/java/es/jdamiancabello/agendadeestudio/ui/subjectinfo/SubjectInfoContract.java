package es.jdamiancabello.agendadeestudio.ui.subjectinfo;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class SubjectInfoContract {
    interface View extends BaseView<Presenter> {
        void refresh(List<Topic> topicList);
        void noTopics();
    }

    interface Presenter{
        void loadData(int idSubject);
    }
}
