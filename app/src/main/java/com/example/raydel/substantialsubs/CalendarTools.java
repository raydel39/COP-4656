package com.example.raydel.substantialsubs;

import java.util.Calendar;

/**
 * Created by Raydel on 5/2/2016.
 */
public class CalendarTools {
    public static Calendar CurrentCalendar = Calendar.getInstance(),
                            range_StartDate= Calendar.getInstance(),
                            range_EndDate= Calendar.getInstance();

    public static int startDay_int,
                      endDay_int;

    public static int calendarToInt(Calendar cal)
    {
        int year = cal.getTime().getYear() + 1900,
            month = (cal.getTime().getMonth())+1,
            day = cal.getTime().getDate();

        return  year*10000+month*100+day;
    }
}
