package ua.com.alevel.level1;

import java.util.Locale;
import java.util.Scanner;

public class ChessHorse {

    private static final char[] LETTERS_FOR_THE_FIELD = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private static final char[] NUMBERS_FOR_THE_FIELD = {'1', '2', '3', '4', '5', '6', '7', '8'};
    private static final int FIELD_SIZE = 8;
    static Scanner scanner = new Scanner(System.in);

    static {
        System.out.println("Программа вычисляет возможно ли походить конем в поле 8х8");
    }

    private static char[] enteringHorse() {
        System.out.println("Пожалуйста, введите куда бы вы хотели поставить коня(Пример: A3): ");
        String inputData = scanner.nextLine().toUpperCase(Locale.ROOT);
        if (validationEnteringHorseCheck(inputData)) {
            return inputData.toCharArray();
        } else {
            System.out.println("Вы пытаетесь выбрать клетку что не находится на поле:( ");
            return enteringHorse();
        }
    }

    private static boolean validationEnteringHorseCheck(String inputData) {
        return inputData.matches("[A-H][1-8]");
    }

    private static void displayPossibleMoves(char[] horse) {
        System.out.println("Все возможные ходы представлены как '!'");
        //Отрисовка букв
        StringBuilder field = new StringBuilder();
        System.out.print(" ");
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(" " + LETTERS_FOR_THE_FIELD[i]);
        }
        System.out.println();
        //Вывод цифр, поля, коня и возможных ходов
        for (int i = 0; i < FIELD_SIZE; i++) {
            field.append(NUMBERS_FOR_THE_FIELD[i]);
            field.append(" ");
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (horse[0] == LETTERS_FOR_THE_FIELD[j] && horse[1] == NUMBERS_FOR_THE_FIELD[i]) {
                    field.append('К');
                } else {
                    int dx = Math.abs(horse[0] - LETTERS_FOR_THE_FIELD[j]);
                    int dy = Math.abs(horse[1] - NUMBERS_FOR_THE_FIELD[i]);

                    if (dx == 1 && dy == 2 || dx == 2 && dy == 1)
                        field.append('!');
                    else
                        field.append('0');
                }
                field.append(' ');
            }
            field.append('\n');
        }
        System.out.println(field);
    }


    public static void outputConsole() {
        displayPossibleMoves(enteringHorse());
    }
}
