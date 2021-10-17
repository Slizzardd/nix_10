package ua.com.alevel;


import org.apache.commons.lang3.time.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class School {
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
        Date date = new Date(21600000);
        date = DateUtils.addMinutes(date, countResultOfMinute());
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
        System.out.println("Урок закончится в " + format1.format(date));
    }
}
