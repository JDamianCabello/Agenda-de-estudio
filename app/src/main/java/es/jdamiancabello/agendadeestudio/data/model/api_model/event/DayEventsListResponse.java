package es.jdamiancabello.agendadeestudio.data.model.api_model.event;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Event;

public class DayEventsListResponse {
    private String date;
    private boolean error;
    private List<Event> events;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
