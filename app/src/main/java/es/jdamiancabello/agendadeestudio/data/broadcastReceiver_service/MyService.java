package es.jdamiancabello.agendadeestudio.data.broadcastReceiver_service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

import es.jdamiancabello.agendadeestudio.R;
import es.jdamiancabello.agendadeestudio.ui.FocusApplication;
import es.jdamiancabello.agendadeestudio.ui.FragmentActivity;

import static java.security.AccessController.getContext;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        Toast.makeText(this,"Servicio onCreate llamado",Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Notification.Builder builder = new Notification.Builder(this, FocusApplication.CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_book)
                .setContentText("Broadcast llamado")
                .setContentTitle("Notificacion del service");

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        notificationManagerCompat.notify(new Random().nextInt(100), builder.build());

        this.onDestroy();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Servicio onDestroy llamado",Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
