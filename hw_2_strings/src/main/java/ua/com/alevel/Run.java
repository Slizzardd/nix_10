package ua.com.alevel;

import java.util.Scanner;

public class Run {
    private static final Scanner scanner = new Scanner(System.in);

    public void exercise1() {
        String line;
        while (true) {
            System.out.println("Введите вашу строку: ");
            line = scanner.nextLine();
            System.out.println("Результат: " + StringUtility.reverseString(line));
            break;
        }
    }

    public void exercise2() {
        String line, substring;
        while (true) {
            System.out.println("Введите вашу строку: ");
            line = scanner.nextLine();
            System.out.println("Введите вашу подстроку: ");
            substring = scanner.nextLine();
            System.out.println("Результат: " + StringUtility.reverseString(line, substring));
            break;
        }
    }

    public void exercise3() {
        String line;
        int firstIndex = 0, lastIndex = 0;
        while (true) {
            System.out.println("Введите вашу строку: ");
            line = scanner.nextLine();
            try {
                System.out.println("Введите индекс, с которого начать реверс: ");
                var firstIndexOnString = scanner.nextLine();
                System.out.println("Введите индекс на котором закончить реверс: ");
                var lastIndexOnString = scanner.nextLine();
                firstIndex = Integer.parseInt(firstIndexOnString);
                lastIndex = Integer.parseInt(lastIndexOnString);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не правильный индекс:( ");
            }
            System.out.println("Результат: " + StringUtility.reverseString(line, firstIndex, lastIndex));
            break;
        }
    }
}
