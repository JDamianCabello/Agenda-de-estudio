package es.jdamiancabello.agendadeestudio.ui.calendar;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class CalendarContract {
    interface View{
        void putEvents(List<Subject> subjectList);
        void setPresenter(Presenter presenter);
    }

    interface Presenter{
        void load();
    }
}
