package ua.com.alevel.Run_Module_1;

import java.util.Scanner;

public class RunLevel {

    private static final Scanner scanner = new Scanner(System.in);

    public void runMenu() {
        RunExercise runExercise = new RunExercise();
        String menu;
        do {
            System.out.println("Введите номер уровня 1-3(0 для выхода из программы): ");
            menu = scanner.nextLine();
            switch (menu) {
                case "1" -> runExercise.runExerciseLevelOne();
                case "2" -> runExercise.runExerciseLevelTwo();
                case "3" -> runExercise.runExerciseLevelThree();
            }
        } while (!menu.equals("0"));
    }
}
