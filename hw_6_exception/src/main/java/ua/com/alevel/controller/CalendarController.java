package ua.com.alevel.controller;

import ua.com.alevel.dataclass.Calendar;
import ua.com.alevel.dataclass.CustomDateTime;
import ua.com.alevel.service.DateTimeService;
import ua.com.alevel.service.FormatService;
import ua.com.alevel.util.DateTimeCalculator;
import ua.com.alevel.util.DateTimeConverter;

import java.util.Collections;

import static ua.com.alevel.UtilityHelper.*;

public class CalendarController {
    private static final FormatService FORMAT_SERVICE = new FormatService();
    private static final DateTimeService DATETIME_SERVICE = new DateTimeService();
    private static final DateTimeCalculator CALCULATOR = new DateTimeCalculator();
    private static final DateTimeConverter CONVERTER = new DateTimeConverter();

    public void run() {
        int choice = 11;
        while (choice != 0) {
            print("Chose function to proceed:\n" +
                    "1 - find difference between dates\n" +
                    "2 - add time or period to date\n" +
                    "3 - subtract time or period from date\n" +
                    "4 - compare list of dates in desc or asc order\n" +
                    "0 - EXIT\n");
        }
        choice = getInt();

        switch (choice) {
            case 1:
                difference();
                break;
            case 2:
                addition();
                break;
            case 3:
                subtraction();
                break;
            case 4:
                comparation();
                break;
            default:
                break;
        }
        print("________________________________________________________________________________________________________________________________________");
    }

    private void difference() {
        print("In which format you`d like to find difference:\n" +
                "1 - in hours\n" +
                "2 - in days\n" +
                "3 - in years\n" +
                "4 - in minutes\n" +
                "5 - in seconds\n" +
                "6 - in milliseconds");
        int choice = getInt();
        switch (choice) {
            case 1 -> System.out.println(CALCULATOR.calculateDifference(
                    createDateTimeInSelectedFormatOfInputDates(), createDateTimeInSelectedFormatOfInputDates()) * 24
                    + " hours of difference");
            case 2 -> System.out.println(CALCULATOR.calculateDifference(
                    createDateTimeInSelectedFormatOfInputDates(), createDateTimeInSelectedFormatOfInputDates())
                    + " days of difference");
            case 3 -> System.out.println((double) CALCULATOR.calculateDifference(
                    createDateTimeInSelectedFormatOfInputDates(), createDateTimeInSelectedFormatOfInputDates()) / 365.0
                    + " years of difference");
            case 4 -> System.out.println(CALCULATOR.calculateDifference
                    (createDateTimeInSelectedFormatOfInputDates(), createDateTimeInSelectedFormatOfInputDates()) * 24 * 60
                    + " minutes of difference");
            case 5 -> System.out.println(CALCULATOR.calculateDifference(
                    createDateTimeInSelectedFormatOfInputDates(), createDateTimeInSelectedFormatOfInputDates()) * 24 * 60 * 60
                    + " seconds of difference");
            case 6 -> System.out.println((long) CALCULATOR.calculateDifference(
                    createDateTimeInSelectedFormatOfInputDates(), createDateTimeInSelectedFormatOfInputDates()) * 24 * 60 * 60 * 1000 + " milliseconds of difference");
            default -> {
                break;
            }
        }
    }

    private void addition() {
        print("What you`d like to add to date:\n" +
                "1 - hours\n" +
                "2 - days\n" +
                "3 - years\n" +
                "4 - minutes\n" +
                "5 - seconds\n" +
                "6 - milliseconds");
        int choice = getInt();
        switch (choice) {
            case 1 -> {
                print("Enter hours to add: ");
                int hoursToAdd = getInt();
                CONVERTER.showResultsInSelectedFormatOfOutputDates(Collections.singletonList(CALCULATOR.addHours(createDateTimeInSelectedFormatOfInputDates(), hoursToAdd)));
            }
            case 2 -> {
                print("Enter days to add: ");
                int daysToAdd = getInt();
                CONVERTER.showResultsInSelectedFormatOfOutputDates(Collections.singletonList(CALCULATOR.addDays(createDateTimeInSelectedFormatOfInputDates(), daysToAdd)));
            }
            case 3 -> {
                CustomDateTime customDateTime = createDateTimeInSelectedFormatOfInputDates();
                if (customDateTime != null) {
                    print("Enter years to add: ");
                    int yearsToAdd = getInt();
                    CONVERTER
                            .showResultsInSelectedFormatOfOutputDates(Collections
                                    .singletonList(DATETIME_SERVICE
                                            .createDateTime(new CustomDateTime(new Calendar(customDateTime
                                                    .getCalendar().getDay(), customDateTime.getCalendar().getMonth(), customDateTime.getCalendar().getYear() + yearsToAdd), customDateTime.getClock()))));
                }
            }
            case 4 -> {
                print("Enter minutes to add: ");
                int minutesToAdd = getInt();
                CONVERTER.showResultsInSelectedFormatOfOutputDates(Collections.singletonList(CALCULATOR.addMinutes(createDateTimeInSelectedFormatOfInputDates(), minutesToAdd)));
            }
            case 5 -> {
                print("Enter seconds to add: ");
                int secondsToAdd = getInt();
                CONVERTER.showResultsInSelectedFormatOfOutputDates(Collections.singletonList(CALCULATOR.addSeconds(createDateTimeInSelectedFormatOfInputDates(), secondsToAdd)));
            }
            case 6 -> {
                print("Enter milliseconds to add: ");
                int millisecondsToAdd = getInt();
                CONVERTER.showResultsInSelectedFormatOfOutputDates(Collections.singletonList(CALCULATOR.addMillis(createDateTimeInSelectedFormatOfInputDates(), millisecondsToAdd)));
            }
        }
    }

    public CustomDateTime createDateTimeInSelectedFormatOfInputDates() {
        int choice = 11;
        while (choice != 0) {
            print("Chose format for entering date:\n" +
                    "dd/mm/yyyy \t- 1\n" +
                    "mm/dd/yyyy \t- 2\n" +
                    "month-d-yy \t- 3\n" +
                    "dd-mm-yyyy hh:mn- 4\n" +
                    "dd-mm-yyyy hh:mn:ss - 5\n" +
                    "dd-mm-yyyy hh:mn:ss:msc - 6\n" +
                    "yyyy hh:mn \t- 7\n" +
                    "dd-month-yyyy hh:mn - 8\n");
            choice = getInt();
            print("Enter date: ");
            String date = getString();
            CustomDateTime newDate;
            switch (choice) {
                case 1 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "ddmmyyyy"));
                    if (newDate != null) return newDate;
                    else break;
                }
                case 2 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "mmddyyyy"));
                    if (newDate != null) return newDate;
                    else break;
                }
                case 3 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "monthdyy"));
                    if (newDate != null) return newDate;
                    else break;
                }

                case 4 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "ddmmyyyyhhmn"));
                    if (newDate != null) return newDate;
                    else break;
                }
                case 5 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "ddmmyyyyhhmnss"));
                    if (newDate != null) return newDate;
                    else break;
                }
                case 6 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "ddmmyyyyhhmnssmsc"));
                    if (newDate != null) return newDate;
                    else break;
                }
                case 7 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "yearhhmn"));
                    if (newDate != null) return newDate;
                    else break;
                }
                case 8 -> {
                    newDate = DATETIME_SERVICE.createDateTime(FORMAT_SERVICE.createDateTime(date, "ddmonthyyyyhhmn"));
                    if (newDate != null) return newDate;
                    else break;
                }
                default -> {
                    break;
                }
            }

            print("Date was not created, try again.");

        }
        return null;
    }


}