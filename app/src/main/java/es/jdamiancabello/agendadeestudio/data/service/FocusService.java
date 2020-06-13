package es.jdamiancabello.agendadeestudio.data.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.data.DAO.EventDAO;
import es.jdamiancabello.agendadeestudio.data.DAO.SubjectDAO;
import es.jdamiancabello.agendadeestudio.data.model.Event;
import es.jdamiancabello.agendadeestudio.data.model.Subject;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;

public class FocusService extends IntentService implements EventDAO.EventDaoNotificationListener{

    private static final String name = "FocusService";
    private Notification notificationSuccess;
    private NotificationManagerCompat notificationManager;
    private boolean error = true;


    public FocusService() {
        super(name);
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, FocusApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setContentTitle("Focus getting data")
                .setContentText("Getting all your today events....");
        Notification notification = builder.build();
        startForeground(100, notification);
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        notificationManager = NotificationManagerCompat.from(this);
        EventDAO.getNotificationEvents(this);
    }



    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSuccess(List<Event> eventList) {
        if (eventList.isEmpty())
            return;

        int id= 0;
        for (Event todayEvent : eventList) {
            if(todayEvent.isAppnotification()) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, FocusApplication.CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo)
                        .setColor(todayEvent.getEvent_color())
                        .setContentTitle("Focus: " + todayEvent.getEvent_name())
                        .setContentText(todayEvent.getEvent_resume())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
                notificationSuccess = builder.build();
            }
            notificationManager.notify(id++, notificationSuccess);
        }
    }
}
