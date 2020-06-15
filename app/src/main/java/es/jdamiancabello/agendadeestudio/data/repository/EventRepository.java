package es.jdamiancabello.agendadeestudio.data.repository;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.DAO.EventDAO;
import es.jdamiancabello.agendadeestudio.data.model.Event;

public class EventRepository implements EventDAO.EventDaoListener{
    public static EventRepository eventRepository;
    public static EventRepository getInstance(){
        return eventRepository;
    }

    static {
        eventRepository = new EventRepository();
    }

    public static EventsRepositoryListener eventsRepositoryListener;

    public void getEventsOfDate(EventsRepositoryListener eventsRepositoryListener, String date){
        this.eventsRepositoryListener = eventsRepositoryListener;
        EventDAO.getDateEvents(this,date);

    }

    public void getAllUserEvents(EventsRepositoryListener eventsRepositoryListener){
        this.eventsRepositoryListener = eventsRepositoryListener;
        EventDAO.getAllEvents(this);

    }

    public void addEvent(EventsRepositoryListener eventsRepositoryListener, Event event) {
        this.eventsRepositoryListener = eventsRepositoryListener;
        EventDAO.addEvent(this,event);
    }

    public void updateEvent(EventsRepositoryListener eventsRepositoryListener, Event event) {
        this.eventsRepositoryListener = eventsRepositoryListener;
        EventDAO.updateEvent(this,event);
    }


    public void deleteEvent(EventsRepositoryListener eventsRepositoryListener, Event event) {
        this.eventsRepositoryListener = eventsRepositoryListener;
        EventDAO.deleteEvent(this,event);
    }

    @Override
    public void onGetData(List<Event> eventList) {
        eventsRepositoryListener.onGetData(eventList);
    }

    @Override
    public void onUpdated(Event event) {
        eventsRepositoryListener.onSuccesUpdated(event);
    }

    @Override
    public void onAdded(Event event) {
        eventsRepositoryListener.onSuccessAdded(event);
    }

    @Override
    public void gettingAllEvents(List<Event> eventList) {
        eventsRepositoryListener.onGetAllEvents(eventList);
    }

    @Override
    public void onDeleted(Event event) {
        eventsRepositoryListener.onSuccesDelete(event);
    }


    public interface EventsRepositoryListener{
        void onGetData(List<Event> eventList);
        void onSuccessAdded(Event event);
        void onGetAllEvents(List<Event> eventList);
        void onSuccesUpdated(Event event);
        void onSuccesDelete(Event event);
    }
}
