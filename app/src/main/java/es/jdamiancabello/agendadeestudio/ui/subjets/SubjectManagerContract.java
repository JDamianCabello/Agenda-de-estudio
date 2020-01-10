package es.jdamiancabello.agendadeestudio.ui.subjets;

import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public class SubjectManagerContract {
    public interface View extends BaseView<Presenter>{


    }

    public interface Presenter{
        void addSubject(String name, int state);
        void modifySubject(int id, String name, int state);
    }
}
