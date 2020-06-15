package es.jdamiancabello.agendadeestudio.data.model.api_model.event;

import es.jdamiancabello.agendadeestudio.data.model.Event;

public class EventDeleteResponse {
    private boolean error;
    private  String message;
    private Event deletedEvent;

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

    public Event getDeletedEvent() {
        return deletedEvent;
    }

    public void setDeletedEvent(Event deletedEvent) {
        this.deletedEvent = deletedEvent;
    }
}
