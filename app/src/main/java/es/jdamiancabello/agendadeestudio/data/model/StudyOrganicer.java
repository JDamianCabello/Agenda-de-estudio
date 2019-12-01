package es.jdamiancabello.agendadeestudio.data.model;

import java.util.List;

public class StudyOrganicer {
    private String dateTime;
    private int duration;
    private String durationQuantifier; //Esto muestra el tipo de duración : ejemplo in duration 4 y aqui iria horas, minutos o dias.
    private Subject subject;
    private String eventTitle;

    public StudyOrganicer(String dateTime, int duration, String durationQuantifier, Subject subject) {
        this.dateTime = dateTime;
        this.duration = duration;
        this.durationQuantifier = durationQuantifier;
        this.subject = subject;
        this.eventTitle = subject.getStateEnum().toString();
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDurationQuantifier() {
        return durationQuantifier;
    }

    public void setDurationQuantifier(String durationQuantifier) {
        this.durationQuantifier = durationQuantifier;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}
