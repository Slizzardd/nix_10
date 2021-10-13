package ua.com.alevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Symbol {
    private String enterTheString() {
        System.out.println("Введите вашу строку:");
        Scanner scanner = new Scanner(System.in);
        var line = scanner.next();
        return line;
    }

    private String convertStringToStringNotNum(String line) {
        var lineNotNum = line.replaceAll("[^A-Za-z]", "");
        return lineNotNum;
    }

    private Map countingCharacters() {
        var lineNotNum = convertStringToStringNotNum(enterTheString());
        Map<Character, Integer> chars = new HashMap<Character, Integer>();
        for (var i = 0; i < lineNotNum.length(); i++) {
            char c = lineNotNum.charAt(i);

            Integer count = chars.get(c);
            if (count == null) {
                chars.put(c, 1);
            } else {
                chars.put(c, count + 1);
            }
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
