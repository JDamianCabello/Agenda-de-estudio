package es.jdamiancabello.agendadeestudio.ui;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.model.User;
import es.jdamiancabello.agendadeestudio.data.service.FocusBroadcastReceiver;
import es.jdamiancabello.agendadeestudio.ui.dashboard.DashboardActivity;

public class FocusApplication extends Application {
    public static final String CHANNEL_ID = "1000";
    static Context context;
    public static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        createNotificationChannel();
    }

    public static Context getUserContext(){
        return context;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User u) {
        FocusBroadcastReceiver broadcastReceiver = new FocusBroadcastReceiver();
        broadcastReceiver.onReceive(context, new Intent(context, DashboardActivity.class));
        user = u;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Focus", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Notificacion");
        channel.enableLights(true);
        channel.setLightColor(Color.BLUE);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
