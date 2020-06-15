package es.jdamiancabello.agendadeestudio.data.model.api_model.event;

import es.jdamiancabello.agendadeestudio.data.model.Event;

public class EventUpdateResponse {
    private boolean error;
    private String message;
    private Event oldEvent;
    private Event updatedEvent;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Event getOldEvent() {
        return oldEvent;
    }

    public void setOldEvent(Event oldEvent) {
        this.oldEvent = oldEvent;
    }

    public Event getUpdatedEvent() {
        return updatedEvent;
    }

    public void setUpdatedEvent(Event updatedEvent) {
        this.updatedEvent = updatedEvent;
    }
}
