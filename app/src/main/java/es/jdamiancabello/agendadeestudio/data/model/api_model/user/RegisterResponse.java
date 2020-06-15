package es.jdamiancabello.agendadeestudio.data.model.api_model.user;

import es.jdamiancabello.agendadeestudio.data.model.User;

public class RegisterResponse {

    private String error;

    private String message;

    private User user;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
