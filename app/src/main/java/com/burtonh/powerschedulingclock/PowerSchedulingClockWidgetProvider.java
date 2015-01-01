package com.burtonh.powerschedulingclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;
import com.burtonh.powerschedulingclock.R;

import java.util.Calendar;

/**
 * Created by burtonh on 12/19/2014.
 */
public class PowerSchedulingClockWidgetProvider extends AppWidgetProvider {

    //private String intFormat = "%02d";
    private PendingIntent service = null;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //super.onUpdate(context, appWidgetManager, appWidgetIds);

        final AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Calendar time = Calendar.getInstance();
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        final Intent intent = new Intent(context, PowerSchedulingClockMinutelyUpdateService.class);
        //intent.setAction("Update Power Scheduling Widget");

        if(service == null){
            service = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        }

        alarm.setRepeating(AlarmManager.RTC, time.getTime().getTime(), 1000 * 60, service);
        //for(int i = 0; i < appWidgetIds.length; i++) {
            //int appWidgetId = appWidgetIds[i];

            //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            //views.setTextViewText(R.id.hourEndingValue, String.format(intFormat, Utilities.getLocalHourEnding()));
            //views.setTextViewText(R.id.hourEndingUtcValue, String.format(intFormat, Utilities.getUtcHourEnding()));
            //views.setTextViewText(R.id.utcHour, String.format(intFormat, Utilities.getUtcHour()));
            //views.setTextViewText(R.id.utcMin, String.format(intFormat, Utilities.getMinute()));

            //appWidgetManager.updateAppWidget(appWidgetId, views);
        //}
    }

    @Override
    public void onDisabled(Context context){
        final AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarm.cancel(service);
    }

 //   private void updateHourEnding(Context context) {
  //      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
  //      int hourEnding = Utilities.getHourEnding();
  //      views.setTextViewText(R.id.hourEndingValue, Integer.toString(hourEnding));
  //  }
}
