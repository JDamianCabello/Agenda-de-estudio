package es.jdamiancabello.agendadeestudio.data.model.api_model.subject;

import es.jdamiancabello.agendadeestudio.data.model.Subject;

public class SubjectModifyResponse {
    private boolean error;
    private String message;
    private Subject oldSubject;
    private Subject updatedSubject;

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

    public Subject getOldSubject() {
        return oldSubject;
    }

    public void setOldSubject(Subject oldSubject) {
        this.oldSubject = oldSubject;
    }

    public Subject getUpdatedSubject() {
        return updatedSubject;
    }

    public void setUpdatedSubject(Subject updatedSubject) {
        this.updatedSubject = updatedSubject;
    }
}
