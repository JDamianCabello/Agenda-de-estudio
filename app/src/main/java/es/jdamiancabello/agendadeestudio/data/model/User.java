package es.jdamiancabello.agendadeestudio.data.model;


public class User {
    public static final String userKey = "username";
    public static final String passwordKey = "password";

    private String api_token;


    public String getApi_token() {
        return api_token;
    }

    public User(String api_token) {
        this.api_token = api_token;
    }
}
