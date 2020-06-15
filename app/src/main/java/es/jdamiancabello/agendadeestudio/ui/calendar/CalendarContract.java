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
        void updateEvents(Event event);
        void deleteEvent(Event event);
    }

    interface Presenter{
        void load();
        void deleteEvent(Event event);
        void updateEvent(Event event);
        void addEvent(Event event);
        void getTodayEvents(String date);
    }
}
