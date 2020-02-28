package es.jdamiancabello.agendadeestudio.ui;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.room_database.Database;

public class FocusApplication extends Application {
    public static final String CHANNEL_ID = "1000";
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
        createNotificationChannel();
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

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
