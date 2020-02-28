package es.jdamiancabello.agendadeestudio.ui;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


public class FocusService extends Service {

//    public FocusService(String name) {
//        super("Servicio");
//    }
//
//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
////        Intent a = new Intent(getApplicationContext(), FragmentActivity.class);
////        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////
////
////        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), new Random().nextInt(100), a, PendingIntent.FLAG_UPDATE_CURRENT);
////
////
////
////        Notification.Builder builder = new Notification.Builder(getApplicationContext(), FocusApplication.CHANNEL_ID)
////                .setAutoCancel(true)
////                .setSmallIcon(R.mipmap.ic_launcher)
////                .setContentText("Se ha iniciado el telefono")
////                .setContentTitle("Listener")
////                .setContentIntent(pendingIntent);
////
////        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
////
////        notificationManagerCompat.notify(new Random().nextInt(100), builder.build());
//
//        Intent i = new Intent(getApplicationContext(),FragmentActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        startActivity(i);
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ServiceZombie", "Ejecución número:");         return START_STICKY;
    }
}
