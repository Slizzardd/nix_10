package ua.com.alevel.task1.service;

import ua.com.alevel.task1.entity.Date;

import static ua.com.alevel.task1.const_date.Const.*;

//Храню все в String чтоб не было такой фигни как на скрине date_int.png
public class DateFormat {
    private static String year;
    private static String month;
    private static String day;

    public static boolean checkDateFormat(String date) {
        return date.matches("^[0-9]{1,4}/[0-9]{2}/[0-9]{2}$") ||
                date.matches("^[0-9]{2}/[0-9]{2}/[0-9]{1,4}$") ||
                date.matches("^[0-9]{2}-[0-9]{2}-[0-9]{1,4}$");
    }

    public static Date getDate(String line) {
        Date date = new Date();
        if (line.matches("^[0-9]{1,4}/[0-9]{2}/[0-9]{2}$")) {
            String[] temporary = line.trim().split("/");
            year = temporary[0];
            month = temporary[1];
            day = temporary[2];
        } else if (line.matches("^[0-9]{2}/[0-9]{2}/[0-9]{1,4}$")) {
            String[] temporary = line.trim().split("/");
            day = temporary[0];
            month = temporary[1];
            year = temporary[2];
        } else if (line.matches("^[0-9]{2}-[0-9]{2}-[0-9]{1,4}$")) {
            String[] temporary = line.trim().split("-");
            month = temporary[1];
            day = temporary[0];
            year = temporary[2];
        }
        if (checkYear(Integer.parseInt(year))) {
            date.setYear(year);
        } else return null;
        if (checkMonth(Integer.parseInt(month))) {
            date.setMonth(month);
        } else return null;
        if (checkDay(Integer.parseInt(month))) {
            date.setDay(day);
        } else return null;

        return date;
    }

    private static boolean checkDay(int day) {
        int currentMonth = Integer.parseInt(month);
        if (day < 1) return false;
        if (currentMonth == NUMBER_FEBRUARY) return daysInFebruary();
        else if (currentMonth == NUMBER_APRIL || currentMonth == NUMBER_JUNE
                || currentMonth == NUMBER_SEPTEMBER
                || currentMonth == NUMBER_NOVEMBER) return day <= DAYS_IN_LOW_MONTH;
        else return day <= DAYS_IN_HEIGHT_MONTH;
    }

    private static boolean checkMonth(int month) {
        return month > 0 && month <= 12;
    }

    private static boolean checkYear(int year) {
        return year >= 0;
    }

    private static boolean isLeap(int year) {
        return (year % 100 == 0 && year % 400 == 0) || year % 4 == 0;
    }

    private static boolean daysInFebruary() {
        if (isLeap(Integer.parseInt(year))) return Integer.parseInt(day) <= DAYS_IN_HEIGHT_FEBRUARY;
        else return Integer.parseInt(day) <= DAYS_IN_LOW_FEBRUARY;
    }
}
