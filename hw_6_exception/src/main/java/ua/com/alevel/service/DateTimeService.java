package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dataclass.Calendar;
import ua.com.alevel.dataclass.CustomDateTime;


public class DateTimeService {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private static final Calendar calendar = new Calendar();

    public CustomDateTime createDateTime(CustomDateTime customDateTime) {
        if (customDateTime != null) {
            if (checkDateTime(customDateTime)) return customDateTime;
        }
        return null;
    }

    public boolean checkDateTime(CustomDateTime date) {
        if (!checkYear(date.getCalendar().getYear())) {
            LOGGER_ERROR.error("incorrect year");
            return false;
        }
        if (!checkMonth(date.getCalendar().getMonth())) {
            LOGGER_ERROR.error("incorrect month");
            return false;
        }
        if (!checkDay(date)) {
            LOGGER_ERROR.error("incorrect day");
            return false;
        }
        if (!checkHours(date.getClock().getHours())) {
            LOGGER_ERROR.error("incorrect hour");
            return false;
        }
        if (!checkMinutes(date.getClock().getMinutes())) {
            LOGGER_ERROR.error("incorrect minutes");
            return false;
        }
        if (!checkSeconds(date.getClock().getSeconds())) {
            LOGGER_ERROR.error("incorrect seconds");
            return false;
        }
        if (!checkMilliseconds(date.getClock().getMilliseconds())) {
            LOGGER_ERROR.error("incorrect milliseconds");
            return false;
        }

        return true;
    }

    private boolean checkMilliseconds(int milliseconds) {
        return (milliseconds >= 0 && milliseconds < 1000);
    }

    private boolean checkSeconds(int seconds) {
        return (seconds >= 0 && seconds < 60);
    }

    private boolean checkMinutes(int minutes) {
        return (minutes >= 0 && minutes < 60);
    }

    private boolean checkHours(int hours) {
        return (hours >= 0 && hours < 24);
    }

    private boolean checkYear(int year) {
        return (year > 0);
    }

    private boolean checkMonth(int month) {
        return (month >= 1 && month <= 12);
    }

    private boolean checkDay(CustomDateTime date) {

        if (date.getCalendar().getDay() > 31 || date.getCalendar().getDay() < 1) return false;
        if (Calendar.getLongMonth().contains(date.getCalendar().getMonth())) return true;
        else {
            if (date.getCalendar().getMonth() != 2 && date.getCalendar().getDay() < 31 && date.getCalendar().getDay() > 0)
                return true;
            else if (date.getCalendar().getMonth() == 2 && date.getCalendar().getDay() < 29) return true;
            else
                return date.getCalendar().getMonth() == 2 && date.getCalendar().getYear() % 4 == 0 && date.getCalendar().getDay() < 30;
        }
    }
}
