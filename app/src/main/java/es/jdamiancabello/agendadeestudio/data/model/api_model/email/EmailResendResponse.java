package es.jdamiancabello.agendadeestudio.data.model.api_model.email;

public class EmailResendResponse {
    private boolean error;
    private String mesage;

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
}
