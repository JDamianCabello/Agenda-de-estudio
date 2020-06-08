package es.jdamiancabello.agendadeestudio.data.model.api_model.email;

import es.jdamiancabello.agendadeestudio.data.model.User;

public class EmailVerifyCodeResponse {
    private boolean error;
    private String mesage;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
