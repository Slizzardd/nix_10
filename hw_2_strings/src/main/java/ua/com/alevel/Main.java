package ua.com.alevel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите свой вариант(1-3), либо же 0 для выхода из программы: ");
        String operation;
        while ((operation = scanner.nextLine()) != null) {
            logic(operation);
            operation = scanner.nextLine();
            switch (operation) {
                case "0" -> System.exit(0);
                case "1" -> logic(operation);
            }
        }
    }

    private static void logic(String operation) throws InputMismatchException {
        Run run = new Run();
        switch (operation) {
            case "1" -> run.exercise1();
            case "2" -> run.exercise2();
            case "3" -> run.exercise3();
        }
        System.out.println("Введите свой вариант(1-3), либо же 0 для выхода из программы");
    }
}
