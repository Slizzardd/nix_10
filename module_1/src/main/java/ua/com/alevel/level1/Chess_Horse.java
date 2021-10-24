package ua.com.alevel.level1;

import java.util.Locale;
import java.util.Scanner;

public class Chess_Horse {
    private static final char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private static final char[] NUMBERS = {'1', '2', '3', '4', '5', '6', '7', '8'};
    private static final int FIELD_SIZE = 8;
    static Scanner scanner = new Scanner(System.in);


    static {
        System.out.println("Программа вычисляет возможно ли походить конем в поле 8х8");
    }


    private static char[] inputHorse() {
        System.out.println("Пожалуйста, введите куда бы вы хотели поставить коня(Пример: A3): ");
        String input = scanner.nextLine().toUpperCase(Locale.ROOT);
        if (validationInputCheck(input)) {
            return input.toCharArray();
        } else {
            System.out.println("Вы пытаетесь выбрать клетку что не находится на поле:( ");
            return inputHorse();
        }
    }

    private static boolean validationInputCheck(String line) {
        return line.matches("[A-H][1-8]");
    }


    private static void outputFields(char[] horse) {
        System.out.println("Все возможные ходы представлены как '!'");

        StringBuilder vertical = new StringBuilder();
        System.out.print(" ");
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(" " + LETTERS[i]);
        }

        System.out.println();

        for (int i = 0; i < FIELD_SIZE; i++) {
            vertical.append(NUMBERS[i]);
            vertical.append(" ");

            for (int j = 0; j < FIELD_SIZE; j++) {

                //Куда мы ставим коня
                if (horse[0] == LETTERS[j] && horse[1] == NUMBERS[i]) {
                    vertical.append('К');

                } else {
                    //Формула хода коня
                    int dx = Math.abs(horse[0] - LETTERS[j]);
                    int dy = Math.abs(horse[1] - NUMBERS[i]);

                    if (dx == 1 && dy == 2 || dx == 2 && dy == 1) {
                        vertical.append('!');
                    } else {
                        vertical.append('0');
                    }
                }
                vertical.append(' ');
            }
            vertical.append('\n');
        }
        System.out.println(vertical);
    }


    public static void outputConsole() {
        outputFields(inputHorse());
    }
}
