package com.corejava.time;

import java.util.Calendar;

public class Time {
    
    private int day, month, year, hour, minute, second;

    public Time(int hour, int minute, int second, int day, int month, int year) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Time() {
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR_OF_DAY);
        minute = date.get(Calendar.MINUTE);
        second = date.get(Calendar.SECOND);
        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
        year = date.get(Calendar.YEAR);
    }


    public String toString() {
        return String.format("%02d:%02d:%02d %02d.%02d.%02d", this.hour, this.minute, this.second,
                this.day, this.month, this.year % 100);
    }
    
    public String now() {
        return new Time().toString();
    }
}
