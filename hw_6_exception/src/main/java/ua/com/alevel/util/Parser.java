package ua.com.alevel.util;


import ua.com.alevel.data.Calendar;
import ua.com.alevel.data.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static ua.com.alevel.UtilityHelper.print;
import static ua.com.alevel.constant.Constant.DEFAULT_CAPACITY_FOR_CALENDAR;
import static ua.com.alevel.constant.Constant.DEFAULT_CAPACITY_FOR_TIME;

public class Parser {

    public static Parser getInstance(){
        return instance;
    }
    private Time time;
    private static final Parser instance = new Parser();
    public Time returnParsTime(String date, String format){
        switch (format){
            case "dd/mm/yyyy" ->{
                return ddmmyyyy(date);
            }
            case "mm/dd/yyyy" -> {
                return mmddyyyy(date);
            }
            case "month-d-yy" ->{
                return monthdyy(date);
            }
            case "dd-mm-yyyy hh:mn" ->{
                return ddmmyyyyhhmn(date);
            }
            case "dd-mm-yyyy hh:mn:ss"->{
                return ddmmyyyyhhmnss(date);
            }
            case "dd-mm-yyyy hh:mn:ss:msc"->{
                return ddmmyyyyhhmnssmsc(date);
            }
            case "yyyy hh:mn"->{
                return yyyyhhmn(date);
            }
            case "dd-month-yyyy hh:mn"->{
                return ddmonthyyyyhhmn(date);
            }
        }
        return null;
    }

    private Time ddmonthyyyyhhmn(String date) {
        ArrayList<String> dateStrings = new ArrayList<>(7);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int day = Integer.parseInt(dateStrings.get(0));
            int month = getMonthByName(dateStrings.get(1));
            int year = Integer.parseInt(dateStrings.get(2));
            int hour = Integer.parseInt(dateStrings.get(3));
            int minute = Integer.parseInt(dateStrings.get(4));
            return new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME, minute,
                    hour, day, month, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Time yyyyhhmn(String date) {
        ArrayList<String> dateStrings = new ArrayList<>(3);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int year = Integer.parseInt(dateStrings.get(0));
            int hour = Integer.parseInt(dateStrings.get(1));
            int minute = Integer.parseInt(dateStrings.get(2));
            return new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME, minute,
                    hour, DEFAULT_CAPACITY_FOR_CALENDAR, DEFAULT_CAPACITY_FOR_CALENDAR, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Time ddmmyyyyhhmnssmsc(String date) {
        ArrayList<String> dateStrings = new ArrayList<>(7);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int day = Integer.parseInt(dateStrings.get(0));
            int month = Integer.parseInt(dateStrings.get(1));
            int year = Integer.parseInt(dateStrings.get(2));
            int hour = Integer.parseInt(dateStrings.get(3));
            int minute = Integer.parseInt(dateStrings.get(4));
            int second = Integer.parseInt(dateStrings.get(5));
            int millisecond = Integer.parseInt(dateStrings.get(6));
            return new Calendar(millisecond, second, minute,
                    hour, day, month, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Time ddmmyyyyhhmnss(String date) {
        ArrayList<String> dateStrings = new ArrayList<>(6);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int day = Integer.parseInt(dateStrings.get(0));
            int month = Integer.parseInt(dateStrings.get(1));
            int year = Integer.parseInt(dateStrings.get(2));
            int hour = Integer.parseInt(dateStrings.get(3));
            int minute = Integer.parseInt(dateStrings.get(4));
            int second = Integer.parseInt(dateStrings.get(5));
            return new Calendar(DEFAULT_CAPACITY_FOR_TIME, second, minute,
                    hour, day, month, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Time ddmmyyyyhhmn(String date) {
        ArrayList<String> dateStrings = new ArrayList<>(5);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int day = Integer.parseInt(dateStrings.get(0));
            int month = Integer.parseInt(dateStrings.get(1));
            int year = Integer.parseInt(dateStrings.get(2));
            int hour = Integer.parseInt(dateStrings.get(3));
            int minute = Integer.parseInt(dateStrings.get(4));
            return new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME, minute,
                    hour, day, month, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Time ddmmyyyy(String date){
        ArrayList<String> dateStrings = new ArrayList<>(3);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int day = Integer.parseInt(dateStrings.get(0));
            int month = Integer.parseInt(dateStrings.get(1));
            int year = Integer.parseInt(dateStrings.get(2));
            return new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                    DEFAULT_CAPACITY_FOR_TIME, day, month, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Time mmddyyyy(String date){
        ArrayList<String> dateStrings = new ArrayList<>(3);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int month = Integer.parseInt(dateStrings.get(0));
            int day = Integer.parseInt(dateStrings.get(1));
            int year = Integer.parseInt(dateStrings.get(2));
            return new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                    DEFAULT_CAPACITY_FOR_TIME, day, month, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Time monthdyy(String date){
        ArrayList<String> dateStrings = new ArrayList<>(3);
        Collections.addAll(dateStrings, date.split("[./ :-]+"));
        try{
            int month = getMonthByName(dateStrings.get(0));
            int day = Integer.parseInt(dateStrings.get(1));
            int year = Integer.parseInt(dateStrings.get(2));
            return new Calendar(DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME, DEFAULT_CAPACITY_FOR_TIME,
                    DEFAULT_CAPACITY_FOR_TIME, day, month, year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getMonthByName(String number){

        switch (number.toLowerCase(Locale.ROOT)){
            case "январь" -> {
                return 1;
            }
            case "февраль" -> {
                return 2;
            }
            case "март" -> {
                return 3;
            }
            case "апрель" -> {
                return 4;
            }
            case "май" -> {
                return 5;
            }
            case "июнь" -> {
                return 6;
            }
            case "июль" -> {
                return 7;
            }
            case "август" -> {
                return 8;
            }
            case "сентябрь" -> {
                return 9;
            }
            case "октябрь" -> {
                return 10;
            }
            case "ноябрь" -> {
                return 11;
            }
            case "декабрь" -> {
                return 12;
            }
            default -> {
                print("Введите правильно месяц в именительном падеже. Пример(Февраль)");
                return 0;
            }
        }
    }
}