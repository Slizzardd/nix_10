package ua.com.alevel;

import ua.com.alevel.controller.Controller;

import static ua.com.alevel.UtilityHelper.print;

public class Start_Hw_6 {
    public static void main(String[] args) {
        print("-------------------------------------------------------------- Консольный календарь --------------------------------------------------------------------------------");
        print("  Программа способна на:\n" +
                "- прибавлене и убавление времени от даты\n" +
                "- сортировка дат по убыванию/возврастанию\n" +
                "\n_______________________________________________________________________________________________________________________________________________________________\n");
        new Controller().run();
    }
}