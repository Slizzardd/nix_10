package ua.com.alevel.level1;

import java.awt.Point;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Area_Of_Triangle {
    static Scanner scanner = new Scanner(System.in);
    private static Point A;
    private static Point B;
    private static Point C;


    private boolean checkInLine() {
        return getAreaTriangle() == 0;
    }

    private double distanceBetweenTwoPoints(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double getAreaTriangle() {
        double ab = distanceBetweenTwoPoints(A, B);
        double bc = distanceBetweenTwoPoints(B, C);
        double ca = distanceBetweenTwoPoints(C, A);
        double s = (ab + bc + ca) / 2;
        return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
    }

    private void inputPoint() {
         A = inputCoords();
         B = inputCoords();
         C = inputCoords();
    }

    private Point inputCoords() {
        char[] message = {'X', 'Y'};
        int[] chars = new int[2];
        for (int i = 0; i < 2; i++) {
            try {
                System.out.println("Пожалуйста, введите координату " + message[i] + ": ");
                String symbol = scanner.next();
                chars[i] = Integer.parseInt(symbol);
            } catch (NumberFormatException a) {
                System.out.println("Вы ввели недействительное число, попробуйте ещё раз:( ");
            }
        }
        return new Point(chars[0], chars[1]);
    }

    public void outputConsole() {
        inputPoint();
        if (!checkInLine()) {
            System.out.println("Площадь вашего треугольника: " + new DecimalFormat("#.00").format(getAreaTriangle()));
        } else {
            System.out.println("Точки что вы ввели обраюсь линию, попробуйте ещё раз");
        }
    }
}
