package com.example.self_service_gate.data;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class Converters {
    @TypeConverter
    public long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalendar(long value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}
