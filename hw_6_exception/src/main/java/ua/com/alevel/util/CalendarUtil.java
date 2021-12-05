package ua.com.alevel.util;

import ua.com.alevel.data.Calendar;
import ua.com.alevel.data.Time;

import static ua.com.alevel.UtilityHelper.print;

public class CalendarUtil {

    public Calendar differenceCalendar(Time start, Time end) {
        return new Calendar(start.getTime() - end.getTime());
    }

    public Calendar addCalendar(Time start, Time end) {
        try {
            return new Calendar(start.getTime() + end.getTime());
        }catch (NullPointerException e){
            e.getMessage();
        }
        return null;
    }

    public Time[] sortTimeAscCalendar(Time[] times) {
        Time temp;
        for (int i = 0; i < times.length - 1; i++) {
            for (int j = 0; j < times.length - i - 1; j++) {
                if (times[j].getTime() > times[j + 1].getTime()) {
                    temp = times[j];
                    times[j] = times[j + 1];
                    times[j + 1] = temp;
                }
            }
        }
        return times;
    }

    public Time[] sortTimeDescCalendar(Time[] times) {
        Time temp;
        for (int i = 0; i < times.length - 1; i++) {
            for (int j = 0; j < times.length - i - 1; j++) {
                if (times[j].getTime() < times[j + 1].getTime()) {
                    temp = times[j];
                    times[j] = times[j + 1];
                    times[j + 1] = temp;
                }
            }
        }
        return times;
    }

    private String getMonthByNumber(int number) {

        switch (number) {
            case 1 -> {
                return "явнарь";
            }
            case 2 -> {
                return "февраль";
            }
            case 3 -> {
                return "март";
            }
            case 4 -> {
                return "апрель";
            }
            case 5 -> {
                return "май";
            }
            case 6 -> {
                return "июнь";
            }
            case 7 -> {
                return "июль";
            }
            case 8 -> {
                return "август";
            }
            case 9 -> {
                return "сентябрь";
            }
            case 10 -> {
                return "октябрь";
            }
            case 11 -> {
                return "ноябрь";
            }
            case 12 -> {
                return "декабрь";
            }
        }
        return null;
    }

    public void showResult(Calendar d, String format) {
        switch (format) {
            case "dd/mm/yyyy" -> print(d.getDays() + "/" + d.getMonths() + "/"
                    + d.getYears());
            case "mm/dd/yyyy" -> print(d.getMonths() + "/" + d.getDays() + "/"
                    + d.getYears());
            case "month-d-yy" -> print(getMonthByNumber((int) d.getMonths()) + "-" + d.getDays() + "-"
                    + d.getYears());
            case "dd-mm-yyyy hh:mn" -> print(d.getDays() + "-" + d.getMonths() + "-"
                    + d.getYears() + " " + d.getHours() + ":"
                    + d.getMinutes());
            case "dd-mm-yyyy hh:mn:ss" -> print(d.getDays() + "-" + d.getMonths() + "-"
                    + d.getYears() + " " + d.getHours() + ":"
                    + d.getMinutes() + ":" + d.getSeconds());
            case "dd-mm-yyyy hh:mn:ss:msc" -> print(d.getDays() + "-" + d.getMonths() + "-"
                    + d.getYears() + " " + d.getHours() + ":"
                    + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getMilliseconds());
            case "yyyy hh:mn" -> print(d.getYears() + " " + d.getHours() + ":" + d.getMinutes());
            case "dd-month-yyyy hh:mn" -> print(d.getDays() + "-" + getMonthByNumber((int) d.getMonths()) + "-"
                    + d.getYears() + " " + d.getHours() + ":"
                    + d.getMinutes());
        }
    }
}
