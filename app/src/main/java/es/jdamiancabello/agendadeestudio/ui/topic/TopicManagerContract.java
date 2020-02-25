package es.jdamiancabello.agendadeestudio.ui.topic;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class TopicManagerContract {
    public interface View extends BaseView<Presenter> {


    }

    public interface Presenter{
        void addTopict(String subjectName, String name, int state);
        void modifyTopic(String subjectName, String name, int state);
        List<Subject> getSubjectList();
    }
}
