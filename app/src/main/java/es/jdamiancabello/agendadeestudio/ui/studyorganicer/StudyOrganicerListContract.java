package es.jdamiancabello.agendadeestudio.ui.studyorganicer;

import java.util.ArrayList;
import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public interface StudyOrganicerListContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void noStudyOrganicers();
        void refresh(ArrayList<StudyOrganicer> studyOrganicerArrayList);
        void onSuccessDeleted();
        void onSucessUndo(StudyOrganicer studyOrganicer);
    }

    interface Presenter{
        void delete(StudyOrganicer studyOrganicer);
        void load();
        void undo(StudyOrganicer studyOrganicer);
    }
}
