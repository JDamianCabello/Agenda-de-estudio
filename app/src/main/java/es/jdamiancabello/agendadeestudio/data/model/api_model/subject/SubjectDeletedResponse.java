package es.jdamiancabello.agendadeestudio.data.model.api_model.subject;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class SubjectDeletedResponse {
    private boolean error;
    private String message;
    private Subject deletedSubject;
    private List<Topic> topicsDeleted;

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

    public void setDeletedSubject(Subject subject) {
        this.deletedSubject = subject;
    }

    public Subject getDeletedSubject() { return deletedSubject; }

    public List<Topic> getTopicsDeleted() {
        return topicsDeleted;
    }

    public void setTopicsDeleted(List<Topic> topicsDeleted) {
        this.topicsDeleted = topicsDeleted;
    }
}
