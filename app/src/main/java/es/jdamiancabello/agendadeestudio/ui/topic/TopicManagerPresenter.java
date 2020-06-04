package es.jdamiancabello.agendadeestudio.ui.topic;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicManagerPresenter implements TopicManagerContract.Presenter{
    private TopicManagerContract.View view;

    public TopicManagerPresenter(TopicManagerContract.View view) {
        this.view = view;
    }

    @Override
    public void addTopict(String subjectName, String name, int state) {
//        if(!notNullString(name)) {
//            if (TopicRepository_room.getInstance().insert(new Topic(subjectName, name, state)) != -1)
//                view.onSucess();
//            else
//                view.showGenericError("Ya existe ese tema en esa asignatura, prueba con otro nombre");
//        }else{
//            view.showGenericError("El nombre del tema no puede estar vacío");
//        }
    }

    @Override
    public void modifyTopic(String subjectName, String name, int state) {
//        TopicRepository_room.getInstance().update(new Topic(subjectName,name,state));
//        view.onSucess();
    }

    @Override
    public List<Subject> getSubjectList() {
//        return SubjectRepository_room.getInstance().getList();
        return null;
    }

    private boolean notNullString(String s){
        return s.trim().isEmpty();
    }
}
