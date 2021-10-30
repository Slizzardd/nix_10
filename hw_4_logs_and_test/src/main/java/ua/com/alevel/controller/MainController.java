package ua.com.alevel.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainController {

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Программа базы данных владельцев авто");
        System.out.println("Выберите действие: ");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Проблема: = " + e.getMessage());
        }
    }
        private void runNavigation() {
            System.out.println();
            System.out.println("Если вы хотите действовать относительно владельца, нажмите 1");
            System.out.println("Если вы хотите действовать относительно машины, нажмите 2");
            System.out.println("Если вы хотите выйти из программы, нажмите 0");
            System.out.println();
        }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> DriverController.getInstance().crud(position, reader);
            case "2" -> CarController.getInstance().crud(position, reader);
        }
        runNavigation();
    }
}
