package es.jdamiancabello.agendadeestudio.ui.studyorganicer;

import java.util.ArrayList;

import es.jdamiancabello.agendadeestudio.data.model.StudyOrganicer;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.base.BaseView;

public interface StudyOrganicerManageContract {
    interface View extends BaseView<Presenter> {

        void setupContentList(ArrayList<Subject> subjects);

        void onDurationEmpty(String error);
        void onDurationNumber(String error);
        void onTimeEmpty(String error);
        void onTimeQuantifier(String error);


        void onClearErrorDurationEmpty();
        void onClearErrorDurationNumber();
        void onClearErroronTimeEmpty();
        void onClearErrorTimeQuantifier();
    }

    interface Presenter{
        void onViewCreated();
        void onAddStudyOrganicer(StudyOrganicer studyOrganicer);
        void onModifyStudyOrganicer(StudyOrganicer studyOrganicer);
        int getPosition(Subject subject);
    }
}
