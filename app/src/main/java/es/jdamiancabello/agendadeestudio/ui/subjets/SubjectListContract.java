package es.jdamiancabello.agendadeestudio.ui.subjets;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public interface SubjectListContract{
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void noSubjets();
        void refresh(ArrayList<Subject> subjectArrayList);
        void startDeleteView(Subject subject);
        void checkEmptyAdapter();
    }

    interface Presenter{
        void startDelete(Subject subject);
        void load();
        void onDelete(Subject subject);
    }
}
