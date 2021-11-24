package ua.com.alevel.dataclass;

import lombok.Data;

import java.util.ArrayList;
import java.util.Locale;

import static ua.com.alevel.constant.Const.*;

@Data
public class Calendar implements Comparable<Calendar> {
    private int year;
    private int month;
    private int day;

    public Calendar(int year){
        this.day = STARTING_DAY;
        this.month = STARTING_MONTH;
        this.year = year;
    }

    public Calendar(){
        this.day = STARTING_DAY;
        this.month = STARTING_MONTH;
        this.year = STARTING_YEAR;
    }

    public Calendar(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static ArrayList<Integer> getLongMonth(){
        ArrayList<Integer> longMonth = new ArrayList<>();
        longMonth.add(1);
        longMonth.add(3);
        longMonth.add(5);
        longMonth.add(7);
        longMonth.add(8);
        longMonth.add(10);
        longMonth.add(12);
        return longMonth;
    }

    public static int getMonthByName(String name) {
        switch (name.toLowerCase(Locale.ROOT)) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return 1;
        }
    }

    public String getMonthToName(int month) {
        return switch (month) {
            case 1 -> "january";
            case 2 -> "february";
            case 3 -> "march";
            case 4 -> "april";
            case 5 -> "may";
            case 6 -> "june";
            case 7 -> "july";
            case 8 -> "august";
            case 9 -> "september";
            case 10 -> "october";
            case 11 -> "november";
            case 12 -> "december";
            default -> "none";
        };
    }

    @Override
    public String toString() {
        return " " + day + "/" + month + "/" + year;
    }

    @Override
    public int compareTo(Calendar calendar) {

        if (this.getDay() == calendar.getDay() && this.getMonth() == calendar.getMonth() && calendar.getYear() == this.getYear())
            return 0;
        else {
            if (this.year > calendar.getYear()) return 1;
            else {
                if (this.year == calendar.getYear() && this.month > calendar.getMonth()) return 1;
                else {
                    if (this.year == calendar.getYear() && this.month == calendar.getMonth() && this.day > calendar.getDay())
                        return 1;
                    else return -1;
                }
            }
        }
    }
}
