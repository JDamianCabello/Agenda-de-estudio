package es.jdamiancabello.agendadeestudio.ui.calendar;


import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Event;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.repository.EventRepository;
import es.jdamiancabello.agendadeestudio.data.repository.SubjectRepository;

public class CalendarPresenter implements CalendarContract.Presenter, EventRepository.EventsRepositoryListener {
    private CalendarContract.View view;

    public CalendarPresenter(CalendarContract.View view) {
        this.view = view;
    }

    @Override
    public void load() {
        EventRepository.getInstance().getAllUserEvents(this);
    }

    @Override
    public void deleteEvent(Event event) {
        EventRepository.getInstance().deleteEvent(this,event);
    }

    @Override
    public void updateEvent(Event event) {
        EventRepository.getInstance().updateEvent(this,event);
    }

    @Override
    public void addEvent(Event event) {
        EventRepository.getInstance().addEvent(this,event);
    }

    @Override
    public void getTodayEvents(String date) {
        EventRepository.getInstance().getEventsOfDate(this,date);
    }

    @Override
    public void onGetData(List<Event> eventList) {
        if(eventList.isEmpty())
            view.noTodayEvents();
        else
            view.loadTodayEvent(eventList);
    }

    @Override
    public void onSuccessAdded(Event event) {
        view.onSuccesAdded(event);
    }

    @Override
    public void onGetAllEvents(List<Event> eventList) {
        view.putEvents(eventList);
    }

    @Override
    public void onSuccesUpdated(Event event) {
        view.updateEvents(event);
    }

    @Override
    public void onSuccesDelete(Event event) {
        view.deleteEvent(event);
    }
}
