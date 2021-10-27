package ua.com.alevel.level1;

import java.awt.Point;
import java.text.DecimalFormat;
import java.util.Scanner;

public class AreaOfTriangle {
    static Scanner scanner = new Scanner(System.in);
    private static Point coordinate_A;
    private static Point coordinate_B;
    private static Point coordinate_C;

    static {
        System.out.println("Программа вычисляет плоащдь введеного треугольника на плоскости(X, Y)");
    }

    private boolean checkIfPointsOrganizingLine() {
        return getAreaTriangle() == 0;
    }

    private double distanceBetweenTwoPoints(Point coordinateP1, Point coordinateP2) {
        double result_x = coordinateP1.x - coordinateP2.x;
        double result_y = coordinateP1.y - coordinateP2.y;
        return Math.sqrt(result_x * result_x + result_y * result_y);
    }

    private double getAreaTriangle() {
        double distanceAB = distanceBetweenTwoPoints(coordinate_A, coordinate_B);
        double distanceBC = distanceBetweenTwoPoints(coordinate_B, coordinate_C);
        double distanceCA = distanceBetweenTwoPoints(coordinate_C, coordinate_A);
        double square = (distanceAB + distanceBC + distanceCA) / 2;
        return Math.sqrt(square * (square - distanceAB) * (square - distanceBC) * (square - distanceCA));
    }

    private void inputPoint() {
        coordinate_A = enteringCoordinates();
        coordinate_B = enteringCoordinates();
        coordinate_C = enteringCoordinates();
    }

    private Point enteringCoordinates() {
        char[] message = {'X', 'Y'};
        int[] coordinates = new int[2];
        for (int i = 0; i < 2; i++) {
            while (true) {
                try {
                    System.out.println("Пожалуйста, введите координату " + message[i] + ": ");
                    String inputCoords = scanner.next();
                    coordinates[i] = Integer.parseInt(inputCoords);
                    break;
                } catch (NumberFormatException a) {
                    System.out.println("Вы ввели недействительное число, попробуйте ещё раз:( ");
                }
            }
        }
        return new Point(coordinates[0], coordinates[1]);
    }

    public void outputConsole() {
        inputPoint();
        if (!checkIfPointsOrganizingLine()) {
            System.out.println("Площадь вашего треугольника: " + new DecimalFormat("#.00").format(getAreaTriangle()));
        } else {
            System.out.println("Точки что вы ввели обраюсь линию, попробуйте ещё раз");
        }
    }
}
