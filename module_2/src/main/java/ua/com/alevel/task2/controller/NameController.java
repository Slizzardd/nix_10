package ua.com.alevel.task2.controller;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.com.alevel.UtilityHelper.print;


public class NameController {

    @SneakyThrows
    public void run() {
        String FILE = "src/main/java/ua/com/alevel/files/input_name.txt";
        String names = Files.readString(Paths.get(FILE));
        getAndPrintUniqueName(names.split("\n"));
    }

    private void getAndPrintUniqueName(String[] Names) {
        Map<String, Long> mapNames = Arrays.stream(Names)
                .collect(Collectors.groupingBy(name -> name, LinkedHashMap::new, Collectors.counting()));
        if (mapNames.entrySet().stream().anyMatch(name -> name.getValue() == 1))
            print("You first unique name: " +
                    mapNames.entrySet().stream()
                    .filter(name -> name.getValue() == 1)
                    .map(Map.Entry::getKey).findFirst().get());
        else print("There are no unique names in the file");
    }
}