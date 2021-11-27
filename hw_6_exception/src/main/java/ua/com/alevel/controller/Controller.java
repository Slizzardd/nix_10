package ua.com.alevel.controller;


import ua.com.alevel.data.Calendar;
import ua.com.alevel.data.Time;
import ua.com.alevel.util.CalendarUtil;
import ua.com.alevel.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

import static ua.com.alevel.UtilityHelper.print;
import static ua.com.alevel.constant.Constant.*;

public class Controller {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String format = "dd/mm/yyyy";

    public void run() {
        print("Выберите операцию: ");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        print(
                "текущий формат (" + format + ")" +
                        "\nЕсли вы хотите изменить формат, введите 1");
        print("Если вы хотите отсортировать время от высокого к низкому, введите 2");
        print("Если вы хотите отсортировать время от низкого к высокому, введите 3");
        print("Если вы хотите найти разницу между датами, введите 4");
        print("Если вы хотите добавить время к дате, введите 5");
        print("Если вы хотите вычесть время из даты, введите 6");
        print("Если вы хотите выйти, введите 0");
        print("");
    }

    private void crud(String position) {
        switch (position) {
            case "1":
                setFormat();
                break;
            case "2":
                sortTimesFromHeightToLow();
                break;
            case "3":
                sortTimesFromLowToHeight();
            case "4":
                differenceDates();
                break;
            case "5":
                addToTimeDate();
                break;
            case "6":
                subtract();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("ВЫБЕРИТЕ!!!");
        }
        runNavigation();
    }

    private void subtract() {
        print("6");
        Calendar timeSecond = null;
        Calendar result = null;
        try {
            Time timeFirst = enterTime();
            print("Выберите что вы хотите хотите отнять от этой даты: ");
            printChoice();
            try{
                String choice = reader.readLine();
                switch (choice){
                    case "1" ->{
                        print("Введите сколько бы вы хотели отнять миллисекунд: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(Long.parseLong(toAdd), DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "2" ->{
                        print("Введите сколько бы вы хотели отнять секунд: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, Long.parseLong(toAdd),
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "3" ->{
                        print("Введите сколько бы вы хотели отнять минут: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                Long.parseLong(toAdd), DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "4" ->{
                        print("Введите сколько бы вы хотели отнять часов: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, Long.parseLong(toAdd),
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "5" ->{
                        print("Введите сколько бы вы хотели отнять дней: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                Long.parseLong(toAdd), DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "6" ->{
                        print("Введите сколько бы вы хотели отнять месяцев: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, Long.parseLong(toAdd),
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "7" ->{
                        print("Введите сколько бы вы хотели отнять лет: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                Long.parseLong(toAdd));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
            assert timeSecond != null;
            result = new CalendarUtil().differenceCalendar(timeFirst, timeSecond);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert result != null;
        new Calendar(result.getTime()).printCalendar(result, format);
    }

    private void addToTimeDate() {
        print("5");
        Calendar timeSecond = null;
        Calendar result = null;
        try {
            Time timeFirst = enterTime();
            print("Выберите что вы хотите добавить к этой дате: ");
            printChoice();
            try{
                String choice = reader.readLine();
                switch (choice){
                    case "1" ->{
                        print("Введите сколько бы вы хотели добавить миллисекунд: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(Long.parseLong(toAdd), DEFAULT_CAPACITY_FOR_TIME, 
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "2" ->{
                        print("Введите сколько бы вы хотели добавить секунд: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, Long.parseLong(toAdd),
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "3" ->{
                        print("Введите сколько бы вы хотели добавить минут: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                Long.parseLong(toAdd), DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "4" ->{
                        print("Введите сколько бы вы хотели добавить часов: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, Long.parseLong(toAdd),
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "5" ->{
                        print("Введите сколько бы вы хотели добавить дней: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                Long.parseLong(toAdd), DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "6" ->{
                        print("Введите сколько бы вы хотели добавить месяцев: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, Long.parseLong(toAdd),
                                DEFAULT_CAPACITY_FOR_TIME);
                    }
                    case "7" ->{
                        print("Введите сколько бы вы хотели добавить лет: ");
                        String toAdd = reader.readLine();
                        timeSecond = new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                                Long.parseLong(toAdd));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
            assert timeSecond != null;
            result = new CalendarUtil().addCalendar(timeFirst, timeSecond);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert result != null;
        new Calendar(result.getTime()).printCalendar(result, format);
    }

    private void sortTimesFromLowToHeight() {
        Time[] times = new Time[1];
        try {
            String choice = reader.readLine();
            boolean status = true;
            int counter = 0;
            times[counter] = enterTime();
            while (status) {
                counter++;
                times = Arrays.copyOf(times, counter + 1);
                times[counter] = enterTime();
                print("Нажмите 1 если не хотите вводить ещё 1 дату: ");
                if (reader.readLine().equals("1")) {
                    status = false;
                }
            }
            final Time[] timesSorted = new CalendarUtil().sortTimeAscCalendar(times);
            for (Time time : timesSorted) {
                new Calendar().printCalendar((Calendar) time, format);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sortTimesFromHeightToLow() {
        Time[] times = new Time[1];
        try {
            String choice = reader.readLine();
            boolean status = true;
            int counter = 0;
            times[counter] = enterTime();
            while (status) {
                counter++;
                times = Arrays.copyOf(times, counter + 1);
                times[counter] = enterTime();
                print("Нажмите 1 если не хотите вводить ещё 1 дату: ");
                if (reader.readLine().equals("1")) {
                    status = false;
                }
            }
            final Time[] timesSorted = new CalendarUtil().sortTimeDescCalendar(times);
            for (Time time : timesSorted) {
                new Calendar().printCalendar((Calendar) time, format);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void printChoice(){
        print("Миллисекунды - 1");
        print("Секунды - 2");
        print("Минуты - 3");
        print("Часы - 4");
        print("Дни - 5");
        print("Месяца - 6");
        print("Года - 7");
        
    }
    private void differenceDates() {
        Time result;
        try {
            Time timeFirst = enterTime();
            Time timeSecond = enterTime();
            result = new CalendarUtil().differenceCalendar(timeFirst, timeSecond);
            print("если вы хотите получить результат в миллисекундах, введите 1");
            print("если вы хотите получить результат в секундах, введите 2");
            print("если вы хотите получить результат в минутах, введите 3");
            print("если вы хотите получить результат в часах, введите 4");
            print("если вы хотите получить результат в днях, введите 5");
            print("если вы хотите получить результат в месяцах, введите 6");
            print("если вы хотите получить результат в годах, введите 7");
            try {
                String operation = reader.readLine();
                switch (operation) {
                    case "1" -> print(Objects.requireNonNull(result).getTime() + " миллисекунд");
                    case "2" -> print(Objects.requireNonNull(result).getTime() / MILLISECONDS_PER_SECOND + " секунд");
                    case "3" -> print(Objects.requireNonNull(result).getTime() / MILLISECONDS_PER_MINUTE + " минут");
                    case "4" -> print(Objects.requireNonNull(result).getTime() / MILLISECONDS_PER_HOUR + " часов");
                    case "5" -> print(Objects.requireNonNull(result).getTime() / MILLISECONDS_PER_DAY + " дней");
                    case "6" -> print(new Calendar().getMonthsFromMilliseconds(Objects.requireNonNull(result).getTime()) + " месяцев");
                    case "7" -> print(new Calendar().getYearsFromMilliseconds(Objects.requireNonNull(result).getTime()) + " годов");
                }
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Time enterTime() throws IOException {
        print("Введите дату по примеру: " + format);
        return Parser.getInstance().returnParsTime(reader.readLine(), format);
    }

    private void setFormat() {
        String choice = "1";
        print("""
                Выберите желаемый формат даты:
                dd/mm/yyyy \t- 1
                mm/dd/yyyy \t- 2
                month-d-yy \t- 3
                dd-mm-yyyy hh:mn- 4
                dd-mm-yyyy hh:mn:ss - 5
                dd-mm-yyyy hh:mn:ss:msc - 6
                yyyy hh:mn \t- 7
                dd-month-yyyy hh:mn - 8
                """);
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (choice) {
            case "1" -> format = "dd/mm/yyyy";
            case "2" -> format = "mm/dd/yyyy";
            case "3" -> format = "month-d-yy";
            case "4" -> format = "dd-mm-yyyy hh:mn";
            case "5" -> format = "dd-mm-yyyy hh:mn:ss";
            case "6" -> format = "dd-mm-yyyy hh:mn:ss:msc";
            case "7" -> format = "yyyy hh:mn";
            case "8" -> format = "dd-month-yyyy hh:mn";
        }
    }
}