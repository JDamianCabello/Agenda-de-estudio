package es.jdamiancabello.agendadeestudio.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class FocusListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent i = new Intent(context,FocusService.class);
//        i.setAction("ServicioLlamado");
//        context.startService(i);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, FocusService.class));
        } else {
            context.startService(new Intent(context, FocusService.class));
        }
    }
}
