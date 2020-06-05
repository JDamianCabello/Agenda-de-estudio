package es.jdamiancabello.agendadeestudio.data.model.api_model.topic;

import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicModifyResponse {
    private boolean error;
    private String message;
    private Topic oldTopic;
    private Topic updatedTopic;

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

    public Topic getOldTopic() {
        return oldTopic;
    }

    public void setOldTopic(Topic oldTopic) {
        this.oldTopic = oldTopic;
    }

    public Topic getUpdatedTopic() {
        return updatedTopic;
    }

    public void setUpdatedTopic(Topic updatedTopic) {
        this.updatedTopic = updatedTopic;
    }
}
