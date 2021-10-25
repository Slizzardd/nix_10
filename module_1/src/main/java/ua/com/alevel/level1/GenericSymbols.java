package ua.com.alevel.level1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenericSymbols {
    static Scanner scanner = new Scanner(System.in);
    static{
        System.out.println("Задание 1 уровня 1 \nОтфильтровать строку и выбрать уникальные числа");
    }


    private String enterTheString() {
        System.out.println("Пожалуйста, введите вашу строку:");
        return scanner.next();
    }


    private char[] convertStringToArrayNotNum(String line){
        var numbersOnly = line.replaceAll("\\D", "");
        return numbersOnly.toCharArray();
    }


    private int numberOfUniqueNumbers(){
        var arrayNumbersOnly = convertStringToArrayNotNum(enterTheString());
        List<Integer> myList = new ArrayList<>();
        for (int x : arrayNumbersOnly) {
            if (!myList.contains(x))
                myList.add(x);
        }
        return myList.size();
    }


    public void outputConsole(){
        System.out.println("Число уникальных чисел в вашей строке: " + numberOfUniqueNumbers());
    }
}