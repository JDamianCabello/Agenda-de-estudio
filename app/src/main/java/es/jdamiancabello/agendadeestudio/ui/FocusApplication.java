package es.jdamiancabello.agendadeestudio.ui;

import android.app.Application;
import android.content.Context;

import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.room_database.Database;

public class FocusApplication extends Application {
    static Context context;
    public static User user;
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Database.create(context);
    }

    public static Context getUserContext(){
        return context;
    }

    public User getUser() {
        return user;
    }

    public static void setUser(User u) {
        user = u;
    }
}
