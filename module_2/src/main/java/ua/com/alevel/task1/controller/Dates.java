package ua.com.alevel.task1.controller;

import ua.com.alevel.task1.entity.Date;
import ua.com.alevel.task1.service.DateFormat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ua.com.alevel.UtilityHelper.*;

public class Dates {
    private static final String FILE = "files/dates.txt";
    private String[] dates;

    public void run() {
        print("Task1 DATES" +
                "\nResult:");
                try {
                    String dates = Files.readString(Paths.get(FILE));
                    this.dates = dates.split("\n");
                } catch (IOException e) {
                    print(e.getMessage());
                }
                convertAndPrintDate(dates);
    }

    private void convertAndPrintDate(String[] datesArray) {
        for (String date : datesArray) {
            if (DateFormat.checkDateFormat(date.trim())) {
                Date current = DateFormat.getDate(date.trim());
                if (current != null) {
                    print("" + current.getYear() + current.getMonth() +  current.getDay());
                }
            }
        }
    }
}
