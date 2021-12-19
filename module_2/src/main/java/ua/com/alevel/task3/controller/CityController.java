package ua.com.alevel.task3.controller;

import ua.com.alevel.task3.service.CityService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ua.com.alevel.UtilityHelper.print;

public class CityController {
    private static final String FILE_INPUT = "src/main/java/ua/com/alevel/files/input_city.txt";

    public void run() {
        print("Task3");
        String someTask = "";
        try {
            someTask = Files.readString(Paths.get(FILE_INPUT));
        } catch (IOException e) {
            print(e.getMessage());
        }
        String[] arrayRoadsCities = someTask.split("\n");
        String result = CityService.findTheCheapestRoad(arrayRoadsCities);
        print("Result:\n" + result);
    }
}