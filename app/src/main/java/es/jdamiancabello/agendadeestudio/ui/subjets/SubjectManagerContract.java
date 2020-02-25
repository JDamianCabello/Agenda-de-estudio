package es.jdamiancabello.agendadeestudio.ui.subjets;

import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class SubjectManagerContract {
    public interface View extends BaseView<Presenter>{


    }

    public interface Presenter{
        void addSubject(String name, String examDate, int subjectColor);
        void modifySubject(String name, String examDate, int subjectColor);
    }
}
