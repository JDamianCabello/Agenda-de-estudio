package es.jdamiancabello.agendadeestudio.data.model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int idUser;
    private String userName;
    private String email;
    private String password;

    public int getIdUser() {
        return idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userName, String email, String password) {
        this.idUser = count.incrementAndGet();
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
