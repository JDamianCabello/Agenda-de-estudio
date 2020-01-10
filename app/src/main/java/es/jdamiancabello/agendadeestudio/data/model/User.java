package es.jdamiancabello.agendadeestudio.data.model;


public class User {
    public static final String userKey = "username";
    public static final String passwordKey = "password";
    public static final String userToken = "token";

    private String api_token;


    public String getApi_token() {
        return api_token;
    }

    public User(String api_token) {
        this.api_token = api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
