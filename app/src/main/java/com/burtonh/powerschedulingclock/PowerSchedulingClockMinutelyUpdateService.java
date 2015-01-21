package com.burtonh.powerschedulingclock;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class PowerSchedulingClockMinutelyUpdateService extends Service {
    String intFormat = "%02d";

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
        views.setTextViewText(R.id.hourEndingLocalValue, String.format(intFormat, Utilities.getLocalHourEnding()));
        views.setTextViewText(R.id.hourEndingUtcValue, String.format(intFormat, Utilities.getUtcHourEnding()));
        views.setTextViewText(R.id.clockHour, String.format(intFormat, Utilities.getUtcHour()));
        views.setTextViewText(R.id.clockMinute, String.format(intFormat,Utilities.getMinute()));
        ComponentName widget = new ComponentName(this, PowerSchedulingClockWidgetProvider.class);

        manager.updateAppWidget(widget, views);

        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
