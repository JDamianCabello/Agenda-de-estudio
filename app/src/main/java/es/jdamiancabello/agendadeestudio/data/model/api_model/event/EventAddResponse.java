package es.jdamiancabello.agendadeestudio.data.model.api_model.event;

import es.jdamiancabello.agendadeestudio.data.model.Event;

public class EventAddResponse {
    private boolean error;
    private String mesage;
    private Event event;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
