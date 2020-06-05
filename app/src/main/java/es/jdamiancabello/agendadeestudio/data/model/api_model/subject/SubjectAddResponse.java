package es.jdamiancabello.agendadeestudio.data.model.api_model.subject;

import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class SubjectAddResponse {
    private boolean error;
    private String message;
    private Subject subject;

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
}
