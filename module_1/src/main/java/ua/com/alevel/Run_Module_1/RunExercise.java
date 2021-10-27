package ua.com.alevel.Run_Module_1;

import ua.com.alevel.level1.AreaOfTriangle;
import ua.com.alevel.level1.ChessHorse;
import ua.com.alevel.level1.GenericSymbols;
import ua.com.alevel.level2.BinaryTree;
import ua.com.alevel.level2.StringValidation;
import ua.com.alevel.level3.Window;

import java.util.Scanner;

public class RunExercise {
    static Scanner scanner = new Scanner(System.in);

    public void runExerciseLevelOne() {
        String line;
        do {
            System.out.println("Уровень 1");
            System.out.println("(1)Уникальные числа\n(2)Ход коня\n(3)Площадь тругольника\n(0)Выход в главное меню");
            line = scanner.nextLine();
            switch (line) {
                case "1" -> runExerciseLevelOneExerciseOne();
                case "2" -> runExerciseLevelOneExerciseTwo();
                case "3" -> runExerciseLevelOneExerciseThree();
            }
        } while (!line.equals("0"));
    }

    public void runExerciseLevelTwo() {
        String line;
        do {
            System.out.println("Уровень 2");
            System.out.println("(1)Проверка строки на валидность\n(2)Бинарное дерево\n(0)Выход в главное меню");
            line = scanner.nextLine();
            switch (line) {
                case "1" -> runExerciseLevelTwoExerciseOne();
                case "2" -> runExerciseLevelTwoExerciseTwo();
            }
        } while (!line.equals("0"));
    }

    public void runExerciseLevelThree() {
        runExerciseLevelThreeExerciseOne();
    }

    public void runExerciseLevelOneExerciseOne() {
        GenericSymbols genericSymbols = new GenericSymbols();
        genericSymbols.outputConsole();
    }

    public void runExerciseLevelOneExerciseTwo() {
        ChessHorse chessHorse = new ChessHorse();
        ChessHorse.outputConsole();
    }

    public void runExerciseLevelOneExerciseThree() {
        AreaOfTriangle areaOfTriangle = new AreaOfTriangle();
        areaOfTriangle.outputConsole();
    }

    public void runExerciseLevelTwoExerciseOne() {
        StringValidation stringValidation = new StringValidation();
        stringValidation.outputConsole();
    }

    public void runExerciseLevelTwoExerciseTwo() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.outputConsole();
    }

    public void runExerciseLevelThreeExerciseOne() {
        Window window = new Window();
        javax.swing.SwingUtilities.invokeLater(window);
    }
}
