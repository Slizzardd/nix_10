package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dataclass.Calendar;
import ua.com.alevel.dataclass.Clock;
import ua.com.alevel.dataclass.CustomDateTime;

import java.util.ArrayList;
import java.util.Collections;

public class FormatService {
    private final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private CustomDateTime customDateTime;

    public CustomDateTime createDateTime(String date, String format) {
        customDateTime = new CustomDateTime();
        if (customDateTime(date, format)) {
            return customDateTime;
        }
        LOGGER_ERROR.error("failed to create date and time");
        return null;
    }

    private boolean customDateTime(String date, String format) {
        switch (format) {
            case "ddmmyyyy":
                if (ddmmyyyy(date)) return true;
                break;
            case "mmddyyyy":
                if (mmddyyyy(date)) return true;
                break;
            case "yearhhmn":
                if (yyyyhhmn(date)) return true;
                break;
            case "ddmmyyyyhhmnssmsc":
                if (ddmmyyyyhhmnssmsc(date)) return true;
                break;
            case "ddmmyyyyhhmnss":
                if (ddmmyyyyhhmnss(date)) return true;
                break;
            case "ddmmyyyyhhmn":
                if (ddmmyyyyhhmn(date)) return true;
                break;
            case "monthdyy":
                if (monthdyy(date)) return true;
                break;
            case "ddmonthyyyyhhmn":
                if (ddmonthyyyyhhmn(date)) return true;
                break;
            default:
                break;
        }
        LOGGER_ERROR.error("Incorrect format");
        return false;
    }

    private boolean ddmmyyyy(String date) {
        ArrayList<String> strings = new ArrayList<>(3);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int month = Integer.parseInt(strings.get(1));
            int day = Integer.parseInt(strings.get(0));
            int year = Integer.parseInt(strings.get(2));
            customDateTime.setCalendar(new Calendar(day, month, year));
            customDateTime.setClock(new Clock());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean mmddyyyy(String date) {
        ArrayList<String> strings = new ArrayList<>(3);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int month = Integer.parseInt(strings.get(0));
            int day = Integer.parseInt(strings.get(1));
            int year = Integer.parseInt(strings.get(2));
            customDateTime.setCalendar(new Calendar(day, month, year));
            customDateTime.setClock(new Clock());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean yyyyhhmn(String date) {
        ArrayList<String> strings = new ArrayList<>(3);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int hour = Integer.parseInt(strings.get(1));
            int minutes = Integer.parseInt(strings.get(2));
            int year = Integer.parseInt(strings.get(0));
            customDateTime.setCalendar(new Calendar(year));
            customDateTime.setClock(new Clock(hour, minutes));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean ddmmyyyyhhmnssmsc(String date) {
        ArrayList<String> strings = new ArrayList<>(3);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int day = Integer.parseInt(strings.get(0));
            int month = Integer.parseInt(strings.get(1));
            int year = Integer.parseInt(strings.get(2));
            int hour = Integer.parseInt(strings.get(3));
            int minutes = Integer.parseInt(strings.get(4));
            int seconds = Integer.parseInt(strings.get(5));
            int millisecond = Integer.parseInt(strings.get(6));

            customDateTime.setCalendar(new Calendar(day, month, year));
            customDateTime.setClock(new Clock(hour, minutes, seconds, millisecond));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean ddmmyyyyhhmnss(String date) {
        ArrayList<String> strings = new ArrayList<>(3);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int day = Integer.parseInt(strings.get(0));
            int month = Integer.parseInt(strings.get(1));
            int year = Integer.parseInt(strings.get(2));
            int hour = Integer.parseInt(strings.get(3));
            int minutes = Integer.parseInt(strings.get(4));
            int seconds = Integer.parseInt(strings.get(5));

            customDateTime.setCalendar(new Calendar(day, month, year));
            customDateTime.setClock(new Clock(hour, minutes, seconds));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean ddmmyyyyhhmn(String date) {
        ArrayList<String> strings = new ArrayList<>(3);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int day = Integer.parseInt(strings.get(0));
            int month = Integer.parseInt(strings.get(1));
            int year = Integer.parseInt(strings.get(2));
            int hour = Integer.parseInt(strings.get(3));
            int minutes = Integer.parseInt(strings.get(4));

            customDateTime.setCalendar(new Calendar(day, month, year));
            customDateTime.setClock(new Clock(hour, minutes));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean monthdyy(String date) {
        ArrayList<String> strings = new ArrayList<>(7);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int month = Calendar.getMonthByName(strings.get(0));
            int day = Integer.parseInt(strings.get(1));
            int year = Integer.parseInt(strings.get(2));

            customDateTime.setCalendar(new Calendar(day, month, year));
            customDateTime.setClock(new Clock());

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean ddmonthyyyyhhmn(String date) {
        ArrayList<String> strings = new ArrayList<>(3);
        Collections.addAll(strings, date.split("[./ :-]+"));
        try {
            int month = Calendar.getMonthByName(strings.get(1));
            int day = Integer.parseInt(strings.get(0));
            int year = Integer.parseInt(strings.get(2));
            int hours = Integer.parseInt(strings.get(3));
            int minutes = Integer.parseInt(strings.get(4));
            customDateTime.setCalendar(new Calendar(day, month, year));
            customDateTime.setClock(new Clock(hours, minutes));
        } catch (NumberFormatException | IndexOutOfBoundsException FormatException) {
            return false;
        }
        return true;
    }
}
