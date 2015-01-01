package com.burtonh.powerschedulingclock;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by burtonh on 12/23/2014.
 */
public class PowerSchedulingClockMinutelyUpdateService extends Service {
    String intFormat = "%02d";

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //updateWidget(intent);

        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        //int[] widgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        //for(int i = 0; i < widgetIds.length; i++) {
          //  int appWidgetId = widgetIds[i];

            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.hourEndingValue, String.format(intFormat, Utilities.getLocalHourEnding()));
            views.setTextViewText(R.id.hourEndingUtcValue, String.format(intFormat, Utilities.getUtcHourEnding()));
            views.setTextViewText(R.id.utcHour, String.format(intFormat, Utilities.getUtcHour()));
            views.setTextViewText(R.id.utcMin, String.format(intFormat, Utilities.getMinute()));
            ComponentName widget = new ComponentName(this, PowerSchedulingClockWidgetProvider.class);

            manager.updateAppWidget(widget, views);
        //}
        //return super.onStartCommand(intent, flags, startId);
        return START_REDELIVER_INTENT;
        //return START_NOT_STICKY;
    }

    private void updateWidget(Intent intent) {
        //RemoteViews views = getViews();
        //views = updateViews(views);
        //publishUpdate(views);
        //Log.d("WidgetProvider", "Update Called");
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] widgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        for(int i = 0; i < widgetIds.length; i++) {
            int appWidgetId = widgetIds[i];

            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.hourEndingValue, String.format(intFormat, Utilities.getLocalHourEnding()));
            views.setTextViewText(R.id.hourEndingUtcValue, String.format(intFormat, Utilities.getUtcHourEnding()));
            views.setTextViewText(R.id.utcHour, String.format(intFormat, Utilities.getUtcHour()));
            views.setTextViewText(R.id.utcMin, String.format(intFormat, Utilities.getMinute()));
            //ComponentName widget = new ComponentName(this, PowerSchedulingClockWidgetProvider.class);

            manager.updateAppWidget(appWidgetId, views);
        }
    }

    private RemoteViews getViews() {
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
        return views;
    }

    private RemoteViews updateViews(RemoteViews views) {
        views.setTextViewText(R.id.hourEndingValue, String.format(intFormat, Utilities.getLocalHourEnding()));
        views.setTextViewText(R.id.hourEndingUtcValue, String.format(intFormat, Utilities.getUtcHourEnding()));
        views.setTextViewText(R.id.utcHour, String.format(intFormat, Utilities.getUtcHour()));
        views.setTextViewText(R.id.utcMin, String.format(intFormat, Utilities.getMinute()));
        return views;
    }

    private void publishUpdate(RemoteViews views) {
        ComponentName widget = new ComponentName(this, PowerSchedulingClockWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(widget, views);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
