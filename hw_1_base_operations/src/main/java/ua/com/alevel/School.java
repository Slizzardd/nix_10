package ua.com.alevel;

import java.util.Scanner;

public class School {
    public static final int START_OF__LESSON = 9;
    public static final int ONE_HORSE = 60;
    public static final int LESSON = 45;
    public static final int TIME1 = 5;
    public static final int TIME2 = 15;
    Scanner scanner = new Scanner(System.in);

    private int enterTheNum() {
        var numOfLesson = 0;
        do {
            try {
                System.out.println("Введите номер урока(1-10): ");
                var operation = scanner.nextLine();
                numOfLesson = Integer.parseInt(operation);
            } catch (NumberFormatException a) {
                System.out.println("Вы ввели недействительное число, попробуйте ещё раз:( ");
            }
        } while (numOfLesson < 1 || numOfLesson > 10);
        return numOfLesson;
    }

    private int countResultOfMinute() {
        var numOfLesson = enterTheNum();
        var breaks = numOfLesson - 1;
        var bigBreaks = breaks / 2;
        return (numOfLesson * LESSON) + (TIME2 * bigBreaks) + (TIME1 * (breaks - bigBreaks));
    }

    public void outputConsole() {
        var timeOfMinute = countResultOfMinute();
        var timeResultOfHours = START_OF__LESSON + timeOfMinute / ONE_HORSE;
        var timeResultOfMinutes = timeOfMinute % ONE_HORSE;
        if (timeResultOfMinutes < 10) {
            System.out.println("Урок закончится в: " + timeResultOfHours + ":0" + timeResultOfMinutes);
        } else {
            System.out.println("Урок закончится в: " + timeResultOfHours + ":" + timeResultOfMinutes);
        }
    }
}
