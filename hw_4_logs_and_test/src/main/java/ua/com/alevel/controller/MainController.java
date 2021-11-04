package ua.com.alevel.controller;

import java.io.IOException;

import static ua.com.alevel.UtilityHelper.UtilityHelper.*;

public class MainController {
    public void run() {
        print("Car owner database program");
        print("choose operation: ");
        String position;
        try {
            runNavigation();
            while ((position = getString()) != null) {
                crud(position);
                position = getString();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position);
            }
        } catch (IOException e) {
            print("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        print("If you want to act in relation to the driver, press 1");
        print("If you want to act in relation to the car, press 2");
        print("If you want to exit the program, press 0");
        print("");
    }

    private void crud(String position) throws IOException {
        switch (position) {
            case "1" -> DriverController.getInstance().crud(position);
            case "2" -> CarController.getInstance().crud(position);
            case "3" -> DriverController.getInstance().outputDriverAndCar();
        }
        runNavigation();
    }
}
