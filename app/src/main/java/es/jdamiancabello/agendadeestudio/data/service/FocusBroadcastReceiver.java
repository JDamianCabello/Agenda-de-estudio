package es.jdamiancabello.agendadeestudio.data.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class FocusBroadcastReceiver extends BroadcastReceiver {

    public static final String FOCUS_RUNSERVICE = "es.jdamiancabello.agendadeestudio.data.Service.FOCUS_RUNSERVICE";
    private static final int REQUEST_SERVICE = 1994;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            makeNewService(context);
        }
        else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                context.startForegroundService(new Intent(context, FocusService.class));
            else
                context.startService(new Intent(context, FocusService.class));
        }
    }

    private void makeNewService(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                PendingIntent.getBroadcast(context, REQUEST_SERVICE, new Intent(context, FocusBroadcastReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
