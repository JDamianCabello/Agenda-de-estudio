package es.jdamiancabello.agendadeestudio.data.model.api_model.subject;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class SubjectRestoredResponse {
    private boolean error;
    private String message;
    private Subject subject;
    private List<Topic> topicsRestored;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Topic> getTopicsRestored() {
        return topicsRestored;
    }

    public void setTopicsRestored(List<Topic> topicsRestored) {
        this.topicsRestored = topicsRestored;
    }
}
