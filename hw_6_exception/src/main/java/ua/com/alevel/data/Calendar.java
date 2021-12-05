package ua.com.alevel.data;

import ua.com.alevel.exceptions.ExceptionNix;
import ua.com.alevel.util.CalendarUtil;

import java.io.Serializable;

import static ua.com.alevel.constant.Constant.*;

public class Calendar extends Time implements Serializable {

    static long numberOfMonth;
    static long numberOfYears;

    public Calendar() { }

    public Calendar(long millisecond, long second, long minute, long hour, long day, long month,
                    long year) {
        super.milliseconds = millisecond;
        super.seconds = second;
        super.minutes = minute;
        super.hours = hour;
        super.days = day;
        super.months = month;
        super.years = year;
        super.time = (millisecond) + (second * MILLISECONDS_PER_SECOND) + (minute * MILLISECONDS_PER_MINUTE)
                + (hour * MILLISECONDS_PER_HOUR)
                + (day * MILLISECONDS_PER_DAY) + (getMillisecondsInMonths(month, year))
                + (getMillisecondsInYear(year));
    }

    public Calendar(long milliseconds) {
        if (milliseconds < 0) {
            try {
                throw new ExceptionNix("Exception: milliseconds cant be < 0");
            } catch (ExceptionNix e) {
                e.printStackTrace();
            }
        }
        super.time = milliseconds;
        super.milliseconds = getMillisecondsFromMilliseconds(milliseconds);
        super.seconds = getSecondsFromMilliseconds(milliseconds);
        super.minutes = getMinutesFromMilliseconds(milliseconds);
        super.hours = getHoursFromMilliseconds(milliseconds);
        super.days = getDaysFromMilliseconds(milliseconds);
        super.months = getMonthsFromMilliseconds(milliseconds);
        super.years = getYearsFromMilliseconds(milliseconds);
    }

    private long getMillisecondsInYear(long year) {
        long time = 0;
        for (int i = 1; i < year; i++) {
            time += (daysInYear(i) * MILLISECONDS_PER_DAY);
        }
        for (int i = 1; i <= year/100; i++) {
            if (year >= ((100L *i)+1)) {
                time += (MILLISECONDS_PER_DAY);
            }
            if (year >= ((400L *i)+1)) {
                time -= (MILLISECONDS_PER_DAY);
            }
        }
        return time;
    }

    public long getMillisecondsInMonths(long month, long year) {
        long time = 0;
        if (month == 1) {
            return daysInMonth(1, year) * MILLISECONDS_PER_DAY;
        }
        if(month == 2){
            month = 3;
        }
        for (int i = 1; i < month; i++) {
            time += (daysInMonth(i, year) * MILLISECONDS_PER_DAY);
        }
        return time;
    }

    @Override
    public boolean isLeapYear(long years) {
        return (years % 400 == 0) || ((years % 4 == 0) && (years % 100 != 0));
    }

    @Override
    public long daysInYear(long year) {
        if (isLeapYear(year)) {
            return 366;
        }
        return 365;
    }

    @Override
    public long getTime() {
        return super.time;
    }

    public long getMonthsFromMilliseconds(long milliseconds) {
        long days = milliseconds / MILLISECONDS_PER_DAY;
        numberOfMonth = 0;
        final long yearsFromMilliseconds = getYearsFromMilliseconds(milliseconds);
        long daysInThisYear = days;
        for (int i = 1; i < yearsFromMilliseconds; i++) {
            daysInThisYear -= (daysInYear(i));
        }
        long nowYear = getYearsFromMilliseconds(milliseconds);
        for (int i = 0; i <= 12; i++) {
            if (daysInThisYear >= daysInMonth(i + 1, nowYear)) {
                numberOfMonth++;
                daysInThisYear -= daysInMonth(i + 1, nowYear);
            } else {
                break;
            }
        }
        return numberOfMonth;
    }

    @Override
    public long getYearsFromMilliseconds(long milliseconds) {
        numberOfYears = 1;
        int count = 0;
        long days = milliseconds / MILLISECONDS_PER_DAY;
        while (true) {
            if (days >= 365) {
                switch (++count) {
                    case 1, 2, 3 -> {
                        numberOfYears++;
                        days -= 365;
                    }
                    case 4 -> {
                        numberOfYears++;
                        days -= 366;
                        count = 0;
                    }
                }
            } else {
                break;
            }
        }
        return numberOfYears;
    }

    @Override
    public long getDaysFromMilliseconds(long milliseconds) {
        long daysInThisYear = ((milliseconds / MILLISECONDS_PER_DAY) % DAYS_PER_FOUR_YEARS) % 365;
        long nowYear = getYearsFromMilliseconds(milliseconds);
        if (nowYear == 0) {
            nowYear = 1;
        }
        for (int i = 0; i < 12; i++) {
            if (daysInThisYear >= daysInMonth(i + 1, nowYear)) {
                daysInThisYear -= daysInMonth(i + 1, nowYear);
            } else {
                break;
            }
        }
        return daysInThisYear;
    }

    @Override
    public long getHoursFromMilliseconds(long milliseconds) {
        return (milliseconds % (MILLISECONDS_PER_DAY)) / MILLISECONDS_PER_HOUR;
    }

    @Override
    public long getMinutesFromMilliseconds(long milliseconds) {
        return (milliseconds % (MILLISECONDS_PER_HOUR)) / MILLISECONDS_PER_MINUTE;
    }

    @Override
    public long getSecondsFromMilliseconds(long milliseconds) {
        return (milliseconds % (MILLISECONDS_PER_MINUTE)) / MILLISECONDS_PER_SECOND;
    }

    @Override
    public long getMillisecondsFromMilliseconds(long millisecondsAll) {
        return (millisecondsAll % (MILLISECONDS_PER_SECOND));

    }

    @Override
    public long daysInMonth(long numberOfMouth, long years) {
        final long l = (1 - (years % 4 + 2) % (years % 4 + 1)) * ((years % 100 + 2) % (years % 100 + 1));
        final long l1 = 1 - (years % 400 + 2) % (years % 400 + 1);
        return (long) (28 + ((numberOfMouth + Math.floor(numberOfMouth / 8)) % 2) + 2 % numberOfMouth + Math
                        .floor((1 + l + l1) / numberOfMouth) + Math
                        .floor(1 / numberOfMouth)
                        - Math.floor(
                        (l + l1) / numberOfMouth));
    }

    public void printCalendar(Calendar calendar, String format) {
        new CalendarUtil().showResult(calendar, format);
    }
}