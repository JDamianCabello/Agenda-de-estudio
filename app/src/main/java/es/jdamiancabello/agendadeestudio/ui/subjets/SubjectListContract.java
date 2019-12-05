package es.jdamiancabello.agendadeestudio.ui.subjets;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public interface SubjectListContract{
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void noSubjets();
        void refresh(ArrayList<Subject> subjectArrayList);
        void onSuccessDeleted();
        void onSucessUndo(Subject subject);
    }

    interface Presenter{
        void delete(Subject subject);
        void load();
        void undo(Subject subject);
    }
}
