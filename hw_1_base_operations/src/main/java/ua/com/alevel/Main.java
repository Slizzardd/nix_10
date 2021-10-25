package ua.com.alevel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        switch (operation) {
            case "1" -> {
                Number number = new Number();
                number.outputConsole();
            }
            case "2" -> {
                Symbol symbol = new Symbol();
                symbol.outputConsole();
            }
            case "3" -> {
                School school = new School();
                school.outputConsole();
            }
        }
        System.out.println("Введите свой вариант(1-3), либо же 0 для выхода из программы");
    }

}