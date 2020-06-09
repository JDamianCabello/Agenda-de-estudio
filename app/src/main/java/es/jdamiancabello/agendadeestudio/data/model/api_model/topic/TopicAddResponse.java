package es.jdamiancabello.agendadeestudio.data.model.api_model.topic;

import es.jdamiancabello.agendadeestudio.data.model.Topic;

public class TopicAddResponse {
    private boolean error;
    private String message;
    private int percent;
    private Topic topic;

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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
