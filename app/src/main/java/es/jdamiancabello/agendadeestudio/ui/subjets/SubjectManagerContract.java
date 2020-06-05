package es.jdamiancabello.agendadeestudio.ui.subjets;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class SubjectManagerContract {
    public interface View extends BaseView<Presenter>{
    }

    public interface Presenter{
        void addSubject(Subject newSubject);
        void modifySubject(Subject updatedSubject);
    }
}
