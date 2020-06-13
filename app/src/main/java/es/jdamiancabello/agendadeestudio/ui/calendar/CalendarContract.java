package es.jdamiancabello.agendadeestudio.ui.calendar;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Event;
import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class CalendarContract {
    interface View{
        void putEvents(List<Event> eventList);
        void setPresenter(Presenter presenter);
        void onSuccesAdded(Event event);
        void loadTodayEvent(List<Event> eventList);
        void noTodayEvents();
    }

    interface Presenter{
        void load();
        void addEvent(Event event);
        void getTodayEvents(String date);
    }
}
