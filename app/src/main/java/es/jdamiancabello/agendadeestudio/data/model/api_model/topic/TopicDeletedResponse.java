package es.jdamiancabello.agendadeestudio.data.model.api_model.topic;

import java.util.List;

import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicDeletedResponse {
    private boolean error;
    private String message;
    private Topic topicsDeleted;

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

    public Topic getTopicsDeleted() {
        return topicsDeleted;
    }

    public void setTopicsDeleted(Topic topicsDeleted) {
        this.topicsDeleted = topicsDeleted;
    }
}
