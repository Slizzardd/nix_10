package ua.com.alevel.run_module_2;

import ua.com.alevel.task1.controller.DatesController;
import ua.com.alevel.task2.controller.NameController;
import ua.com.alevel.task3.controller.CityController;

import static ua.com.alevel.UtilityHelper.getString;

public class RunTask {
    public void runMenu() {
        String menu;
        do {
            System.out.println("Enter number task(1-3/ 0 - exit): ");
            menu = getString();
            switch (menu) {
                case "1" -> new DatesController().run();
                case "2" -> new NameController().run();
                case "3" -> new CityController().run();
            }
        } while (!menu.equals("0"));
    }
}
