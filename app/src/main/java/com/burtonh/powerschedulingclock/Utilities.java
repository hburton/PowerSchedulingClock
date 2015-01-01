package com.burtonh.powerschedulingclock;

import android.text.format.Time;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by burtonh on 12/19/2014.
 */
public class Utilities {

    private static final int MINUTE_OF_SCHEDULING_ROLL_OVER = 45;
    private static final int HOURS_IN_A_DAY = 24;

    public static int getLocalHourEnding(){
        return getHourEnding(Calendar.getInstance());
    }

    public static int getUtcHourEnding(){
        return getHourEnding(Calendar.getInstance(TimeZone.getTimeZone(Time.TIMEZONE_UTC)));
    }

    private static int getHourEnding(Calendar calendar){
        int currentHourEnding = calendar.get(Calendar.HOUR_OF_DAY) + 1;
        int schedulingHourEnding;

        if(getMinute() < MINUTE_OF_SCHEDULING_ROLL_OVER){
            schedulingHourEnding = currentHourEnding + 1;
        }
        else{
            schedulingHourEnding = currentHourEnding + 2;
        }

        schedulingHourEnding %= HOURS_IN_A_DAY;

        if(schedulingHourEnding == 0)
            return HOURS_IN_A_DAY;

        return schedulingHourEnding;
    }

    public static int getUtcHour(){
        return Calendar.getInstance(TimeZone.getTimeZone(Time.TIMEZONE_UTC)).get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(){
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
}
