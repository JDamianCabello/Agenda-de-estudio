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

    @Override
    public void onGetData(List<Event> eventList) {
        eventsRepositoryListener.onGetData(eventList);
    }

    @Override
    public void onAdded(Event event) {
        eventsRepositoryListener.onSuccessAdded(event);
    }

    @Override
    public void gettingAllEvents(List<Event> eventList) {
        eventsRepositoryListener.onGetAllEvents(eventList);
    }


    public interface EventsRepositoryListener{
        void onGetData(List<Event> eventList);
        void onSuccessAdded(Event event);
        void onGetAllEvents(List<Event> eventList);
    }
}
