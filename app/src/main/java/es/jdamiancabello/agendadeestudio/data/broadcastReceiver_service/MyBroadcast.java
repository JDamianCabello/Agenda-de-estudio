package es.jdamiancabello.agendadeestudio.data.broadcastReceiver_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import es.jdamiancabello.agendadeestudio.ui.dashboard.DashboardActivity;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, DashboardActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            Toast.makeText(context, "Broadcast iniciando servicio", Toast.LENGTH_LONG).show();
            context.startService(new Intent(context, MyService.class));
    }
}
