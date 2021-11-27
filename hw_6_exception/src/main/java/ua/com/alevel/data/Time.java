package ua.com.alevel.data;

import lombok.Data;

@Data
public abstract class Time {
    protected long time;
    protected long milliseconds;
    protected long seconds;
    protected long minutes;
    protected long hours;
    protected long days;
    protected long months;
    protected long years;

    public abstract boolean isLeapYear(long years);
    public abstract long daysInYear(long year);
    public abstract long getTime();
    public abstract long getYearsFromMilliseconds(long milliseconds);
    public abstract long getMonthsFromMilliseconds(long milliseconds);
    public abstract long getDaysFromMilliseconds(long milliseconds);
    public abstract long getHoursFromMilliseconds(long milliseconds);
    public abstract long getMinutesFromMilliseconds(long milliseconds);
    public abstract long getSecondsFromMilliseconds(long milliseconds);
    public abstract long getMillisecondsFromMilliseconds(long milliseconds);
    public abstract long daysInMonth(long numberOfMonth, long years);
}
