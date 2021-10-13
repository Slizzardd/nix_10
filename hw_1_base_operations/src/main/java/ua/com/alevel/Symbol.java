package ua.com.alevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Symbol {
    private String enterTheString() {
        System.out.println("Введите вашу строку:");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private String convertStringToStringNotNum(String line) {
        return line.replaceAll("[^A-Za-z]", "");
    }

    private Map countingCharacters() {
        var lineNotNum = convertStringToStringNotNum(enterTheString());
        Map<Character, Integer> chars = new HashMap<>();
        for (var i = 0; i < lineNotNum.length(); i++) {
            char c = lineNotNum.charAt(i);

            chars.merge(c, 1, Integer::sum);
        }
        return chars;
    }

    public void outputConsole() {
        System.out.println("Программа которая подсчитывает сколько раз вы ввели тот или иной символ");
        for (var entry : countingCharacters().entrySet()) {

            System.out.println(entry);

        }
    }

}
