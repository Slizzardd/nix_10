package ua.com.alevel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dataclass.Calendar;
import ua.com.alevel.dataclass.CustomDateTime;

import java.util.List;
import java.util.Scanner;


import static ua.com.alevel.constant.Const.*;

public class DateTimeConverter {
    private static final Scanner in = new Scanner(System.in);
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public void showResultsInSelectedFormatOfOutputDates(List<CustomDateTime> dates) {
        try{
            System.out.println("Select in which format you`d like to get results: ");
            System.out.println("Chose format for entering date:\n" +
                    "dd/mm/yyyy \t- 1\n" +
                    "mm/dd/yyyy \t- 2\n" +
                    "month-d-yy \t- 3\n" +
                    "dd-mm-yyyy hh:mn- 4\n" +
                    "dd-mm-yyyy hh:mn:ss - 5\n" +
                    "dd-mm-yyyy hh:mn:ss:msc - 6\n" +
                    "yyyy hh:mn \t- 7\n" +
                    "dd-month-yyyy hh:mn - 8\n");
            int choice;
            if (in.hasNextInt()) choice = in.nextInt();
            else choice = 11;

            switch (choice) {
                case 1: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getDay() + "/" + d.getCalendar().getMonth() + "/" + d.getCalendar().getYear());

                }
                break;
                case 2: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getMonth() + "/" + d.getCalendar().getDay() + "/" + d.getCalendar().getYear());

                }
                break;
                case 3: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getMonthToName(d.getCalendar().getMonth()) + "-" + d.getCalendar().getDay() + "-" + d.getCalendar().getYear());
                }
                break;
                case 4: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getDay() + "-" + d.getCalendar().getMonth() + "-" + d.getCalendar().getYear() +
                                " " + d.getClock().getHours() + ":" + d.getClock().getMinutes());
                }
                break;
                case 5: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getDay() + "-" + d.getCalendar().getMonth() + "-" + d.getCalendar().getYear() +
                                " " + d.getClock().getHours() + ":" + d.getClock().getMinutes() + ":" + d.getClock().getSeconds());
                }
                break;
                case 6: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getDay() + "-" + d.getCalendar().getMonth() + "-" + d.getCalendar().getYear() +
                                " " + d.getClock().getHours() + ":" + d.getClock().getMinutes() + ":" + d.getClock().getSeconds() + ":" + d.getClock().getMilliseconds());
                }
                break;
                case 7: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getYear() + " " + d.getClock().getHours() + ":" + d.getClock().getMinutes());
                }
                break;
                case 8: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getDay() + "-" + d.getCalendar().getMonthToName(d.getCalendar().getMonth()) + "-"
                                + "-" + d.getCalendar().getYear() + " " + d.getClock().getHours() + ":" + d.getClock().getMinutes());
                }
                break;
                default: {
                    for (CustomDateTime d : dates)
                        System.out.println(d.getCalendar().getDay() + "/" + d.getCalendar().getMonth() + "/" + d.getCalendar().getYear() + " "
                                + d.getClock().getHours() + ":" + d.getClock().getMinutes() + ":" + d.getClock().getSeconds() + ":" + d.getClock().getMilliseconds());
                }
                break;
            }
        }catch(NullPointerException e){
            LOGGER_ERROR.error("empty dates");
        }
    }


    public int monthToDays(int year, int month) {
        int days = 0;
        for (int i = 1; i < month; i++) {
            if (i == FEBRUARY && year % 4 == 0) days += SHORT_MONTH;
            else if (i == FEBRUARY && year % 4 != 0) days += TINY_MONTH;
            else {
                if (Calendar.getLongMonth().contains(i)) days += LONG_MONTH;
                else days += MONTH;
            }
        }
        return days;
    }
}