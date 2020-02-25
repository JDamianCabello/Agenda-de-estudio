package es.jdamiancabello.agendadeestudio.ui.topic;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository_room;
import es.jdamiancabello.agendadeestudio.data.repository.TopicRepository_room;

public class TopicManagerPresenter implements TopicManagerContract.Presenter{
    private TopicManagerContract.View view;

    public TopicManagerPresenter(TopicManagerContract.View view) {
        this.view = view;
    }

    @Override
    public void addTopict(String subjectName, String name, int state) {
        TopicRepository_room.getInstance().insert(new Topic(subjectName,name,state));
        view.onSucess();
    }

    @Override
    public void modifyTopic(String subjectName, String name, int state) {
        TopicRepository_room.getInstance().update(new Topic(subjectName,name,state));
        view.onSucess();
    }

    @Override
    public List<Subject> getSubjectList() {
        return SubjectRepository_room.getInstance().getList();
    }
}
