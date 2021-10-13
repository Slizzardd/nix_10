package ua.com.alevel;

import java.util.Scanner;

//Программа удаляет все символы, знаки и буквы из строки и оставляет только цифры
public class Number {
    private String enterTheString() {
        System.out.println("Введите вашу строку:");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private char[] convertStringToNum(String line) {
        var numbersOnly = line.replaceAll("\\D", "");
        return numbersOnly.toCharArray();
    }

    private int turningIntoNumbers() {
        var numbersOnly = convertStringToNum(enterTheString());
        var setOfNumber = 0;
        for (int c : numbersOnly) {
            setOfNumber += Character.getNumericValue(c);
        }
        return setOfNumber;
    }

    public void outputConsole() {
        System.out.println("Программа которая вычисляет сумму всех ваших чисел из введенной вами строки");
        System.out.println("Сумма всех ваших чисел = " + turningIntoNumbers());
    }
}
